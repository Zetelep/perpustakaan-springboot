package perpustakaan_backend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import perpustakaan_backend.dto.MahasiswaDto;
import perpustakaan_backend.dto.PeminjamanDto;
import perpustakaan_backend.entity.Buku;
import perpustakaan_backend.entity.Mahasiswa;
import perpustakaan_backend.entity.Peminjaman;
import perpustakaan_backend.exception.ResourceNotFoundException;
import perpustakaan_backend.mapper.BukuMapper;
import perpustakaan_backend.mapper.PeminjamanMapper;
import perpustakaan_backend.repository.BukuRepository;
import perpustakaan_backend.repository.MahasiswaRepository;
import perpustakaan_backend.repository.PeminjamanRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PeminjamanServiceImpl implements PeminjamanService{

    private PeminjamanRepository peminjamanRepository;
    private MahasiswaRepository mahasiswaRepository;
    private BukuRepository bukuRepository;
    @Override
    public PeminjamanDto createPeminjaman(PeminjamanDto peminjamanDto) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(peminjamanDto.getMahasiswaId())
                .orElseThrow(()->new ResourceNotFoundException("Mahasiswa doens't Exist"));
        Buku buku = bukuRepository.findById(peminjamanDto.getBukuId())
                .orElseThrow(()->new ResourceNotFoundException("Buku doens't Exist"));

        if(!isBukuAvailable(buku)){
            throw new ResourceNotFoundException("Buku sedang kosong");
        }

        if(TotalPeminjamanInLastMonth(mahasiswa)>=10){
            throw new ResourceNotFoundException("Anda telah meminjam melebihi bata");
        }

        Peminjaman peminjaman = PeminjamanMapper.mapToPeminjaman(peminjamanDto,mahasiswa,buku);
        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);

        buku.setJumlahDipinjam(buku.getJumlahDipinjam()+1);
        bukuRepository.save(buku);

        return PeminjamanMapper.mapToPeminjamanDto(peminjaman);
    }
    @Override
    public PeminjamanDto updatePeminjaman(Long id, PeminjamanDto updatedPeminjaman) {
        Peminjaman peminjaman = peminjamanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Peminjaman Doesn't Exist")
                );
        Mahasiswa mahasiswa = mahasiswaRepository.findById(updatedPeminjaman.getMahasiswaId())
                .orElseThrow(()->new ResourceNotFoundException("Mahasiswa doens't Exist"));
        Buku buku = bukuRepository.findById(updatedPeminjaman.getBukuId())
                .orElseThrow(()->new ResourceNotFoundException("Buku doens't Exist"));

        peminjaman.setBuku(buku);
        peminjaman.setTanggalPeminjaman(updatedPeminjaman.getTanggalPeminjaman());
        peminjaman.setMahasiswa(mahasiswa);
        peminjaman.setTanggalBatasPengembalian(updatedPeminjaman.getTanggalBatasPengembalian());
        peminjaman.setTanggalPengembalian(updatedPeminjaman.getTanggalPengembalian());

        if(peminjaman.getTanggalPengembalian() !=null){
            Long denda = HitungDenda(peminjaman.getTanggalBatasPengembalian(),peminjaman.getTanggalPengembalian());
            peminjaman.setDenda(denda);

            buku.setJumlahDipinjam(buku.getJumlahDipinjam()-1);
            bukuRepository.save(buku);
        }
        Peminjaman newUpdatedPeminjaman = peminjamanRepository.save(peminjaman);
        return PeminjamanMapper.mapToPeminjamanDto(peminjaman);
    }

    @Override
    public void deletePeminjaman(Long id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Peminjaman Doesn't Exist")
                );
        peminjamanRepository.deleteById(id);
    }
    @Override
    public List<PeminjamanDto> getAllPeminjaman() {
        List<Peminjaman> listPeminjaman = peminjamanRepository.findAll();
        return listPeminjaman
                .stream()
                .map((peminjaman )-> PeminjamanMapper.mapToPeminjamanDto(peminjaman))
                .collect(Collectors.toList());
    }

    @Override
    public PeminjamanDto getPeminjamanById(Long id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Peminjaman Doesn't Exist")
                );
        return PeminjamanMapper.mapToPeminjamanDto(peminjaman);
    }

    private long HitungDenda(LocalDate tanggalBatasPengembalian, LocalDate tanggalPengembalian) {
        if (tanggalPengembalian.isBefore(tanggalBatasPengembalian) || tanggalPengembalian.isEqual(tanggalBatasPengembalian)) {
            return 0;
        }

        long daysLate = ChronoUnit.DAYS.between(tanggalBatasPengembalian, tanggalPengembalian);
        long denda = 0;
        long rate = 1000;

        for (int i = 1; i <= daysLate; i++) {
            denda += rate;
            if (i % 2 == 0) {
                rate += 1000;
            }
        }

        return denda;
    }

    private Integer TotalPeminjamanInLastMonth(Mahasiswa mahasiswa) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        Integer total = peminjamanRepository.totalPeminjamanWithin(mahasiswa,oneMonthAgo);
        return total;
    }

    private boolean isBukuAvailable(Buku buku) {
        return buku.getKuantitas()- buku.getJumlahDipinjam() > 0;
    }
}

