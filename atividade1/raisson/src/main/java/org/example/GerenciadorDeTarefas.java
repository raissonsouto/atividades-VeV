package org.example;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
    private List<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        tarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, String dataDeVencimento, Prioridade prioridade) {
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

    public void deleteTarefa(String titulo) {
        for (Tarefa tarefa: tarefas) {
            if (tarefa.getTitulo().equals(titulo)) {
                tarefas.remove(tarefa);
                break;
            }
        }
    }

    public int length() {
        return tarefas.size();
    }
}
