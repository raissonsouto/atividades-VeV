package com.lucasgsa.business.boleto.process;

import com.lucasgsa.entity.boleto.Boleto;
import com.lucasgsa.entity.fatura.Fatura;
import com.lucasgsa.entity.fatura.enums.FaturaStatusEnum;
import com.lucasgsa.entity.pagamento.Pagamento;
import com.lucasgsa.entity.pagamento.enums.PaymentTypeEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

class BoletoProcessorTest {

    private static final String CUSTOMER_NAME = "Alfredo";
    private static final LocalDate DATE_1 = LocalDate.of(2023, 8, 5);
    private static final LocalDate DATE_2 = LocalDate.of(2023, 8, 8);
    private static final LocalDate DATE_3 = LocalDate.of(2023, 8, 13);

    @ParameterizedTest
    @MethodSource
    @DisplayName("The fatura should be marked as paid when boletos are equals or bigger than fatura value.")
    void shouldMarkFaturaAsPaid(Double faturaValue, List<Double> boletosValues) {
        Fatura fatura = makeFatura(BigDecimal.valueOf(faturaValue));
        List<Boleto> boletos = boletosValues.stream().map(value -> makeBoleto(BigDecimal.valueOf(value), DATE_1)).toList();

        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaPaid(fatura);
    }

    static Stream<Arguments> shouldMarkFaturaAsPaid() {
        return Stream.of(
                Arguments.of(1500, List.of(600, 350, 550)),
                Arguments.of(100, List.of(99, 1)),
                Arguments.of(5, List.of(3.6, 1.4)),
                Arguments.of(0, List.of()));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("The fatura should continue pending when boletos not enough to pay the fatura.")
    void shouldKeepFaturaAsPending(Double faturaValue, List<Double> boletosValues) {
        Fatura fatura = makeFatura(BigDecimal.valueOf(faturaValue));
        List<Boleto> boletos = boletosValues.stream().map(value -> makeBoleto(value, DATE_1)).toList();

        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        assertFaturaPending(fatura);
    }

    static Stream<Arguments> shouldKeepFaturaAsPending() {
        return Stream.of(
                Arguments.of(1500, List.of(600, 350, 549)),
                Arguments.of(100, List.of(99, 0)),
                Arguments.of(100, List.of(99, 0.987654321)),
                Arguments.of(1, List.of()));
    }

    @Test
    void shouldCreatePagamentosCorrectly() {
        Fatura fatura = makeFatura(BigDecimal.valueOf(100));

        List<Boleto> boletos = List.of(
                makeBoleto(10.0, DATE_1),
                makeBoleto(11.0, DATE_2),
                makeBoleto(12.0, DATE_3));

        BoletoProcessor boletoProcessor = new BoletoProcessor(fatura);
        boletoProcessor.process(boletos);

        List<Pagamento> expectedPagamentos = List.of(
                new Pagamento(10.0, DATE_1, PaymentTypeEnum.BOLETO),
                new Pagamento(11.0, DATE_2, PaymentTypeEnum.BOLETO),
                new Pagamento(12.0, DATE_3, PaymentTypeEnum.BOLETO)
        );

        Assertions.assertEquals(expectedPagamentos, fatura.getPagamentos());
    }

    private static void assertFaturaPaid(Fatura fatura) {
        Assertions.assertEquals(FaturaStatusEnum.PAID, fatura.getStatus());
    }

    private static void assertFaturaPending(Fatura fatura) {
        Assertions.assertEquals(FaturaStatusEnum.PENDING, fatura.getStatus());
    }

    private static Boleto makeBoleto(Double value, LocalDate date) {
        return makeBoleto(BigDecimal.valueOf(value), date);
    }

    private static Boleto makeBoleto(BigDecimal value, LocalDate date) {
        String code = RandomStringUtils.randomAlphanumeric(50);
        return new Boleto(code, date, value);
    }

    private static Fatura makeFatura(BigDecimal value) {
        LocalDate date = LocalDate.of(2023, 8, 20);
        return new Fatura(CUSTOMER_NAME, date, value);
    }

}
