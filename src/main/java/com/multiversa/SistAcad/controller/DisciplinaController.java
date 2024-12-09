package com.multiversa.SistAcad.controller;

import com.multiversa.SistAcad.Service.DisciplinaService;
import com.multiversa.SistAcad.model.DisciplinaModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    public void disciplinaController(DisciplinaService alunoService) {
        this.disciplinaService = alunoService;
    }

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }


    @PostMapping("/create")
    public ResponseEntity<DisciplinaModel> create(@RequestBody Long id, DisciplinaModel aluno) {
        return ResponseEntity.ok(disciplinaService.save(id, aluno));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaModel>> findAll() {
        return ResponseEntity.ok(disciplinaService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<DisciplinaModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisciplinaModel> update(@PathVariable Long id, @RequestBody DisciplinaModel aluno) {
        return ResponseEntity.ok(disciplinaService.save(id, aluno));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
