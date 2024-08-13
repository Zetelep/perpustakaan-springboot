package perpustakaan_backend.mapper;

import perpustakaan_backend.dto.MahasiswaDto;
import perpustakaan_backend.entity.Mahasiswa;

public class MahasiswaMapper {

    public static MahasiswaDto mapToMahasiswaDto(Mahasiswa mahasiswa){
        return  new MahasiswaDto(
                mahasiswa.getId(),
                mahasiswa.getNama(),
                mahasiswa.getNim(),
                mahasiswa.getJurusan()
        );
    }

    public static Mahasiswa mapToMahasiswa(MahasiswaDto mahasiswaDto){
        return  new Mahasiswa(
                mahasiswaDto.getId(),
                mahasiswaDto.getNama(),
                mahasiswaDto.getNim(),
                mahasiswaDto.getJurusan()
        );
    }
}
