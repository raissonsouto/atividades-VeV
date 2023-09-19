package org.example;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
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
    @DisplayName("Testa atualizar tarefa valida")
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
    @DisplayName("Testa criar tarefa com identificador previamente utilizado")
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
    @DisplayName("Testa atualizar tarefa com identificador reconhecido")
    public void testUpdateTarefaValida() {
        Assertions.assertDoesNotThrow(() -> {
            gerenciadorDeTarefas.getTarefa(tituloBase).setDescricao("a");
        });
    }

    @Test
    @DisplayName("Testa atualizar tarefa com identificador não reconhecido")
    public void testUpdateTarefaInexistente() {
        Assertions.assertThrows(Exception.class, () -> {
            gerenciadorDeTarefas.getTarefa("Gol").setDescricao("a");
        });
    }

    @NewTest
    @Tag("TestNullValue")
    @DisplayName("Testa atualizar tarefa com identificador nulo")
    public void testUpdateTarefaNula() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            gerenciadorDeTarefas.getTarefa(null).setDescricao("a");
        });
    }

    @Test
    @DisplayName("Testa deletar tarefa com identificador valido")
    public void testDeleteTarefaValida() throws Exception {
        int tamanhoDaLista = gerenciadorDeTarefas.length();
        gerenciadorDeTarefas.deleteTarefa(tituloBase);

        int novoTamanhoDaLista = gerenciadorDeTarefas.length();
        int expectedTamanhoDaLista = tamanhoDaLista - 1;

        Assertions.assertEquals(expectedTamanhoDaLista, novoTamanhoDaLista);
    }

    @Test
    @DisplayName("Testa deletar tarefa com identificador não reconhecido")
    public void testDeleteTarefaInexistente() {
        Assertions.assertThrows(Exception.class, () -> {
            gerenciadorDeTarefas.deleteTarefa("Gol");
        });
    }

    @NewTest
    @Tag("TestNullValue")
    @DisplayName("Testa deletar tarefa com idenficador null")
    public void testDeleteTarefaNula() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            gerenciadorDeTarefas.deleteTarefa(null);
        });
    }

    @Test
    @DisplayName("Get lista com todas as tarefas")
    public void testGetAllTarefas() throws Exception {
        gerenciadorDeTarefas.criarTarefa("Tarefa 1", descricaoBase, dataBase, prioridadeBase);
        gerenciadorDeTarefas.criarTarefa("Tarefa 2", descricaoBase, dataBase, prioridadeBase);

        List<Tarefa> tarefas = gerenciadorDeTarefas.getAllTarefas();
        Assertions.assertEquals(tarefas.size(), 3);
    }

}