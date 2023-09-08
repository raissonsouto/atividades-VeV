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

class BoletoProcessorDecisionTableTest {

    private static final String CUSTOMER_NAME = "Alfredo";
    private static final LocalDate DATE_1 = LocalDate.of(2023, 8, 5);

    @Test
    @DisplayName("Boletos values bigger than fatura value, no invalid values and not zero fatura.")
    void boletosValuesBiggerThanFaturaValueNoInvalidValuesAndNotZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(82.3, 88.3);

        Fatura fatura = makeFatura(98.2);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(82.3, 88.3));
    }

    @Test
    @DisplayName("Boletos values lower than fatura value, has invalid values and not zero fatura.")
    void boletosValuesLowerThanFaturaValueHasInvalidValuesAndNotZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(82.3, -88.3);

        Fatura fatura = makeFatura(10000.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        Assertions.assertThrows(Exception.class, () -> boletoProcessor.process(boletos));

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Boletos values bigger than fatura value, has invalid values and not zero fatura.")
    void boletosValuesBiggerThanFaturaValueHasInvalidValuesAndNotZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(82.3, 88.3, -1.00);

        Fatura fatura = makeFatura(100.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        Assertions.assertThrows(Exception.class, () -> boletoProcessor.process(boletos));

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Boletos values lower than fatura value, no invalid values and not zero fatura.")
    void boletosValuesLowerThanFaturaValueNoInvalidValuesAndNotZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(82.3, 88.3);

        Fatura fatura = makeFatura(1000.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPending(fatura);
        assertPagamentosOfFatura(fatura, List.of(82.3, 88.3));
    }

    @Test
    @DisplayName("Boletos values bigger than fatura value, no invalid values and zero fatura.")
    void boletosValuesBiggerThanFaturaValueNoInvalidValuesAndZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(82.3, 88.3);

        Fatura fatura = makeFatura(0.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(82.3, 88.3));
    }

    @Test
    @DisplayName("Boletos values lower than fatura value, has invalid values and zero fatura.")
    void boletosValuesLowerThanFaturaValueHasInvalidValuesAndZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(-2.32);

        Fatura fatura = makeFatura(0.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        Assertions.assertThrows(Exception.class, () -> boletoProcessor.process(boletos));

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Boletos values bigger than fatura value, has invalid values and zero fatura.")
    void boletosValuesBiggerThanFaturaValueHasInvalidValuesAndZeroFatura() {
        List<Boleto> boletos = makeBoletosByValues(1923.00, -2.32);

        Fatura fatura = makeFatura(0.00);
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
