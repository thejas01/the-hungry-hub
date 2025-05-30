package com.thejas.notification_service.kafka;


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
                  groupId = "${spring.kafka.consumer.group-id}",
                  containerFactory = "kafkaListenerContainerFactory")
    public void handlePaymentNotification(
            @Payload Payment payment,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            logger.info("Received payment notification from topic: {}, partition: {}, offset: {}", 
                       topic, partition, offset);
            logger.info("📢 Processing payment notification:");
            logger.info("🧾 Payment ID: {}", payment.getId());
            logger.info("💰 Amount: ${}", payment.getAmount());
            logger.info("✅ Status: {}", payment.getStatus());
            logger.info("📦 Order ID: {}", payment.getOrderId());
            
            // Simulate notification sending
            sendNotification(payment);
            
            logger.info("✅ Successfully processed payment notification for payment ID: {}", payment.getId());
        } catch (Exception e) {
            logger.error("❌ Error processing payment notification for payment ID: {}", payment.getId(), e);
            throw e; // Re-throw to let Kafka handle the error based on error handler config
        }
    }
    
    private void sendNotification(Payment payment) {
        try {
            // Simulate different notification types based on payment status
            switch (payment.getStatus().toUpperCase()) {
                case "SUCCESS":
                    logger.info("📧 Sending success email notification for payment: {}", payment.getId());
                    logger.info("📱 Sending success SMS to customer for order: {}", payment.getOrderId());
                    break;
                case "FAILED":
                    logger.info("📧 Sending payment failure email for payment: {}", payment.getId());
                    logger.info("⚠️ Alerting customer service team about failed payment");
                    break;
                case "PENDING":
                    logger.info("📧 Sending payment pending notification for payment: {}", payment.getId());
                    break;
                default:
                    logger.warn("Unknown payment status: {} for payment: {}", payment.getStatus(), payment.getId());
            }
            
            // Simulate notification processing time
            Thread.sleep(50);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Notification sending interrupted", e);
        }
    }
}