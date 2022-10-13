package org.example.service;

import org.example.model.Notification;
import org.example.repository.DBClient;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
    private final DBClient dbClient;

    public NotificationServiceImpl(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public void notify(Notification notification) {
        // save the notification in db client.
        System.out.println(notification);
    }
}
