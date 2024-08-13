package perpustakaan_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perpustakaan_backend.entity.Mahasiswa;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa,Long> {
}
