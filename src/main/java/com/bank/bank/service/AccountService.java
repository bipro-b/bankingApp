package com.bank.bank.service;

import com.bank.bank.dto.AccountDto;
import com.bank.bank.entity.Account;
import com.bank.bank.mapper.AccountMapper;
import com.bank.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {

        Account account= accountRepository.findById(accountId).orElseThrow(()->
                new RuntimeException("Account does not exists"));

        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public List<AccountDto> getAllAcounts() {
        List<Account>  account= accountRepository.findAll();
        return account.stream().map((a)->
                AccountMapper.mapToAccountDto(a)).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccountById(AccountDto accountDto, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Id is not valid"+id)
        );


        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());


        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid id"+id)
                );
        accountRepository.deleteById(id);

    }
}
