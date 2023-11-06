package com.code.avaliacao.controller;

import com.code.avaliacao.enums.RiscoProjetoEnum;
import com.code.avaliacao.enums.StatusProjetoEnum;
import com.code.avaliacao.model.Projeto;
import com.code.avaliacao.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.criarProjeto(projeto);
        return ResponseEntity.ok(novoProjeto);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodosProjetos() {
        return ResponseEntity.ok(projetoService.listarTodosProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.buscarProjetoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        return ResponseEntity.ok(projetoService.atualizarProjeto(id, projeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public List<Map<String, String>> listStatuses() {
        return Arrays.stream(StatusProjetoEnum.values())
                .map(enumValue -> {
                    Map<String, String> enumMap = new HashMap<>();
                    enumMap.put("id", enumValue.name());
                    enumMap.put("descricao", enumValue.getDescricao());
                    return enumMap;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/riscos")
    public RiscoProjetoEnum[] listarRiscoProjeto() {
        return RiscoProjetoEnum.values();
    }
}
