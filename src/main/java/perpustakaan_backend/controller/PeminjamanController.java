package perpustakaan_backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perpustakaan_backend.dto.MahasiswaDto;
import perpustakaan_backend.dto.PeminjamanDto;
import perpustakaan_backend.service.PeminjamanService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {
    private PeminjamanService peminjamanService;

    //Create Peminjaman
    @PostMapping
    public ResponseEntity<PeminjamanDto> createPeminjaman(@RequestBody PeminjamanDto peminjamanDto){
        PeminjamanDto savedPeminjaman = peminjamanService.createPeminjaman(peminjamanDto);

        return new ResponseEntity<>(savedPeminjaman, HttpStatus.CREATED);
    }

    //Update Peminjaman
    @PutMapping("/update/{id}")
    public ResponseEntity<PeminjamanDto> updatePeminjaman(@PathVariable("id") Long id, @RequestBody PeminjamanDto updatedPeminjaman){
        PeminjamanDto peminjamanDto = peminjamanService.updatePeminjaman(id,updatedPeminjaman);
        return  ResponseEntity.ok(peminjamanDto);
    }
    //Delete Peminjaman
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePeminjamanById(@PathVariable("id") Long id) {
        peminjamanService.deletePeminjaman(id);
        return ResponseEntity.ok("Delete Success");
    }
    //Get All Peminjaman
    @GetMapping()
    public ResponseEntity<List<PeminjamanDto>> getAllPeminjaman(){
        List <PeminjamanDto> listPeminjaman = peminjamanService.getAllPeminjaman();
        return  ResponseEntity.ok(listPeminjaman);
    }

    //Get Peminjaman By id
    @GetMapping("/{id}")
    public ResponseEntity<PeminjamanDto> getPeminjamanById(@PathVariable("id") Long id){
        PeminjamanDto peminjaman = peminjamanService.getPeminjamanById(id);
        return  ResponseEntity.ok(peminjaman);
    }

}
