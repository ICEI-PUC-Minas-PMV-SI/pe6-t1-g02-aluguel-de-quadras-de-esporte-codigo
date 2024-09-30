package com.quadrasCrud.quadras.service;

import com.quadrasCrud.quadras.model.Quadra;
import com.quadrasCrud.quadras.repository.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuadraService {

    @Autowired
    private QuadraRepository quadraRepository;

    public Quadra cadastrarQuadra(Quadra quadra) {
        return quadraRepository.save(quadra);
    }

    public List<Quadra> listarQuadras() {
        return quadraRepository.findAll();
    }

    public Optional<Quadra> buscarQuadraPorId(Long id) {
        return quadraRepository.findById(id);
    }

    public Quadra editarQuadra(Long id, Quadra quadraAtualizada) {
        Optional<Quadra> quadraExistente = quadraRepository.findById(id);
        if (quadraExistente.isPresent()) {
            Quadra quadra = quadraExistente.get();
            quadra.setNome(quadraAtualizada.getNome());
            quadra.setTipo(quadraAtualizada.getTipo());
            quadra.setLocalizacao(quadraAtualizada.getLocalizacao());
            quadra.setDescricao(quadraAtualizada.getDescricao());
            return quadraRepository.save(quadra);
        }
        return null;
    }

    public boolean deleteQuadra(Long id) {
        Optional<Quadra> quadra = quadraRepository.findById(id);
        if (quadra.isPresent()) {
            quadraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
