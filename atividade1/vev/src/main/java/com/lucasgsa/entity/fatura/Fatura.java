package com.lucasgsa.entity.fatura;

import com.lucasgsa.entity.fatura.enums.FaturaStatusEnum;
import com.lucasgsa.entity.pagamento.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Fatura {
    public Fatura(String customerName, LocalDate date, BigDecimal value) {
    }

    public List<Pagamento> getPagamentos() {
        return null;
    }

    public FaturaStatusEnum getStatus() {
        return null;
    }
}
