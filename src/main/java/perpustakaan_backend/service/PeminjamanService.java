package perpustakaan_backend.service;

import perpustakaan_backend.dto.PeminjamanDto;

import java.time.LocalDate;
import java.util.List;

public interface PeminjamanService {
    PeminjamanDto createPeminjaman(PeminjamanDto peminjamanDto);
    PeminjamanDto updatePeminjaman(Long id, PeminjamanDto updatedPeminjaman);
    void deletePeminjaman(Long id);
    List<PeminjamanDto> getAllPeminjaman();
    PeminjamanDto getPeminjamanById(Long id);

}
