package perpustakaan_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaDto {
    private Long id;
    private String nama;
    private String nim;
    private String jurusan;
}
