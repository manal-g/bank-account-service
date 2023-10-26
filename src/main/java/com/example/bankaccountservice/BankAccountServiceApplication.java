package com.example.bankaccountservice;

import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.entities.Customer;
import com.example.bankaccountservice.enums.AccountType;
import com.example.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import com.example.bankaccountservice.repositories.BankAccountRepository;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@EntityScan("com/example/bankaccountservice/entities")


public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	//Objet creer au démarrage et methode start va s'executer
	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
		return args -> {

			Stream.of("Manal","Rania","Youssra").forEach(c->{
				Customer customer= Customer.builder().name(c)
						.build();
				customerRepository.save(customer);

			});
			customerRepository.findAll().forEach(customer ->
			{
				for (int i = 0; i < 10; i++) {

					BankAccount bankAccount=BankAccount.builder()
							.Id(UUID.randomUUID().toString())
							.type(Math.random()>0.5? AccountType.CURRENT_ACOUNT:AccountType.SAVING_ACCOUNT)
							.balance(10000+Math.random()*90000)
							.createAt(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);

				}

			});

		};

	}

}



