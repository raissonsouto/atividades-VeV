package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class GerenciadorDeTarefasTests {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();
    String TITULO = "Tarefa 0";

    @BeforeEach
    public void setGerenciadorDeTarefas() {

        String descricao = "Tarefa exemplo.";
        LocalDate dataDeVencimento = LocalDate.of(2023, 10, 31);
        Prioridade prioridade = Prioridade.BAIXA;

        gerenciadorDeTarefas.criarTarefa(
                TITULO,
                descricao,
                dataDeVencimento,
                prioridade
        );
    }

    @Test
    public void testCriarNovaTarefa() {
        Assertions.assertDoesNotThrow(() -> {
            String titulo = "Tarefa 1";
            String descricao = "Essa primeira tarefa é só um exemplo.";
            LocalDate dataDeVencimento = LocalDate.of(2063, 2, 11);
            Prioridade prioridade = Prioridade.MEDIA;

            gerenciadorDeTarefas.criarTarefa(
                    titulo,
                    descricao,
                    dataDeVencimento,
                    prioridade
            );
        });
    }

    @Test
    public void testDeleteTarefa() {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa(TITULO);
        int novoTamanhoDaLista = gerenciadorDeTarefas.length();

        int expectedTamanhoDaLista = tamanhoDaLista - 1;

        Assertions.assertEquals(expectedTamanhoDaLista, novoTamanhoDaLista);
    }

    @Test
    public void testGetAllTarefas() {
        Tarefa tarefa1 = new Tarefa(
                "Tarefa 1",
                "12341234",
                LocalDate.of(23452, 12, 30),
                Prioridade.BAIXA
        );
        Tarefa tarefa2 = new Tarefa(
                "Tarefa 2",
                "1234",
                LocalDate.of(2010, 12, 10),
                Prioridade.MEDIA
        );
        Tarefa tarefa3 = new Tarefa(
                "Tarefa 3",
                "123412341234",
                LocalDate.of(2000, 12, 30),
                Prioridade.ALTA
        );

        List<Tarefa> tarefas = new ArrayList<>();

        tarefas.add(tarefa1);
        tarefas.add(tarefa2);
        tarefas.add(tarefa3);

        gerenciadorDeTarefas.deleteTarefa(TITULO);

        gerenciadorDeTarefas.criarTarefa("Tarefa 1",
                "12341234",
                LocalDate.of(23452, 12, 30),
                Prioridade.BAIXA);
        gerenciadorDeTarefas.criarTarefa("Tarefa 2",
                "1234",
                LocalDate.of(2010, 12, 10),
                Prioridade.MEDIA);
        gerenciadorDeTarefas.criarTarefa("Tarefa 3",
                "123412341234",
                LocalDate.of(2000, 12, 30),
                Prioridade.ALTA);

        List<Tarefa> tarefinhas = gerenciadorDeTarefas.getAllTarefas();
        Assertions.assertEquals(tarefinhas, tarefas);
    }

}