package com.parallon.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parallon.bank.model.Customer;
import com.parallon.bank.service.BankService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	BankService bankService;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return bankService.getAllCustomers();
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return bankService.getCustomer(id);
	}

	@PutMapping("/customer")
	public String updateCustomer(@RequestBody Customer customer) {
		return bankService.updateCustomer(customer);
	}

	@PostMapping("/customer")
	public String addCustomer(@RequestBody Customer customer) {
		return bankService.addCustomer(customer);
	}

	// Delete customer
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		return bankService.deleteCustomer(id);
	}

	// Internal Transfer
	@GetMapping("/customer/{id}/account/{fromAcct}/{toAcct}")
	public String internalTransfer(@PathVariable Integer id, @PathVariable Integer fromAcct,
			@PathVariable Integer toAcct, @RequestParam Integer transferAmount) {
		return bankService.internalTransfer(id, fromAcct, toAcct, transferAmount);
	}

}
