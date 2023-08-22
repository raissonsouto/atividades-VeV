package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TarefaTests {

    private Tarefa tarefa;

    @BeforeEach
    public void setTarefa() {
        tarefa = new Tarefa("Tarefa", "descricao", LocalDate.of(123,9,12), Prioridade.ALTA);
    }
    @Test
    public void testUpdateTitulo() {
        String titulo = "Trocando o título.";
        tarefa.setTitulo(titulo);

        Assertions.assertEquals(titulo, tarefa.getTitulo());
    }

    @Test
    public void testUpdateDescricao() {
        String descricao = "Trocando a descrição.";
        tarefa.setDescricao(descricao);

        Assertions.assertEquals(descricao, tarefa.getDescricao());
    }

    @Test
    public void testUpdateDataDeVencimento() {
        LocalDate dataDeVencimento = LocalDate.of(1, 1, 1);
        tarefa.setDataDeVencimento(dataDeVencimento);

        Assertions.assertEquals(dataDeVencimento, tarefa.getDataDeVencimento());
    }

    @Test
    public void testDefinirPrioridadeAlta() {
        tarefa.setPrioridade(Prioridade.ALTA);
        Assertions.assertEquals(Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeMedia() {
        tarefa.setPrioridade(Prioridade.MEDIA);
        Assertions.assertEquals(Prioridade.MEDIA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeBaixa() {
        tarefa.setPrioridade(Prioridade.BAIXA);
        Assertions.assertEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }
}
