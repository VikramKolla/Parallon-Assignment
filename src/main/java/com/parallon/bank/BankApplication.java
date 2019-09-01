package com.parallon.bank;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parallon.bank.model.Account;
import com.parallon.bank.model.Customer;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	// creating data set to play around, instead of using H2 or SQL DB or JPA
	public static List<Account> accounts = new ArrayList<>();
	public static List<Customer> customers = new ArrayList<>();

	@PostConstruct
	public void init() {
		Account acc1 = new Account(1, "savings", 4234);
		Account acc2 = new Account(2, "savings", 1212);
		Account acc3 = new Account(3, "savings", 3312);
		accounts.add(acc2);
		accounts.add(acc3);
		accounts.add(acc1);
		Customer cust1 = new Customer(1, "Vikram", "Kolla", "7204294869", "Bengaluru, India", acc2);
		cust1.addAccount(acc3);
		customers.add(cust1);
		Customer cust2 = new Customer(2, "Anil", "Kumar", "7204294869", "Mysuru, India", acc1);
		customers.add(cust2);
	}
}
