package org.example.di;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService{


    @Override
    public BigDecimal pay(BigDecimal number) {
        return number.add(BigDecimal.valueOf(100l));
    }

    @Override
    public BigDecimal refund(BigDecimal number) {
        return number.subtract(BigDecimal.valueOf(100l));
    }
}

