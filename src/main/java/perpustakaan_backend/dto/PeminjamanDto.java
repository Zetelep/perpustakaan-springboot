package perpustakaan_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDto {
    private Long id;
    private Long mahasiswaId;
    private Long bukuId;
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalBatasPengembalian;
    private LocalDate tanggalPengembalian;
    private Long denda;
}
