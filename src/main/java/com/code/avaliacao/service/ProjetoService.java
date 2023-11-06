package com.code.avaliacao.service;

import com.code.avaliacao.dto.ProjetoDTO;
import com.code.avaliacao.exception.DeletarProjetoException;
import com.code.avaliacao.exception.ValidaProjetoException;
import com.code.avaliacao.mapper.ProjetoMapper;
import com.code.avaliacao.model.Projeto;
import com.code.avaliacao.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository, ProjetoMapper projetoMapper) {
        this.projetoRepository = projetoRepository;
        this.projetoMapper = projetoMapper;
    }

    public Projeto criarProjeto(ProjetoDTO projetoDTO) {
        this.validarProjeto(projetoDTO);
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
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

    private void validarProjeto(ProjetoDTO projeto) {
        if (!StringUtils.hasText(projeto.getNome())) {
            throw new ValidaProjetoException("projeto.nome.obrigatorio.error");
        }
        if (projeto.getIdGerente() == null) {
            throw new ValidaProjetoException("projeto.gerente.obrigatorio.error");
        }

        if (projeto.getDataInicio() == null) {
            throw new ValidaProjetoException("projeto.data_inicio.obrigatorio.error");
        }

        if (projeto.getDataPrevisaoFim() != null && projeto.getDataInicio().after(projeto.getDataPrevisaoFim())) {
            throw new ValidaProjetoException("projeto.data_previsao_termino.error");
        }

        if (projeto.getDataFim() != null && projeto.getDataInicio().after(projeto.getDataFim())) {
            throw new ValidaProjetoException("projeto.data_termino.error");
        }
    }
}
