package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar(){
        return tarefaService.listar();
    }

    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@RequestBody Tarefa tarefa) {
        return ResponseEntity
                .status(200)
                .body(tarefaService.cadastrar(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {

        var operacaoBemSucedida = tarefaService.excluirPorId(id);

        var response404 = ResponseEntity
                .status(404)
                .build();

        try {
            if (!operacaoBemSucedida) {
                return response404;
            }

            return ResponseEntity
                .noContent()
                .build();

        } catch (RuntimeException e) {
            return response404;
        }
    }
}
