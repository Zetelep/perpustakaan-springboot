package perpustakaan_backend.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perpustakaan_backend.dto.BukuDto;
import perpustakaan_backend.dto.MahasiswaDto;
import perpustakaan_backend.service.MahasiswaService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {

    private MahasiswaService mahasiswaService;

    //Create Mahasiswa
    @PostMapping
    public ResponseEntity<MahasiswaDto> createMahasiwa(@RequestBody MahasiswaDto mahasiswaDto){
        MahasiswaDto savedMahasiswa = mahasiswaService.createMahasiswa(mahasiswaDto);

        return new ResponseEntity<>(savedMahasiswa, HttpStatus.CREATED);
    }

    //Get Mahasiswa
    @GetMapping("/{id}")
    public  ResponseEntity<MahasiswaDto> getMahasiswaById(@PathVariable("id")Long id){
        MahasiswaDto mahasiswa = mahasiswaService.getMahasiswaById(id);
        return ResponseEntity.ok(mahasiswa);
    }
    //Get All Mahasiswa
    @GetMapping()
    public  ResponseEntity<List<MahasiswaDto>> getAllMahasiswa(){
        List<MahasiswaDto> listMahasiswa = mahasiswaService.getAllMahasiwa();
        return ResponseEntity.ok(listMahasiswa);
    }
    //Update Mahasiswa
    @PutMapping("/update/{id}")
    public ResponseEntity<MahasiswaDto> updateBuku(@PathVariable("id") Long id, @RequestBody MahasiswaDto updatedMahasiswa){
        MahasiswaDto mahasiswaDto = mahasiswaService.updateMahasiswa(id, updatedMahasiswa);

        return ResponseEntity.ok(mahasiswaDto);
    }
    //Delete Mahasiswa
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMahasiwaById(@PathVariable("id") Long id){
        mahasiswaService.deleteMahasiswa(id);
        return  ResponseEntity.ok("Delete success!");
    }




}
