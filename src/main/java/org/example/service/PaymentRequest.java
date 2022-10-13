package org.example.service;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentRequest {
    final BigDecimal amount;

    public PaymentRequest(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentRequest that = (PaymentRequest) o;

        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                '}';
    }
}
