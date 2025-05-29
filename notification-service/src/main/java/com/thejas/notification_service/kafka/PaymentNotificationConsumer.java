package com.thejas.notification_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.thejas.notification_service.model.Payment;

@Service
public class PaymentNotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentNotificationConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.payment-notifications}", 
                  groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentNotification(
            @Payload Payment payment,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        try {
            logger.info("Received payment notification from topic: {}, partition: {}", topic, partition);
            logger.info("📢 Sending notification for payment:");
            logger.info("🧾 Payment ID: {}", payment.getId());
            logger.info("💰 Amount: ${}", payment.getAmount());
            logger.info("✅ Status: {}", payment.getStatus());
            logger.info("📦 Order ID: {}", payment.getOrderId());
        } catch (Exception e) {
            logger.error("Error processing payment notification: {}", e.getMessage(), e);
            throw e; // Re-throw to let Kafka handle the error based on error handler config
        }
    }
}