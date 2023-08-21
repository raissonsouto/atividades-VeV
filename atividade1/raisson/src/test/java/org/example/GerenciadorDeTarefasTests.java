package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefasTests {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();

    @BeforeEach
    public void setGerenciadorDeTarefas() {
        String titulo = "Tarefa 0";
        String descricao = "Tarefa exemplo.";
        LocalDate dataDeVencimento = LocalDate.of(2023, 10, 31);
        Prioridade prioridade = Prioridade.BAIXA;

        gerenciadorDeTarefas.criarTarefa(
                titulo,
                descricao,
                dataDeVencimento,
                prioridade
        );
    }

    @Test
    public void testCriarNovaTarefa() {
        try {
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

            Assertions.assertTrue(true);

        } catch (Exception e) {
            Assertions.fail("Teste falhou pois classe não foi criada com sucesso.");
        }
    }

    @Test
    public void testUpdateTitulo() {
        String titulo = "Trocando o título.";
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setTitulo(titulo);

        Assertions.assertEquals(titulo, tarefa.getTitulo());
    }

    @Test
    public void testUpdateDescricao() {
        String descricao = "Trocando a descrição.";

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setDescricao(descricao);

        Assertions.assertEquals(descricao, tarefa.getDescricao());
    }

    @Test
    public void testUpdateDataDeVencimento() {
        LocalDate dataDeVencimento = LocalDate.of(1, 1, 1);

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setDataDeVencimento(dataDeVencimento);

        Assertions.assertEquals(dataDeVencimento, tarefa.getDataDeVencimento());
    }

    @Test
    public void testDefinirPrioridadeAlta() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setPrioridade(Prioridade.ALTA);

        Assertions.assertEquals(Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeMedia() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setPrioridade(Prioridade.MEDIA);

        Assertions.assertEquals(Prioridade.MEDIA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeBaixa() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 0");
        tarefa.setPrioridade(Prioridade.BAIXA);

        Assertions.assertEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }

    @Test
    public void testDeleteTarefa() {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa("Tarefa 0");
        int novoTamanhoDaLista = gerenciadorDeTarefas.length();

        Assertions.assertEquals(tamanhoDaLista - 1, novoTamanhoDaLista);
    }

    @Test
    public void testGetAllTarefas() {
        Tarefa tarefa1 = new Tarefa(
                "Tarefa 1",
                "12341234",
                LocalDate.of(23452,12,30),
                Prioridade.BAIXA
        );
        Tarefa tarefa2 = new Tarefa(
                "Tarefa 2",
                "1234",
                LocalDate.of(2010,12,10),
                Prioridade.MEDIA
        );
        Tarefa tarefa3 = new Tarefa(
                "Tarefa 3",
                "123412341234",
                LocalDate.of(2000,12,30),
                Prioridade.ALTA
        );

        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);
        tarefas.add(tarefa3);

        List<Tarefa> tarefinhas = gerenciadorDeTarefas.getAllTarefas();
        Assertions.assertEquals(tarefinhas, tarefas);
    }

}