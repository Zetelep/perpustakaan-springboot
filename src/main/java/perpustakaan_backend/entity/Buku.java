package perpustakaan_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
public class Buku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String judul;
    private String penulis;
    private Integer kuantitas;
    @Column(name = "jumlah_dipinjam")
    @ColumnDefault("0")
    private Integer jumlahDipinjam;

    @Column(name = "tempat_penyimpanan")
    private String tempatPenyimpanan;

}
