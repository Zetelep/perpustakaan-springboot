package perpustakaan_backend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import perpustakaan_backend.dto.MahasiswaDto;
import perpustakaan_backend.entity.Mahasiswa;
import perpustakaan_backend.exception.ResourceNotFoundException;
import perpustakaan_backend.mapper.MahasiswaMapper;
import perpustakaan_backend.repository.MahasiswaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MahasiswaServiceImpl implements MahasiswaService {

    private MahasiswaRepository mahasiswaRepository;
    @Override
    public MahasiswaDto createMahasiswa(MahasiswaDto mahasiswaDto) {
        Mahasiswa mahasiswa = MahasiswaMapper.mapToMahasiswa(mahasiswaDto);
        Mahasiswa savedMahasiswa = mahasiswaRepository.save(mahasiswa);
        return MahasiswaMapper.mapToMahasiswaDto(mahasiswa);
    }

    @Override
    public MahasiswaDto updateMahasiswa(Long id, MahasiswaDto updatedMahasiswa) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Mahasiswa Doesn't Exist")
                );
        mahasiswa.setNama(updatedMahasiswa.getNama());
        mahasiswa.setNim(updatedMahasiswa.getNim());
        mahasiswa.setJurusan(updatedMahasiswa.getJurusan());

        Mahasiswa newUpdatedMahasiswa = mahasiswaRepository.save(mahasiswa);
        return MahasiswaMapper.mapToMahasiswaDto(newUpdatedMahasiswa);
    }

    @Override
    public void deleteMahasiswa(Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Mahasiswa Doesn't Exist")
                );

        mahasiswaRepository.deleteById(id);
    }

    @Override
    public MahasiswaDto getMahasiswaById(Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Mahasiswa Doesn't Exist")
                );

        return MahasiswaMapper.mapToMahasiswaDto(mahasiswa);
    }

    @Override
    public List<MahasiswaDto> getAllMahasiwa() {
        List<Mahasiswa> listMahasiswa = mahasiswaRepository.findAll();
        return listMahasiswa
                .stream()
                .map((mahasiswa )-> MahasiswaMapper.mapToMahasiswaDto(mahasiswa))
                .collect(Collectors.toList());
    }
}
