package org.example.junit5Tests;

import org.example.Prioridade;
import org.example.Tarefa;
import org.junit.jupiter.api.*;

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
    @DisplayName("Cria tarefa com valores comuns")
    public void testCriarTarefaCasoBase() throws Exception {
        tarefa = new Tarefa(tituloBase, descricaoBase, dataBase, prioridadeBase);
    }

    @Test
    @Tag("TestNullValue")
    @DisplayName("Cria tarefa com valores comuns e titulo nulo")
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
    @DisplayName("Cria tarefa com valores comuns e titulo vazio")
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
    @DisplayName("Cria tarefa com valores comuns e titulo minimo")
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
    @DisplayName("Cria tarefa com valores comuns e titulo maior que o limite")
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
    @DisplayName("Cria tarefa com valores comuns e titulo maior que o limite")
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
    @Tag("TestNullValue")
    @DisplayName("Cria tarefa com valores comuns e descrição nula")
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
    @DisplayName("Cria tarefa com valores comuns e descrição vazia")
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
    @DisplayName("Cria tarefa com valores comuns e descrição com tamanho mínimo")
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
    @DisplayName("Cria tarefa com valores comuns e descrição com tamanho máximo")
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
    @DisplayName("Cria tarefa com valores comuns e descrição maior que o tamanho máximo")
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
    @Tag("TestNullValue")
    @DisplayName("Cria tarefa com valores comuns e data nula")
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
    @DisplayName("Cria tarefa com valores comuns e data para ontem")
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
    @DisplayName("Cria tarefa com valores comuns e data para hoje")
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
    @Tag("TestNullValue")
    @DisplayName("Cria tarefa com valores comuns e prioridade nula")
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
    @DisplayName("Cria tarefa com valores comuns e prioridade alta")
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
    @DisplayName("Cria tarefa com valores comuns e prioridade baixa")
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
    @Tag("TestNullValue")
    @DisplayName("Testa .setTitulo() com descrição nula")
    public void testUpdateTituloNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setTitulo(null);
        });
    }

    @Test
    @DisplayName("Testa .setTitulo() com descrição vazia")
    public void testUpdateTituloVazio() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setTitulo("");
        });
    }

    @Test
    @DisplayName("Testa .setTitulo() com descrição de tamanho mínimo")
    public void testUpdateTitulo1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setTitulo("a");
        });
    }

    @Test
    @DisplayName("Testa .setTitulo() com descrição comum")
    public void testUpdateTituloBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setTitulo(tituloBase);
        });
    }

    @Test
    @DisplayName("Testa .setTitulo() com descrição no tamanho máximo permitido")
    public void testUpdateTituloMaximo() throws Exception {
        String oldTitulo = tarefa.getTitulo();
        tarefa.setTitulo(tituloMaximo);
        Assertions.assertNotEquals(oldTitulo, tarefa.getTitulo());
    }

    @Test
    @DisplayName("Testa .setTitulo() com descrição maior que o máximo permitido")
    public void testUpdateTituloExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setTitulo(tituloExcedente);
        });
    }

    @Test
    @Tag("TestNullValue")
    @DisplayName("Testa .setDescricao() com descrição nula")
    public void testUpdateDescricaoNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setDescricao(null);
        });
    }

    @Test
    @DisplayName("Testa .setDescricao() com descrição menor que o permitido")
    public void testUpdateDescricaoVazio() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDescricao("");
        });
    }

    @Test
    @DisplayName("Testa .setDescricao() com descrição no tamanho mínimo")
    public void testUpdateDescricao1Char() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDescricao("a");
        });
    }

    @Test
    @DisplayName("Testa .setDescricao() com descriçãonum tamanho ok")
    public void testUpdateDescricaoBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDescricao(descricaoBase);
        });
    }

    @Test
    @DisplayName("Testa .setDescricao() com descrição com tamanho máximo permitido")
    public void testUpdateDescricaoMaxima() throws Exception {
        String oldDescricao = tarefa.getDescricao();
        tarefa.setDescricao(descricaoMaxima);
        Assertions.assertNotEquals(oldDescricao, tarefa.getDescricao());
    }

    @Test
    @DisplayName("Testa .setDescricao() com descrição maior que o máximo permitido")
    public void testUpdateDescricaoExcedente() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDescricao(descricaoExcedente);
        });
    }

    @Test
    @Tag("TestNullValue")
    @DisplayName("Testa .setDataDeVencimento() com a data nula")
    public void testUpdateDataNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setDataDeVencimento(null);
        });
    }

    @Test
    @DisplayName("Testa .setDataDeVencimento() com a data do dia anterior que o teste foi executado")
    public void testUpdateDataYesterday() {
        Assertions.assertThrows(Exception.class, () -> {
            tarefa.setDataDeVencimento(yesterday);
        });
    }

    @Test
    @DisplayName("Testa .setDataDeVencimento() com a data do dia que o teste foi executado")
    public void testUpdateDataToday() throws Exception {
        LocalDate oldDate = tarefa.getDataDeVencimento();
        tarefa.setDataDeVencimento(today);
        Assertions.assertNotEquals(oldDate, tarefa.getDataDeVencimento());
    }

    @Test
    @DisplayName("Testa .setDataDeVencimento() com data num futuro longe")
    public void testUpdateDataBase() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setDataDeVencimento(dataBase);
        });
    }

    @Test
    @Tag("TestNullValue")
    @DisplayName("Testa .setPrioridade() com valor nulo")
    public void testUpdatePrioridadeNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tarefa.setPrioridade(null);
        });
    }

    @Test
    @DisplayName("Testa .setPrioridade() com valor Prioridade.BAIXA")
    public void testUpdatePrioridadeBaixa() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setPrioridade(Prioridade.BAIXA);
        });
    }

    @Test
    @DisplayName("Testa .setPrioridade() com valor Prioridade.MEDIA")
    public void testUpdatePrioridadeMedia() {
        Assertions.assertDoesNotThrow(() -> {
            tarefa.setPrioridade(Prioridade.MEDIA);
        });
    }

    @Test
    @DisplayName("Testa .setPrioridade() com valor Prioridade.ALTA")
    public void testUpdatePrioridadeAlta() {
        Prioridade oldPrioridade = tarefa.getPrioridade();
        tarefa.setPrioridade(Prioridade.ALTA);
        Assertions.assertNotEquals(oldPrioridade, tarefa.getPrioridade());
    }
}