package perpustakaan_backend.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import perpustakaan_backend.dto.BukuDto;
import perpustakaan_backend.entity.Buku;

@Service
@AllArgsConstructor
public class BukuMapper {

    public static BukuDto mapToBukuDto(Buku buku){
        return new BukuDto(
                buku.getId(),
                buku.getJudul(),
                buku.getPenulis(),
                buku.getKuantitas(),
                buku.getJumlahDipinjam(),
                buku.getTempatPenyimpanan()
        );
    }

    public static Buku mapToBuku (BukuDto bukuDto){
        return new Buku(
                bukuDto.getId(),
                bukuDto.getJudul(),
                bukuDto.getPenulis(),
                bukuDto.getKuantitas(),
                bukuDto.getJumlahDipinjam(),
                bukuDto.getTempatPenyimpanan()
        );
    }
}
