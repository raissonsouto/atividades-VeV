package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TarefaTests {

    private Tarefa tarefa;
    private final String tituloBase = "Lavar louca";
    private final String tituloMaximo = "Realizar Reunião de Planejamento para o Projeto de Desenvolvimento de Software.";
    private final String tituloExcedente = "Realizar Reunião de Planejamento para o Projeto de Desenvolvimento de Software...";
    private final String descricaoBase = "Descrição genérica";
    private final String descricaoMaxima = "Lavar louça envolve o processo de enxaguar pratos, talheres e panelas com água e sabão," +
            " esfregar com uma bucha para remover a sujeira e impurezas, enxaguar novamente em água " +
            "limpa e deixar secar ou secar com um pano limpo. Após isso, basta guardar em";
    private final String descricaoExcedente = "Lavar louça envolve o processo de enxaguar pratos, talheres e panelas com água e sabão," +
            " esfregar com uma bucha para remover a sujeira e impurezas, enxaguar novamente em água " +
            "limpa e deixar secar ou secar com um pano limpo. Após isso, basta guardar em local adequado.";
    private final LocalDate dataBase = LocalDate.of(2030, 12, 12);
    private final LocalDate yesterday = LocalDate.now().minusDays(1);
    private final LocalDate today = LocalDate.now();
    private final Prioridade prioridadeBase = Prioridade.MEDIA;

    @BeforeEach
    public void testCriarTarefaCasoBase() throws Exception {
        tarefa = new Tarefa(tituloBase, descricaoBase, dataBase, prioridadeBase);
    }

    @Test
    public void testCriarTarefaComTituloNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa = new Tarefa(
                    null,
                    descricaoBase,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComTituloVazio() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa = new Tarefa(
                    "",
                    descricaoBase,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComTitulo1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    "a",
                    descricaoBase,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComTituloMaximo() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloMaximo,
                    descricaoBase,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComTituloExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa = new Tarefa(
                    tituloExcedente,
                    descricaoBase,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDescricaoNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    null,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDescricaoVazia() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    "",
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDescricao1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloBase,
                    "a",
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDescricaoMaxima() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoMaxima,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDescricaoExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoExcedente,
                    dataBase,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDataNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    null,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDataPraOntem() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    yesterday,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComDataPraHoje() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    today,
                    prioridadeBase);
        });
    }

    @Test
    public void testCriarTarefaComPrioridadeNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    dataBase,
                    null);
        });
    }

    @Test
    public void testCriarTarefaComPrioridadeAlta() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    dataBase,
                    Prioridade.ALTA);
        });
    }

    @Test
    public void testCriarTarefaComPrioridadeBaixa() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa = new Tarefa(
                    tituloBase,
                    descricaoBase,
                    dataBase,
                    Prioridade.BAIXA);
        });
    }

    @Test
    public void testUpdateTituloNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setTitulo(null);
        });
    }

    @Test
    public void testUpdateTituloVazio() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setTitulo("");
        });
    }

    @Test
    public void testUpdateTitulo1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setTitulo("a");
        });
    }

    @Test
    public void testUpdateTituloBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setTitulo(tituloBase);
        });
    }

    @Test
    public void testUpdateTituloMaximo() throws Exception {
        String oldTitulo = tarefa.getTitulo();
        tarefa.setTitulo(tituloMaximo);
        Assertions.assertNotEquals(oldTitulo, tarefa.getTitulo());
    }

    @Test
    public void testUpdateTituloExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setTitulo(tituloExcedente);
        });
    }

    @Test
    public void testUpdateDescricaoNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setDescricao(null);
        });
    }

    @Test
    public void testUpdateDescricaoVazio() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDescricao("");
        });
    }

    @Test
    public void testUpdateDescricao1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDescricao("a");
        });
    }

    @Test
    public void testUpdateDescricaoBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDescricao(descricaoBase);
        });
    }

    @Test
    public void testUpdateDescricaoMaxima() throws Exception {
        String oldDescricao = tarefa.getDescricao();
        tarefa.setDescricao(descricaoMaxima);
        Assertions.assertNotEquals(oldDescricao, tarefa.getDescricao());
    }

    @Test
    public void testUpdateDescricaoExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDescricao(descricaoExcedente);
        });
    }

    @Test
    public void testUpdateDataNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setDataDeVencimento(null);
        });
    }

    @Test
    public void testUpdateDataYesterday() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDataDeVencimento(yesterday);
        });
    }

    @Test
    public void testUpdateDataToday() throws Exception {
        LocalDate oldDate = tarefa.getDataDeVencimento();
        tarefa.setDataDeVencimento(today);
        Assertions.assertNotEquals(oldDate, tarefa.getDataDeVencimento());
    }

    @Test
    public void testUpdateDataBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDataDeVencimento(dataBase);
        });
    }

    @Test
    public void testUpdatePrioridadeNull() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDataDeVencimento(null);
        });
    }

    @Test
    public void testUpdatePrioridadeBaixa() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setPrioridade(Prioridade.BAIXA);
        });
    }

    @Test
    public void testUpdatePrioridadeMedia() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setPrioridade(Prioridade.MEDIA);
        });
    }

    @Test
    public void testUpdatePrioridadeAlta() {
        Prioridade oldPrioridade = tarefa.getPrioridade();
        tarefa.setPrioridade(Prioridade.ALTA);
        Assertions.assertNotEquals(oldPrioridade, tarefa.getPrioridade());
    }
}