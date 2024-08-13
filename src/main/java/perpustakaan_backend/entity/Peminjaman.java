package perpustakaan_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peminjaman")
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mahasiswa_id")
    private Mahasiswa mahasiswa;

    @ManyToOne
    @JoinColumn(name = "buku_id")
    private Buku buku;

    @Column(name = "tanggal_peminjaman")
    private LocalDate tanggalPeminjaman;

    @Column(name = "tanggal_batas_pengembalian")
    private LocalDate tanggalBatasPengembalian;

    @Column(name = "tanggal_pengembalian")
    private LocalDate tanggalPengembalian;
    @ColumnDefault("0")
    private Long denda;
}
