package com.thejas.payment_service.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.thejas.payment_service.kafka.PaymentEventProducer;
import com.thejas.payment_service.model.Payment;
import com.thejas.payment_service.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository repo;
    private final PaymentEventProducer producer;

    public PaymentService(PaymentRepository repo, PaymentEventProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }

    public Payment makePayment(Payment payment) {
        // Simulate payment
        payment.setPaidAt(LocalDateTime.now());

        boolean success = new Random().nextBoolean();
        payment.setStatus(success ? "SUCCESS" : "FAILED");

        Payment saved = repo.save(payment);

        if (success) {
            producer.sendPaymentEvent(saved); // Send Kafka event
        }

        return saved;
    }
}
