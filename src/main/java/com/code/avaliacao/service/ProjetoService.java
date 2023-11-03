package com.code.avaliacao.service;

import com.code.avaliacao.enums.StatusProjetoEnum;
import com.code.avaliacao.exception.DeletarProjetoException;
import com.code.avaliacao.model.Projeto;
import com.code.avaliacao.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto atualizarProjeto(Long id, Projeto projetoAtualizado) {
        return projetoRepository.findById(id)
                .map(projeto -> {
                    projeto.setNome(projetoAtualizado.getNome());
                    projeto.setDataInicio(projetoAtualizado.getDataInicio());
                    projeto.setGerente(projetoAtualizado.getGerente());
                    projeto.setDataPrevisaoFim(projetoAtualizado.getDataPrevisaoFim());
                    projeto.setDataFim(projetoAtualizado.getDataFim());
                    projeto.setOrcamento(projetoAtualizado.getOrcamento());
                    projeto.setDescricao(projetoAtualizado.getDescricao());
                    projeto.setStatus(projetoAtualizado.getStatus());
                    return projetoRepository.save(projeto);
                })
                .orElseGet(() -> {
                    projetoAtualizado.setId(id);
                    return projetoRepository.save(projetoAtualizado);
                });
    }

    public void deletarProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new DeletarProjetoException("project.notfound.error", id));

        switch (projeto.getStatus()) {
            case INICIADO:
            case EM_ANDAMENTO:
            case ENCERRADO:
                throw new DeletarProjetoException("project.status.error", projeto.getStatus());
            default:
                projetoRepository.deleteById(id);
        }
    }


}
