package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.enums.AccountType;
import org.springframework.graphql.data.method.annotation.Argument;

public interface AccountService {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO banqueAccountDTO);


    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
