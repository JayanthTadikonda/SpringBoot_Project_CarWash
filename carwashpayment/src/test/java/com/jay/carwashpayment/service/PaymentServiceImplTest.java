package com.jay.carwashpayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jay.carwashpayment.model.Order;
import com.jay.carwashpayment.model.Payment;
import com.jay.carwashpayment.model.TransactionRequest;
import com.jay.carwashpayment.model.TransactionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.mail.internet.AddressException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {

    PaymentServiceImpl paymentServiceImplMock = mock(PaymentServiceImpl.class);


    @Test
    @DisplayName("first car wash test ever")
    void sendEmailDummy() {
        assertTrue(true);
    }

    @Test
    void sendEmail() throws AddressException {
//        assertEquals("MailSent",paymentServiceMock.sendEmailDummy());
    }

    @Test
    void paymentProcessing() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Access Payment List of a Customer")
    void testPaymentListWithMock() {


        List<Payment> paymentList = Arrays.asList(new Payment(null, 2, "a", "b", "success", "444", 3223, 999, null, "good", 3),
                new Payment(null, 2, "a", "b", "success", "5434", 3223, 999, null, "good", 3),
                new Payment(null, 2, "a", "b", "success", "756", 3223, 999, null, "good", 3));

        when(paymentServiceImplMock.paymentList("customer")).thenReturn(paymentList);

    }

    @Test
    void doPaymentSetOrderPaymentStatus() throws AddressException, JsonProcessingException {

        List<Order> orderList = Arrays.asList(
                new com.jay.carwashpayment.model.Order(
                        1, "asd", "suv", 999, "Kevin", "sds", null, null, "paid", "mail"));

        Payment payment = new Payment(
                null, 2, "a", "b", "success",
                "444", 3223, 999, null, "good", 3);

        when(paymentServiceImplMock.doPaymentSetOrderPaymentStatus(new TransactionRequest(orderList.get(0),payment))).thenReturn(
                new TransactionResponse(orderList.get(0),orderList.get(0).getPaymentStatus(),765,"success"));
    }

    @Test
    @DisplayName("Payment Details by ID")
    void paymentById() throws JsonProcessingException {
        List<Payment> paymentList = Arrays.asList(new Payment(null, 2, "a", "b", "success", "444", 3223, 999, null, "good", 3),
                new Payment(null, 2, "a", "b", "success", "5434", 3223, 999, null, "good", 3),
                new Payment(null, 2, "a", "b", "success", "756", 3223, 999, null, "good", 3));

        when(paymentServiceImplMock.paymentById(1)).thenReturn(java.util.Optional.ofNullable(paymentList.get(0)));
    }
}