package com.jay.carwashpayment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jay.carwashpayment.model.*;
import com.jay.carwashpayment.repository.PaymentRepository;
import com.jay.carwashpayment.service.PaymentServiceImpl;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @Autowired
    public EmailService emailService;

    @PostMapping("/pay")
    public TransactionResponse payAmount(@RequestBody TransactionRequest request) throws AddressException, JsonProcessingException {
        return paymentServiceImpl.doPaymentSetOrderPaymentStatus(request);
    }

    @GetMapping("/get-payments-list")
    public ResponseEntity<List<Payment>> paymentList() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-payment/{name}")
    public List<Payment> paymentListOfName(@PathVariable String name) {
        return paymentServiceImpl.paymentList(name);
    }

    @GetMapping("/send-mail")
    public String sendMail() throws AddressException {
        Payment payment = new Payment();
        payment.setPaymentId(1232);
        payment.setPaymentStatus("Success");
        paymentServiceImpl.sendEmail(payment, "jayanth2683@gmail.com");
        return "Sent Mail";
    }

    @GetMapping("/send-dummy")
    public String sendDummy() throws AddressException {
        paymentServiceImpl.sendEmailDummy();
        return "SEnt MAIL";
    }

    @RequestMapping("/test-payment")
    public String testPayment() {
        return "Payment gateway up and running";
    }

    @GetMapping("/get-payment-byId/{Id}")
    public Optional<Payment> getPaymentById(@PathVariable int id) throws JsonProcessingException {
        return paymentServiceImpl.paymentById(id);
    }
}
