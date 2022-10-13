package org.example.model;

public class PaymentGatewayResponse {
    public final PaymentStatus paymentStatus;

    public PaymentGatewayResponse(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentGatewayResponse that = (PaymentGatewayResponse) o;

        return paymentStatus == that.paymentStatus;
    }

    @Override
    public int hashCode() {
        return paymentStatus != null ? paymentStatus.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PaymentGatewayResponse{" +
                "paymentStatus=" + paymentStatus +
                '}';
    }
}
