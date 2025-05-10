package org.example.di;

import java.math.BigDecimal;

public interface PaymentService {
//    支付
    BigDecimal pay(BigDecimal number);
//    退款
    BigDecimal refund(BigDecimal number);
}
