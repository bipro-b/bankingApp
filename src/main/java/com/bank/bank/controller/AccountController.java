package com.bank.bank.controller;


import com.bank.bank.dto.AccountDto;
import com.bank.bank.response.MessageRespone;
import com.bank.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto account = iAccountService.createAccount(accountDto);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable Long id) {
        AccountDto account = iAccountService.getAccountById(id);

        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccount() {

        List<AccountDto> account = iAccountService.getAllAcounts();

        return ResponseEntity.ok(account);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id,
                                                    @RequestBody AccountDto accountDto) {
        AccountDto account = iAccountService.updateAccountById(accountDto, id);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id
    ) {
        iAccountService.deleteAccount(id);

        MessageRespone message = new MessageRespone();
        message.setMessage("Successfully deleted");

        return new ResponseEntity<>(message.getMessage(), HttpStatus.OK);

    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,
                                                    @RequestBody Map<String, Double> req) {

        Double amount = req.get("amount");

        AccountDto account = iAccountService.deposit(id, amount);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,
                                                     @RequestBody Map<String, Double> req) {

        Double amount = req.get("amount");
        AccountDto account = iAccountService.withdraw(id, amount);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

        }



}
