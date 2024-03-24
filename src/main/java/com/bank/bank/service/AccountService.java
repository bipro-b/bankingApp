package com.bank.bank.service;

import com.bank.bank.dto.AccountDto;
import com.bank.bank.entity.Account;
import com.bank.bank.mapper.AccountMapper;
import com.bank.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public AccountDto deposit(Long id, Double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("Id is not found"));
        Double totalABalance = account.getBalance()+ amount;
        account.setBalance(totalABalance);
        Account updatedAmount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(updatedAmount);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("Id is not found"));


        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }



        Double totalABalance = account.getBalance() - amount;
        account.setBalance(totalABalance);
        Account updatedAmount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(updatedAmount);
    }

    @Override
    public AccountDto updateAccountById(AccountDto accountDto, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("Id is not valid"+id)
        );

        if (accountDto.getAccountHolderName() != null && !accountDto.getAccountHolderName().isEmpty()) {
            account.setAccountHolderName(accountDto.getAccountHolderName());
        }

        if (accountDto.getBalance() != null) {
            throw new IllegalArgumentException("Updating balance is not allowed");
        }

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
