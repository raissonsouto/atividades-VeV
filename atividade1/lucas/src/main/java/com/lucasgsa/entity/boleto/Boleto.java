package com.lucasgsa.entity.boleto;

import java.time.LocalDate;
import java.util.Objects;

public class Boleto {
    private final String code;
    private final LocalDate date;
    private final Double value;

    public Boleto(String code, LocalDate date, Double value) {
        this.code = code;
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boleto boleto = (Boleto) o;
        return Objects.equals(code, boleto.code) && Objects.equals(date, boleto.date) && Objects.equals(value, boleto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, date, value);
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", value=" + value +
                '}';
    }

}
