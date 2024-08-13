package perpustakaan_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BukuDto {
    private Long id;
    private String judul;
    private String penulis;
    private Integer kuantitas;
    private Integer jumlahDipinjam;
    private String tempatPenyimpanan;

}
