package com.lucasgsa.entity.fatura;

import com.lucasgsa.entity.fatura.enums.FaturaStatusEnum;
import com.lucasgsa.entity.pagamento.Pagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fatura {
    private final String customerName;
    private final LocalDate date;
    private final Double value;

    private final List<Pagamento> pagamentos;
    private FaturaStatusEnum status;

    public Fatura(String customerName, LocalDate date, Double value) {
        this.customerName = customerName;
        this.date = date;
        this.value = value;
        this.pagamentos = new ArrayList<>();
        this.status = FaturaStatusEnum.PENDING;
    }

    public void addPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }

    public void setStatusToPaid() {
        this.status = FaturaStatusEnum.PAID;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public FaturaStatusEnum getStatus() {
        return status;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return Objects.equals(customerName, fatura.customerName) && Objects.equals(date, fatura.date) && Objects.equals(value, fatura.value) && Objects.equals(pagamentos, fatura.pagamentos) && status == fatura.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, date, value, pagamentos, status);
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "customerName='" + customerName + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", pagamentos=" + pagamentos +
                ", status=" + status +
                '}';
    }

}
