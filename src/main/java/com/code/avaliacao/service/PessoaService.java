package com.code.avaliacao.service;

import com.code.avaliacao.dto.PessoaDTO;
import com.code.avaliacao.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(p -> new PessoaDTO(p.getNome(), p.getFuncionario()))
                .collect(Collectors.toList());
    }
}

