package com.multiversa.SistAcad.controller;

import com.multiversa.SistAcad.Service.MatriculaService;
import com.multiversa.SistAcad.model.MatriculaModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private MatriculaService matriculaService;

    public void MatriculaContorller(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }


    @PostMapping("/create")
    public ResponseEntity<MatriculaModel> create(@RequestBody Long id, MatriculaModel matricula) {
        return ResponseEntity.ok(matriculaService.save(matricula));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaModel>> findAll() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<MatriculaModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MatriculaModel> update(@PathVariable Long id, @RequestBody MatriculaModel matricula) {
        return ResponseEntity.ok(matriculaService.save(matricula));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
