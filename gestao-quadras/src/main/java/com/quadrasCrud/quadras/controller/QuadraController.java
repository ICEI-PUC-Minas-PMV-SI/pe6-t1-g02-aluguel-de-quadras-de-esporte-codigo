package com.quadrasCrud.quadras.controller;

import com.quadrasCrud.quadras.model.Quadra;
import com.quadrasCrud.quadras.service.QuadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quadras")
public class QuadraController {

    @Autowired
    private QuadraService quadraService;

    @PostMapping
    public ResponseEntity<Quadra> cadastrarQuadra(@RequestBody Quadra quadra) {
        Quadra novaQuadra = quadraService.cadastrarQuadra(quadra);
        return ResponseEntity.ok(novaQuadra);
    }

    @GetMapping
    public ResponseEntity<List<Quadra>> listarQuadras() {
        List<Quadra> quadras = quadraService.listarQuadras();
        return ResponseEntity.ok(quadras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadra> buscarQuadraPorId(@PathVariable Long id) {
        Optional<Quadra> quadra = quadraService.buscarQuadraPorId(id);
        return quadra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quadra> editarQuadra(@PathVariable Long id, @RequestBody Quadra quadra) {
        Quadra quadraAtualizada = quadraService.editarQuadra(id, quadra);
        if (quadraAtualizada != null) {
            return ResponseEntity.ok(quadraAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuadra(@PathVariable Long id) {
        boolean deleted = quadraService.deleteQuadra(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
