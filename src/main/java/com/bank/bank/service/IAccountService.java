package com.bank.bank.service;

import com.bank.bank.dto.AccountDto;

import java.util.List;


public interface IAccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById (Long accountId);

    List<AccountDto> getAllAcounts();

    AccountDto deposit(Long id, Double amount);
    AccountDto withdraw(Long id, Double amount);
    AccountDto updateAccountById(AccountDto accountDto, Long id);

    void deleteAccount(Long id);



}
