package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GerenciadorDeTarefasTests {
    @Test
    public void testCriarNovaTarefa() {
        String titulo = "";
        String descricao = "";
        String dataDeVencimento = "";
        String prioridade = "";
        try {
            GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas(
                    titulo,
                    descricao,
                    dataDeVencimento,
                    prioridade);

            assertTrue(true);
        } catch (Exception e) {
            fail("Teste falhou pois classe não foi criada com sucesso.");
        }
    }
}