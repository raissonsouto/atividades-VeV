package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GerenciadorDeTarefasTests {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();

    @BeforeEach
    public void setGerenciadorDeTarefas() {
        String titulo = "Tarefa 0";
        String descricao = "Tarefa exemplo.";
        String dataDeVencimento = "31/10/2023";
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
            String dataDeVencimento = "24/10/2023";
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
        String dataDeVencimento = "1/1/999";

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
        List<Tarefa> tarefas = gerenciadorDeTarefas.getAllTarefas();
        Assertions.assertTrue(tarefas instanceof List);
        for (Tarefa tarefa : tarefas) {
            Assertions.assertTrue(tarefa instanceof Tarefa);
        }
    }

}