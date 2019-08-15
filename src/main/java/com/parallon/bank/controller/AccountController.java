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

import com.parallon.bank.model.Account;
import com.parallon.bank.service.BankService;

import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	BankService bankService;

	// Get all accounts
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		return bankService.getAllAccounts();
	}

	// Get account based on ID
	@GetMapping("/account/{id}")
	public Account getAccount(@PathVariable Integer id) {
		return bankService.getAccount(id);
	}

	// Create/Add Account
	@PostMapping("/account")
	public String createAccount(@RequestBody Account account) {
		return bankService.createAccount(account);
	}
	
	// Delete Account
	@DeleteMapping("/account/{id}")
	public String deleteAccount(@PathVariable Integer id) {
		return bankService.deleteAccount(id);
	}
	
	// Deposit money to account
	@PutMapping("/deposit/account/{id}")
	public String depositMoney(@PathVariable Integer id, @RequestParam Integer depositAmt) {
		return bankService.depositAmount(id, depositAmt);
	}

	// Withdraw money from account
	@PutMapping("/withdraw/account/{id}")
	public String withdrawMoney(@PathVariable Integer id, @RequestParam Integer withdrawAmt) {
		return bankService.withdrawAmount(id, withdrawAmt);
	}
}
