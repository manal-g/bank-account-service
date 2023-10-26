package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dto.BankAccountRequestDTO;
import com.example.bankaccountservice.dto.BankAccountResponseDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.entities.Customer;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.repositories.CustomerRepository;
import com.example.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class BankAccountGaphQLController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;
    @QueryMapping

    public List<BankAccount>accountsList()
    {
        return bankAccountRepository.findAll();
    }

   /* @QueryMapping
    public BankAccount bankAccountById(@Argument String id)
    {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }*/
   @QueryMapping
   public BankAccount bankAccountById(@Argument String id) {
       if (id == null) {
           throw new IllegalArgumentException("Account ID cannot be null");
       }

       return bankAccountRepository.findById(id)
               .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
   }

   @MutationMapping
   public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount)
   {
       return accountService.addAccount(bankAccount);
   }

   @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccountDTO )
   {
       return accountService.updateAccount(id,bankAccountDTO);
   }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id )
    {
         bankAccountRepository.deleteById(id);
         return true;
    }
    @QueryMapping
    public List<Customer> customers(){
       return customerRepository.findAll();
    }


}
