package perpustakaan_backend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import perpustakaan_backend.dto.BukuDto;
import perpustakaan_backend.entity.Buku;
import perpustakaan_backend.exception.ResourceNotFoundException;
import perpustakaan_backend.mapper.BukuMapper;
import perpustakaan_backend.repository.BukuRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BukuServiceImpl implements BukuService{

    private BukuRepository bukuRepository;
    @Override
    public BukuDto createBuku(BukuDto bukuDto) {
        Buku buku = BukuMapper.mapToBuku(bukuDto);
        Buku savedBuku = bukuRepository.save(buku);
        return BukuMapper.mapToBukuDto(buku);
    }

    @Override
    public BukuDto updateBuku(Long id, BukuDto updatedBuku) {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book Doesn't Exist")
                );

        buku.setJudul(updatedBuku.getJudul());
        buku.setPenulis(updatedBuku.getPenulis());
        buku.setKuantitas(updatedBuku.getKuantitas());
        buku.setJumlahDipinjam(updatedBuku.getJumlahDipinjam());

        Buku newUpdatedBuku = bukuRepository.save(buku);
        return BukuMapper.mapToBukuDto(newUpdatedBuku);
    }

    @Override
    public void deleteBuku(Long id) {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book Doesn't Exist")
                );

        bukuRepository.deleteById(id);
    }

    @Override
    public BukuDto getBukuById(Long id) {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book Doesn't Exist")
                );
        return BukuMapper.mapToBukuDto(buku);
    }

    @Override
    public List<BukuDto> getAllBuku() {
        List<Buku> listBuku = bukuRepository.findAll();
        return listBuku
                .stream()
                .map((buku )-> BukuMapper.mapToBukuDto(buku))
                .collect(Collectors.toList());
    }
}
