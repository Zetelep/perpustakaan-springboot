package perpustakaan_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perpustakaan_backend.entity.Buku;
import perpustakaan_backend.entity.Mahasiswa;
import perpustakaan_backend.entity.Peminjaman;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman,Long> {

    @Query("SELECT COUNT(p) FROM Peminjaman p WHERE p.mahasiswa = :mahasiswa AND p.tanggalPeminjaman > :date")
    int totalPeminjamanWithin(@Param("mahasiswa") Mahasiswa mahasiswa, @Param("date") LocalDate date);
}
