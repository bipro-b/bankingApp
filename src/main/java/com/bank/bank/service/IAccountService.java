package com.bank.bank.service;

import com.bank.bank.dto.AccountDto;


public interface IAccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById (Long accountId);

    AccountDto getAllAcounts(AccountDto accountDto);

    AccountDto updateAccountById(AccountDto accountDto, Long id);

    Void deleteAccount(Long id);



}
