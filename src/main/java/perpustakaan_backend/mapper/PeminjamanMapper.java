package perpustakaan_backend.mapper;

import perpustakaan_backend.dto.PeminjamanDto;
import perpustakaan_backend.entity.Buku;
import perpustakaan_backend.entity.Mahasiswa;
import perpustakaan_backend.entity.Peminjaman;

public class PeminjamanMapper {

    public static PeminjamanDto mapToPeminjamanDto(Peminjaman peminjaman){
        return new PeminjamanDto(
                peminjaman.getId(),
                peminjaman.getMahasiswa().getId(),
                peminjaman.getBuku().getId(),
                peminjaman.getTanggalPeminjaman(),
                peminjaman.getTanggalBatasPengembalian(),
                peminjaman.getTanggalPengembalian(),
                peminjaman.getDenda()
        );
    }

    public static Peminjaman mapToPeminjaman(PeminjamanDto peminjamanDto, Mahasiswa mahasiswa, Buku buku){

        return new Peminjaman(
                peminjamanDto.getId(),
                mahasiswa,
                buku,
                peminjamanDto.getTanggalPeminjaman(),
                peminjamanDto.getTanggalBatasPengembalian(),
                peminjamanDto.getTanggalPengembalian(),
                peminjamanDto.getDenda()
        );
    }
}
