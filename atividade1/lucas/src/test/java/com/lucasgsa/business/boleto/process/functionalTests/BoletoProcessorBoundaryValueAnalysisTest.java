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

class BoletoProcessorBoundaryValueAnalysisTest {

    private static final String CUSTOMER_NAME = "Alfredo";
    private static final LocalDate DATE_1 = LocalDate.of(2023, 8, 5);

    @Test
    @DisplayName("Empty boletos list")
    void emptyBoletosList() {
        List<Boleto> boletos = new ArrayList<>();

        Fatura fatura = makeFatura(1.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Empty boletos list but with fatura zero")
    void emptyBoletosListButWithFaturaZero() {
        List<Boleto> boletos = new ArrayList<>();

        Fatura fatura = makeFatura(0.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Boletos values equals the minimum")
    void boletosValuesEqualsTheMinimum() {
        List<Boleto> boletos = makeBoletosByValues(1.50 , 0.50);

        Fatura fatura = makeFatura(2.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(1.50, 0.50));
    }

    @Test
    @DisplayName("Boletos values little bit lower than the minimum")
    void boletosValuesLittleBitLowerThanTheMinimum() {
        List<Boleto> boletos = makeBoletosByValues(0.44 , 0.55);

        Fatura fatura = makeFatura(1.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPending(fatura);
        assertPagamentosOfFatura(fatura, List.of(0.44 , 0.55));
    }

    @Test
    @DisplayName("Boletos values little bit bigger than the minimum")
    void boletosValuesLittleBitBiggerThanTheMinimum() {
        List<Boleto> boletos = makeBoletosByValues(0.44 , 0.57);

        Fatura fatura = makeFatura(1.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(0.44 , 0.57));
    }

    @Test
    @DisplayName("Boletos values bigger than the minimum")
    void boletosValuesBiggerThanTheMinimum() {
        List<Boleto> boletos = makeBoletosByValues(200.38);

        Fatura fatura = makeFatura(150.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaIsPaid(fatura);
        assertPagamentosOfFatura(fatura, List.of(200.38));
    }


    @Test
    @DisplayName("Negative boleto values")
    void negativeBoletoValues() {
        List<Boleto> boletos = makeBoletosByValues(100.00, -76.34);

        Fatura fatura = makeFatura(95.00);
        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        Assertions.assertThrows(Exception.class, () -> boletoProcessor.process(boletos));

        assertFaturaIsPending(fatura);
        assertFaturaHasNoPagamentos(fatura);
    }

    @Test
    @DisplayName("Null boleto values")
    void nullBoletoValues() {
        List<Boleto> boletos = makeBoletosByValues(null, 102.33);

        Fatura fatura = makeFatura(1.00);
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
