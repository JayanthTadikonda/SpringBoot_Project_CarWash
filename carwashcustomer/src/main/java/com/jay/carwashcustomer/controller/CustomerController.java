package com.jay.carwashcustomer.controller;

import com.jay.carwashcustomer.filter.JwtFilter;
import com.jay.carwashcustomer.model.*;
import com.jay.carwashcustomer.repository.CustomerRepository;
import com.jay.carwashcustomer.service.CustomerServiceImpl;
import com.jay.carwashcustomer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customer")
@SuppressWarnings({"unused"})
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/wash-menu")
    public ResponseEntity<List<WashPack>> getWashPackages() {
        return new ResponseEntity<>(customerServiceImpl.getAllWashPackages(), HttpStatus.OK);
    }

    @GetMapping("/addOn-menu")
    public ResponseEntity<List<AddOn>> getAddOns(){
        return new ResponseEntity<>(customerServiceImpl.getAllAddOns(),HttpStatus.OK);
    }

    @GetMapping("/schedule-wash/{date}")
    public String scheduleWash(@DateTimeFormat(pattern = "dd.MM.yyyy") @PathVariable LocalDate date) throws Exception {
        String sent = customerServiceImpl.sendNotification("book-wash at: " + date + " By customer: " + jwtFilter.getLoggedInUserName());
        String resp = customerServiceImpl.receiveNotification();
        return "Wash Need on Date: "+date.toString();
    }

    @GetMapping("/date/{date}")
    public LocalDate returnDate(@DateTimeFormat(pattern = "dd.MM.yyyy") @PathVariable LocalDate date) {
        return date;
    }

    @GetMapping("/wash-now") //Wash Service Booking
    public String bookWash() throws Exception {

        String sent = customerServiceImpl.sendNotification("book-wash" + "By customer: " + jwtFilter.getLoggedInUserName());
        String resp = customerServiceImpl.receiveNotification();

        return sent;
    }

    @GetMapping("/continue") //Proceed to create an ORDER for the Wash
    public OrderResponse placeOrderForAcceptedWashRequest(@RequestParam("pack") String packName, @RequestParam("add-on") String addOnName) throws Exception {
        return customerServiceImpl.placeOrder(packName, addOnName);
    }

    @GetMapping("/pay") // Pay after the wash is completed.
    public TransactionResponse doPayment(@RequestBody RatingReview ratingReview) throws Exception {
        return customerServiceImpl.payAfterWash(ratingReview);
    }

    @GetMapping("/rate-washer") // Rate the washer after the wash
    public RatingReview rateWasher(@RequestBody RatingReview ratingReview) {
        return customerServiceImpl.giveRatingAndReview(ratingReview);
    }

    @PostMapping(value = "/add-customer", consumes = MediaType.APPLICATION_JSON_VALUE) // New Customer Registration
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerServiceImpl.addNewCustomer(customer);
    }

    @GetMapping("/get-customer/{name}") // Get customer with name
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        Customer customer = customerServiceImpl.findByName(name);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer-id/{id}") // Get customer with ID
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerRepository.findById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/get-all-customers") //Lists all the customers in the db
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/update-profile/{name}") // Update user profile
    public Customer updateProfile(@PathVariable String name) {
        return customerServiceImpl.updateProfile(name);
    }

    @GetMapping("/confirmation") //Call to activate Reception of notifications from Customer
    public String notificationTest() throws Exception {
        return customerServiceImpl.receiveNotification();
    }

    //@PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/test-security") //Testing Logged-In Customer name
    public String sayHelloOnAuthentication() {
        return "Hey there " + jwtFilter.getLoggedInUserName();
    }

    @PostMapping("/authenticate") //Authenticate a Customer (Existing)
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid Username or Password Entered !");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/my-orders")
    public List<Order> myOrders() {
        return customerServiceImpl.customerOrders(jwtFilter.getLoggedInUserName());
    }

    @GetMapping("/leaderboard")
    public List<WasherLeaderboard> washerLeaderboard() {
        return customerServiceImpl.washerLeaderboard();
    }

    //@PreAuthorize("hasRole('WASHER')")
    @GetMapping("/washer-only")
    public String heyWasher() {
        return "This method only Accessible by Washer Partner !, Hey there washer !!!";
    }

    //@PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/customer-only")
    public String heyCustomer() {
        return "This method only Accessible by Customer !";
    }
}
