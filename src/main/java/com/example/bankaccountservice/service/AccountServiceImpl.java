package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional

public class AccountServiceImpl implements AccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO banqueAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .Id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(banqueAccountDTO.getBalance())
                .type(banqueAccountDTO.getType())
                .currency(banqueAccountDTO.getCurrency())
                .build();

       BankAccount saveBankAccount= bankAccountRepository.save(bankAccount);
       BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount( String id, BankAccountRequestDTO bankAccountDTO )
    {
        BankAccount bankAccount= BankAccount.builder()
                .Id(id)
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount= bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
