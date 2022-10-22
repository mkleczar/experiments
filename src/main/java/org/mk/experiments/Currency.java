package org.mk.experiments;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class Currency {

    private BigDecimal amount;
    public static Currency of(String amount) {
        return new Currency(new BigDecimal(amount));
    }

    public BigDecimal divide(int number) {
        return amount.divide(BigDecimal.valueOf(number), RoundingMode.HALF_EVEN);
    }
}
