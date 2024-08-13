package perpustakaan_backend.service;

import perpustakaan_backend.dto.BukuDto;

import java.util.List;

public interface BukuService {
    BukuDto createBuku(BukuDto bukuDto);
    BukuDto updateBuku(Long id,BukuDto updatedBukuDto);
    void deleteBuku(Long id);
    BukuDto getBukuById(Long id);
    List<BukuDto> getAllBuku();

}
