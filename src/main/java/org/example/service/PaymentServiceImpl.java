package org.example.service;

import org.example.model.PaymentGatewayResponse;
import org.example.model.PaymentStatus;
import org.example.repository.DBClient;
import org.springframework.stereotype.Component;


@Component
public class PaymentServiceImpl implements PaymentService {
    private final DBClient dbClient;
    private final NotificationService notificationService;

    public PaymentServiceImpl(DBClient dbClient, NotificationService notificationService) {
        this.dbClient = dbClient;
        this.notificationService = notificationService;
    }

    @Override
    public PaymentGatewayResponse executePayment(PaymentRequest paymentRequest) {
        // save payment to db
        // send payment notification
        return new PaymentGatewayResponse(PaymentStatus.SUCCESS);
    }
}
