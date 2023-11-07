package com.code.avaliacao.service;

import com.code.avaliacao.dto.PessoaDTO;
import com.code.avaliacao.dto.ProjetoDTO;
import com.code.avaliacao.enums.RiscoProjetoEnum;
import com.code.avaliacao.exception.DeletarProjetoException;
import com.code.avaliacao.exception.ValidaProjetoException;
import com.code.avaliacao.mapper.ProjetoMapper;
import com.code.avaliacao.model.Pessoa;
import com.code.avaliacao.model.Projeto;
import com.code.avaliacao.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final MessageSource messageSource;

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository, ProjetoMapper projetoMapper, MessageSource messageSource) {
        this.projetoRepository = projetoRepository;
        this.projetoMapper = projetoMapper;
        this.messageSource = messageSource;
    }

    public Projeto criarProjeto(ProjetoDTO projetoDTO) {
        this.validarProjeto(projetoDTO);
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto.setRisco(RiscoProjetoEnum.BAIXO_RISCO);
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        return projetoRepository.findById(id)
                .map(projeto -> {
                    projeto.setNome(projetoDTO.getNome());
                    projeto.setDataInicio(projetoDTO.getDataInicio());
                    projeto.setGerente(new Pessoa());
                    projeto.getGerente().setId(projetoDTO.getIdGerente());
                    projeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
                    projeto.setDataFim(projetoDTO.getDataFim());
                    projeto.setOrcamento(projetoDTO.getOrcamento());
                    projeto.setDescricao(projetoDTO.getDescricao());
                    projeto.setStatus(projetoDTO.getStatus());
                    Set<Pessoa> membros = projetoDTO.getMembros()
                            .stream()
                            .map(this::convertToEntity) // 'this::convertToEntity' deve ser um método que você define
                            .collect(Collectors.toSet());
                    projeto.setMembros(membros);
                    return projetoRepository.save(projeto);
                })
                .orElseGet(() -> {
                    projetoDTO.setId(id);
                    return projetoRepository.save(projetoMapper.toEntity(projetoDTO));
                });
    }
    private Pessoa convertToEntity(PessoaDTO pessoaDTO) {
        return Pessoa.builder().id(pessoaDTO.getId()).build();
    }
    public void deletarProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new DeletarProjetoException(messageSource, "projeto.notfound.error", id));

        switch (projeto.getStatus()) {
            case INICIADO:
            case EM_ANDAMENTO:
            case ENCERRADO:
                throw new DeletarProjetoException(messageSource, "delete.projeto.error", projeto.getStatus());
            default:
                projetoRepository.deleteById(id);
        }
    }

    private void validarProjeto(ProjetoDTO projeto) {
        if (!StringUtils.hasText(projeto.getNome())) {
            throw new ValidaProjetoException(messageSource, "projeto.nome.obrigatorio.error");
        }
        if (projeto.getIdGerente() == null) {
            throw new ValidaProjetoException(messageSource, "projeto.gerente.obrigatorio.error");
        }

        if (projeto.getDataInicio() == null) {
            throw new ValidaProjetoException(messageSource, "projeto.data_inicio.obrigatorio.error");
        }

        if (projeto.getDataPrevisaoFim() != null && projeto.getDataInicio().after(projeto.getDataPrevisaoFim())) {
            throw new ValidaProjetoException(messageSource, "projeto.data_previsao_termino.error");
        }

        if (projeto.getDataFim() != null && projeto.getDataInicio().after(projeto.getDataFim())) {
            throw new ValidaProjetoException(messageSource, "projeto.data_termino.error");
        }
    }
}
