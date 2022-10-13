package org.example.service;

import org.example.model.PaymentGatewayResponse;

public interface PaymentService {
    PaymentGatewayResponse executePayment(PaymentRequest paymentRequest);
}
