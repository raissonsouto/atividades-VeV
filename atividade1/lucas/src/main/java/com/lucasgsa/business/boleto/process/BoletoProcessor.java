package com.lucasgsa.business.boleto.process;

import com.lucasgsa.entity.boleto.Boleto;
import com.lucasgsa.entity.fatura.Fatura;
import com.lucasgsa.entity.pagamento.Pagamento;

import java.util.Collection;
import java.util.List;

public class BoletoProcessor {

    private final Fatura fatura;

    public BoletoProcessor(Fatura fatura) {
        this.fatura = fatura;
    }

    public void process(Collection<Boleto> boletos) {
        addBoletosToFatura(boletos);
        Double totalPaid = getFaturaTotalPaid();

        if (totalPaid >= fatura.getValue()) {
            fatura.setStatusToPaid();
        }
    }

    private void addBoletosToFatura(Collection<Boleto> boletos) {
        for (Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto);
            fatura.addPagamento(pagamento);
        }
    }

    private Double getFaturaTotalPaid() {
        Double totalPaid = 0.0;

        for (Pagamento pagamento : fatura.getPagamentos()) {
            totalPaid += pagamento.getValue();
        }

        return totalPaid;
    }

}
