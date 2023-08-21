package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GerenciadorDeTarefasTests {

    GerenciadorDeTarefas gerenciadorDeTarefas;

    @BeforeAll
    public static void setup() {
        GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();
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
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Tarefa 1");
        tarefa.setTitulo(titulo);

        Assertions.assertEquals(titulo, tarefa.getTitulo());
    }

    @Test
    public void testUpdateDescricao() {
        String descricao = "Trocando a descrição.";

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Trocando o título.");
        tarefa.setDescricao(descricao);

        Assertions.assertEquals(descricao, tarefa.getDescricao());
    }

    @Test
    public void testUpdateDataDeVencimento() {
        String dataDeVencimento = "1/1/999";

        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Trocando o título.");
        tarefa.setDataDeVencimento(dataDeVencimento);

        Assertions.assertEquals(dataDeVencimento, tarefa.getDataDeVencimento());
    }

    @Test
    public void testDefinirPrioridadeAlta() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Trocando o título.");
        tarefa.setPrioridade(Prioridade.ALTA);

        Assertions.assertNotEquals(Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeMedia() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Trocando o título.");
        tarefa.setPrioridade(Prioridade.MEDIA);

        Assertions.assertNotEquals(Prioridade.MEDIA, tarefa.getPrioridade());
    }

    @Test
    public void testDefinirPrioridadeBaixa() {
        Tarefa tarefa = gerenciadorDeTarefas.getTarefa("Trocando o título.");
        tarefa.setPrioridade(Prioridade.BAIXA);

        Assertions.assertNotEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }

    @Test
    public void testDeleteTarefa() {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa("Trocando o título.");
        int novoTamanhoDaLista = gerenciadorDeTarefas.length();

        Assertions.assertEquals(tamanhoDaLista - 1, novoTamanhoDaLista);
    }

    @Test
    public void testGetAllTarefas() {
        Assertions.assertEquals("", gerenciadorDeTarefas.getAllTarefas());
    }

}