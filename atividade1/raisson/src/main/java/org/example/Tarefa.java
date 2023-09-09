package org.example;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataDeVencimento;
    private Prioridade prioridade;

    public Tarefa(String titulo, String descricao, LocalDate dataDeVencimento, Prioridade prioridade) throws Exception {
        if (titulo == null)
            throw new NullPointerException("titulo cannot be null");
        if (titulo.isEmpty())
            throw new Exception("titulo cannot be empty");
        if (titulo.length() > 80)
            throw new Exception("titulo cannot be greater than 80 chars");
        if (descricao == null)
            throw new NullPointerException("descricao cannot be null");
        if (descricao.isEmpty())
            throw new Exception("descricao cannot be empty");
        if (descricao.length() > 255)
            throw new Exception("descricao cannot be greater than 255 chars");
        if (dataDeVencimento == null)
            throw new NullPointerException("data de vencimento cannot be null");
        if (prioridade == null)
            throw new NullPointerException("prioridade cannot be null");
        if (dataDeVencimento.isBefore(LocalDate.now()))
            throw new Exception("data de vencimento cannot be before the current day");

        this.titulo = titulo;
        this.descricao = descricao;
        this.dataDeVencimento = dataDeVencimento;
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if (titulo == null)
            throw new NullPointerException("titulo cannot be null");
        if (titulo.isEmpty())
            throw new Exception("titulo cannot be empty");
        if (titulo.length() > 80)
            throw new Exception("titulo cannot be greater than 80 chars");

        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws Exception {
        if (descricao == null)
            throw new NullPointerException("descricao cannot be null");
        if (descricao.isEmpty())
            throw new Exception("descricao cannot be empty");
        if (descricao.length() > 255)
            throw new Exception("descricao cannot be greater than 255 chars");

        this.descricao = descricao;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) throws Exception {
        if (dataDeVencimento == null)
            throw new NullPointerException("data de vencimento cannot be null");
        if (dataDeVencimento.isBefore(LocalDate.now()))
            throw new Exception("data de vencimento cannot be before the current day");

        this.dataDeVencimento = dataDeVencimento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        if (prioridade == null) {
            throw new NullPointerException("prioridade cannot be null");
        }

        this.prioridade = prioridade;
    }
}
