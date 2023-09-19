package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class GerenciadorDeTarefasTests {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();
    private final String tituloBase = "Lavar louça";
    private final String descricaoBase = "Descrição genérica";
    private final LocalDate dataBase = LocalDate.of(2030, 12, 12);
    private final Prioridade prioridadeBase = Prioridade.MEDIA;

    @BeforeEach
    public void setGerenciadorDeTarefas() throws Exception {

        gerenciadorDeTarefas.criarTarefa(
                tituloBase,
                descricaoBase,
                dataBase,
                prioridadeBase
        );
    }

    @Test
    public void testCriarNovaTarefaValida() {
        Assertions.assertDoesNotThrow(() -> {
            String titulo = "Call mom";

            gerenciadorDeTarefas.criarTarefa(
                    titulo,
                    descricaoBase,
                    dataBase,
                    prioridadeBase
            );
        });
    }

    @Test
    public void testCriarTarefaJaExistente() {
        Assertions.assertThrows(Exception.class, () -> {
            gerenciadorDeTarefas.criarTarefa(
                    tituloBase,
                    descricaoBase,
                    dataBase,
                    prioridadeBase
            );
        });
    }

    @Test
    public void testUpdateTarefaValida() {
        Assertions.assertDoesNotThrow(() -> {
            gerenciadorDeTarefas.getTarefa(tituloBase).setDescricao("a");
        });
    }

    @Test
    public void testUpdateTarefaInexistente() {
        Assertions.assertThrows(Exception.class, () -> {
            gerenciadorDeTarefas.getTarefa("Gol").setDescricao("a");
        });
    }

    @Test
    public void testDeleteTarefaValida() throws Exception {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa(tituloBase);

        int novoTamanhoDaLista = gerenciadorDeTarefas.length();
        int expectedTamanhoDaLista = tamanhoDaLista - 1;

        Assertions.assertEquals(expectedTamanhoDaLista, novoTamanhoDaLista);
    }

    @Test
    public void testDeleteTarefaInexistente() {
        Assertions.assertThrows(Exception.class, () -> {
            gerenciadorDeTarefas.deleteTarefa("Gol");
        });
    }

    @Test
    public void testGetAllTarefas() throws Exception {
        gerenciadorDeTarefas.criarTarefa("Tarefa 1", descricaoBase, dataBase, prioridadeBase);
        gerenciadorDeTarefas.criarTarefa("Tarefa 2", descricaoBase, dataBase, prioridadeBase);

        List<Tarefa> tarefas = gerenciadorDeTarefas.getAllTarefas();
        Assertions.assertEquals(tarefas.size(), 3);
    }

}