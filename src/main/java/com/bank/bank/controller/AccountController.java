package com.bank.bank.controller;


import com.bank.bank.dto.AccountDto;
import com.bank.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto account = iAccountService.createAccount(accountDto);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable Long id){
        AccountDto account = iAccountService.getAccountById(id);

        return new ResponseEntity<>(account, HttpStatus.OK);

    }





}
