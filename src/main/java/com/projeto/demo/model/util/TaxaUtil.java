package com.projeto.demo.model.util;

import java.math.BigDecimal;

public class TaxaUtil {

    public static final BigDecimal TAXA_SAQUE = new BigDecimal("1.50");

    public static BigDecimal calcularTaxaSaque(BigDecimal valorSaque) {
        return TAXA_SAQUE;
    }
}