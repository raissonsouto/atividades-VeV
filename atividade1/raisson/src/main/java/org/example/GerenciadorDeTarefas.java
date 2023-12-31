package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
    private List<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        tarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, LocalDate dataDeVencimento, Prioridade prioridade) throws Exception {
        if (!(getTarefa(titulo) == null))
            throw new Exception("Tarefa already exist");
        Tarefa tarefa = new Tarefa(titulo, descricao, dataDeVencimento, prioridade);
        tarefas.add(tarefa);
    }

    public Tarefa getTarefa(String titulo) {
        for (Tarefa tarefa: tarefas) {
            if (tarefa.getTitulo().equals(titulo)) {
                return tarefa;
            }
        }

        return null;
    }

    public List<Tarefa> getAllTarefas() {
        return tarefas;
    }

    public void deleteTarefa(String titulo) throws Exception {
        if (getTarefa(titulo) == null)
            throw new Exception("Tarefa does not exist");
        Tarefa tarefa = getTarefa(titulo);
        tarefas.remove(tarefa);
    }

    public int length() {
        return tarefas.size();
    }
}
