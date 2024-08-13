package perpustakaan_backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perpustakaan_backend.dto.BukuDto;
import perpustakaan_backend.service.BukuService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/buku")
public class BukuController {
    private BukuService bukuService;

    //Create Buku
    @PostMapping
    public ResponseEntity<BukuDto> createBuku(@RequestBody BukuDto bukuDto){
        BukuDto savedBuku = bukuService.createBuku(bukuDto);

        return  new ResponseEntity<>(savedBuku, HttpStatus.CREATED);
    }

    //Get Buku by id
    @GetMapping("/{id}")
    public ResponseEntity<BukuDto> getBukuById(@PathVariable("id") Long id){
        BukuDto buku = bukuService.getBukuById(id);

        return  ResponseEntity.ok(buku);
    }

    //Get all buku
    @GetMapping
    public ResponseEntity<List<BukuDto>> getAllBuku(){
        List<BukuDto> listBuku = bukuService.getAllBuku();
        return ResponseEntity.ok(listBuku);
    }

    //Update Buku
    @PutMapping("/update/{id}")
    public ResponseEntity<BukuDto> updateBuku(@PathVariable("id") Long id, @RequestBody BukuDto updatedBuku){
        BukuDto buku = bukuService.updateBuku(id,updatedBuku);

        return ResponseEntity.ok(buku);
    }

    //Delete Buku
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBukuById(@PathVariable("id") Long id){
        bukuService.deleteBuku(id);
        return  ResponseEntity.ok("Delete success!");
    }
}
