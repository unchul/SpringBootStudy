package com.example.demo.controller;


import com.example.demo.controller.request.AccountRequest;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account/test")
    public Account justCreateAccount() {
        return new Account("name","password");
    }

    @PostMapping("/account/create")
    public Account createAccount(@RequestBody AccountRequest account) {
        Account requestedAccount = account.toAccount();
        return accountRepository.save(requestedAccount);
    }
}
