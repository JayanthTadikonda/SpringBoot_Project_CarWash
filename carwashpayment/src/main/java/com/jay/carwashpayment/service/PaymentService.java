package com.jay.carwashpayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jay.carwashpayment.model.*;

import javax.mail.internet.AddressException;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    public void sendEmailDummy() throws AddressException;

    public void sendEmail(Payment payment, String emailAddress) throws AddressException;

    public TransactionResponse doPaymentSetOrderPaymentStatus(TransactionRequest request) throws AddressException, JsonProcessingException;

    public String paymentProcessing();

    public List<Payment> paymentList(String name);

    public Optional<Payment> paymentById(int id) throws JsonProcessingException;

}
