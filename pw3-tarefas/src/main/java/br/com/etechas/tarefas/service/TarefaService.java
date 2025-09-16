package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> listar(){
        return repository.findAll();
    }

    public Tarefa cadastrar(Tarefa tarefa) {
        if (tarefa == null) throw new RuntimeException("Tarefa inválida.");

        if (tarefa.getDataLimite()
                .isBefore(LocalDate.now())) throw new RuntimeException("A data da tarefa é inválida.");

        return repository.save(tarefa);
    }

    public boolean excluirPorId(Long id) {
        var tarefa = repository.findById(id);
        
        if (tarefa.isEmpty()) {
            return false;
        } else if (tarefa.get().getStatus() != StatusEnum.PENDENTE) {
            throw new RuntimeException("Tarefa em progresso ou concluída.");
        }

        repository.delete(tarefa.get());
        return true;
    }
}
