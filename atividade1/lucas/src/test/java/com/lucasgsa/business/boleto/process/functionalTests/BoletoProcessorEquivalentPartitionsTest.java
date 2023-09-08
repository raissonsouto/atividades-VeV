package com.lucasgsa.business.boleto.process.functionalTests;

import com.lucasgsa.business.boleto.process.BoletoProcessor;
import com.lucasgsa.entity.boleto.Boleto;
import com.lucasgsa.entity.fatura.Fatura;
import com.lucasgsa.entity.fatura.enums.FaturaStatusEnum;
import com.lucasgsa.entity.pagamento.Pagamento;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class BoletoProcessorEquivalentPartitionsTest {

    private static final String CUSTOMER_NAME = "Alfredo";
    private static final LocalDate DATE_1 = LocalDate.of(2023, 8, 5);

    @Test
    @DisplayName("Fatura with value zero, must be consider paid.")
    void faturaWithValueZeroMustBeConsiderPaid() {
        List<Boleto> boletos = new ArrayList<>();

        Fatura fatura = makeFatura(0.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Boletos values bigger than fatura value")
    void boletosValuesBiggerThanFaturaValue() {
        List<Boleto> boletos = makeBoletosByValues(1787.44, 2332.11);

        Fatura fatura = makeFatura(3123.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(1787.44, 2332.11));
    }

    @Test
    @DisplayName("Boletos values lower than fatura value")
    void boletosValuesLowerThanFaturaValue() {
        List<Boleto> boletos = makeBoletosByValues(124.94, 932.32);

        Fatura fatura = makeFatura(5442.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPending(fatura);
        assertPagamentosOfFatura(fatura, List.of(124.94, 932.32));
    }

    @Test
    @DisplayName("Invalid boletos values must not be processed")
    void invalidBoletosValuesMustNotBeProcessed() {
        List<Boleto> boletos = makeBoletosByValues(11.2, -832.32, 32.2);

        Fatura fatura = makeFatura(150.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        Assertions.assertThrows(Exception.class, () -> boletoProcessor.process(boletos));

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    private static List<Boleto> makeBoletosByValues(Double... values) {
        List<Boleto> boletos = new ArrayList<>();

        for (Double value : values) {
            Boleto boleto =  makeBoleto(value, DATE_1);
            boletos.add(boleto);
        }

        return boletos;
    }

    private static Boleto makeBoleto(Double value, LocalDate date) {
        String code = RandomStringUtils.randomAlphanumeric(50);
        return new Boleto(code, date, value);
    }

    private static Fatura makeFatura(Double value) {
        LocalDate date = LocalDate.of(2023, 8, 20);
        return new Fatura(CUSTOMER_NAME, date, value);
    }

    private static void assertFaturaIsPaid(Fatura fatura) {
        Assertions.assertEquals(FaturaStatusEnum.PAID, fatura.getStatus());
    }

    private static void assertFaturaIsPending(Fatura fatura) {
        Assertions.assertEquals(FaturaStatusEnum.PENDING, fatura.getStatus());
    }

    private void assertFaturaHasNoPagamentos(Fatura fatura) {
        Assertions.assertEquals(0, fatura.getPagamentos().size());
    }

    private void assertPagamentosOfFatura(Fatura fatura, List<Double> expectedPagamentos) {
        List<Double> pagamentos = fatura.getPagamentos().stream().map(Pagamento::getValue).toList();
        Assertions.assertEquals(expectedPagamentos, pagamentos);
    }


}
