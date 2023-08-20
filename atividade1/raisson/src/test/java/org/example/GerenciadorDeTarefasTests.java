package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GerenciadorDeTarefasTests {

    @BeforeAll
    public static void createGerenciador() {
        GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();
    }

    @Test
    public void testCriarNovaTarefa() {
        try {
            String titulo = "";
            String descricao = "";
            String dataDeVencimento = "";
            String prioridade = "";

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
        String titulo = "";
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setTitulo(titulo);

        Assertions.assertEquals(titulo, tarefa.getTitulo());
    }

    @Test
    public void testUpdateDescricao() {
        String descricao = "";

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setDescricao(descricao);

        Assertions.assertEquals(descricao, tarefa.getDescricao());
    }

    @Test
    public void testUpdateDataDeVencimento() {
        String dataDeVencimento = "";

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setDataDeVencimento(dataDeVencimento);

        Assertions.assertEquals(dataDeVencimento, tarefa.getDataDeVencimento());
    }

    @Test
    public void testDefinirPrioridadeAlta() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setPrioridade(Prioridade.ALTA);

        Assertions.assertNotEquals(Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeMedia() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setPrioridade(Prioridade.MEDIA);

        Assertions.assertNotEquals(Prioridade.MEDIA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeBaixa() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa(titulo);
        tarefa.setPrioridade(Prioridade.BAIXA);

        Assertions.assertNotEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }

    @Test
    public void testDeleteTarefa() {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa(titulo);
        int novoTamanhoDaLista = gerenciadorDeTarefas.length();

        Assertions.assertEquals(tamanhoDaLista - 1, novoTamanhoDaLista);
    }

    @Test
    public void testGetAllTarefas() {
        Assertions.assertEquals("", gerenciadorDeTarefas.getAllTarefas()); // nao consigo imaginar o formato de saida
    }

}