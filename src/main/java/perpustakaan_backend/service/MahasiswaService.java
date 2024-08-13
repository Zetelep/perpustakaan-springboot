package perpustakaan_backend.service;

import perpustakaan_backend.dto.BukuDto;
import perpustakaan_backend.dto.MahasiswaDto;

import java.util.List;

public interface MahasiswaService {
    MahasiswaDto createMahasiswa(MahasiswaDto mahasiswaDto);
    MahasiswaDto updateMahasiswa(Long id,MahasiswaDto updatedMahasiswa);
    void deleteMahasiswa(Long id);
    MahasiswaDto getMahasiswaById(Long id);
    List<MahasiswaDto> getAllMahasiwa();
}
