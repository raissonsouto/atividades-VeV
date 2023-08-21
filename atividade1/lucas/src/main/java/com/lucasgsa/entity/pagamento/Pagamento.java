package com.lucasgsa.entity.pagamento;

import com.lucasgsa.entity.boleto.Boleto;
import com.lucasgsa.entity.pagamento.enums.PaymentTypeEnum;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamento {
    private final Double value;
    private final LocalDate date;
    private final PaymentTypeEnum type;

    public Pagamento(Double value, LocalDate date, PaymentTypeEnum type) {
        this.value = value;
        this.date = date;
        this.type = type;
    }

    public Pagamento(Boleto boleto) {
        this.type = PaymentTypeEnum.BOLETO;
        this.date = boleto.getDate();
        this.value = boleto.getValue();
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(value, pagamento.value) && Objects.equals(date, pagamento.date) && type == pagamento.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date, type);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "value=" + value +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

}
