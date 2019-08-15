package com.parallon.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parallon.bank.BankApplication;
import com.parallon.bank.model.Account;
import com.parallon.bank.model.Customer;

@Service
public class BankService {

	List<Account> accounts = BankApplication.accounts;
	List<Customer> customers = BankApplication.customers;

	public List<Account> getAllAccounts() {
		return accounts;
	}

	public Account getAccount(Integer id) {
		Optional<Account> acct = accounts.stream().filter(acc -> acc.getAccountNumber().equals(id)).findFirst();
		if (acct.isPresent())
			return acct.get();
		return null;
	}

	public List<Customer> getAllCustomers() {
		return customers;
	}

	public Customer getCustomer(Integer id) {
		Optional<Customer> cust = customers.stream().filter(cus -> cus.getCustId().equals(id)).findAny();
		if (cust.isPresent())
			return cust.get();
		return null;
	}

	public String depositAmount(Integer id, Integer depositAmt) {

		Optional<Account> acct = accounts.stream().filter(acc -> acc.getAccountNumber().equals(id)).findAny();
		if (acct.isPresent()) {
			acct.get().setBalance(acct.get().getBalance() + depositAmt);
			return "Amount deposited to account";
		} else {
			return "Account doesn't exist with ID : " + id + "cant deposit any amount";
		}
	}

	public String withdrawAmount(Integer id, Integer withdrawAmt) {

		Optional<Account> acct = accounts.stream().filter(acc -> acc.getAccountNumber().equals(id)).findAny();
		if (acct.isPresent()) {
			acct.get().setBalance(acct.get().getBalance() - withdrawAmt);
			return "Please collect the amount";
		} else {
			return "Account doesn't exist with ID : " + id + "cant withdraw amount";
		}
	}

	public String createAccount(Account account) {
		Optional<Account> acct = accounts.stream().filter(acc -> acc.getAccountNumber().equals(account.getAccountNumber()))
				.findAny();
		if (acct.isPresent()) {
			return "Account with same ID already exists, please try again";
		} else {
			accounts.add(account);
			return "Account created successfully";
		}
	}

	public String deleteAccount(Integer accountId) {

		if (accounts.removeIf(acc -> acc.getAccountNumber().equals(accountId))) {
			return "Successfully Deleted account";
		} else {
			return "Account doesn't exist";
		}
	}

	public String addCustomer(Customer customer) {
		Optional<Customer> acct = customers.stream().filter(acc -> acc.getCustId().equals(customer.getCustId())).findAny();
		if (acct.isPresent()) {
			return "Customer with same ID already exists, please try again";
		} else {
			customers.add(customer);
			return "Customer added successfully";
		}
	}

	public String deleteCustomer(Integer id) {
		if (customers.removeIf(acc -> acc.getCustId().equals(id))) {
			return "Successfully Deleted customer";
		} else {
			return "Customer doesn't exist";
		}
	}

	public String updateCustomer(Customer customer) {
		// remove and then add
		if (customers.removeIf(acc -> acc.getCustId().equals(customer.getCustId()))) {
			customers.add(customer);
			return "Customer added successfully";
		} else {

			return "Customer doesn't exist";
		}
	}

	public String internalTransfer(Integer custId, Integer fromAcct, Integer toAcct, Integer transferAmount) {
		// check if cust exists
		Optional<Customer> cust = customers.stream().filter(cus -> cus.getCustId().equals(custId)).findAny();
		if (cust.isPresent()) {

			// check if both fromAcct and toAcct are present, then transfer amount
			Optional<Account> fromAccnt = accounts.stream().filter(acc -> acc.getAccountNumber().equals(fromAcct)).findAny();
			if (fromAccnt.isPresent()) {
				Optional<Account> toAccnt = accounts.stream().filter(acc -> acc.getAccountNumber().equals(toAcct)).findAny();

				if (toAccnt.isPresent()) {

					// debit and credit amount from respective accounts
					fromAccnt.get().setBalance(fromAccnt.get().getBalance() - transferAmount);
					toAccnt.get().setBalance(toAccnt.get().getBalance() + transferAmount);
					return "Transfer Succeess!";
				}
			}
		}
		return "Please validate the account ids/ cust id";
	}

}
