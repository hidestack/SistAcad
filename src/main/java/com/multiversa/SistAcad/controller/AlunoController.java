package com.multiversa.SistAcad.controller;

import com.multiversa.SistAcad.Service.AlunoService;
import com.multiversa.SistAcad.model.AlunoModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController (AlunoService alunoService) {
        this.alunoService = alunoService;
    }


@PostMapping("/create")
    public ResponseEntity<AlunoModel> create(@RequestBody AlunoModel aluno) {
        return ResponseEntity.ok(alunoService.save(aluno));
}

@GetMapping
    public ResponseEntity<List<AlunoModel>> findAll() {
        return ResponseEntity.ok(alunoService.findAll());
}

@GetMapping("/find/{id}")
    public ResponseEntity<Optional<AlunoModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findById(id));
}

@PutMapping("/update/{id}")
    public ResponseEntity<AlunoModel> update(@PathVariable Long id, @RequestBody AlunoModel aluno) {
        return ResponseEntity.ok(alunoService.update(aluno, id));
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
}

}
//kkk