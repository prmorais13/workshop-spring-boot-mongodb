package com.prmorais.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.prmorais.workshopmongo.domain.User;
import com.prmorais.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository repo;

	@Override
	public void run(String... args) throws Exception {
		
		repo.deleteAll();
		
		User paulo = new User(null, "Paulo Roberto", "prmorais@gmail.com");
		User patricia = new User(null, "Patricia Nunes", "srtapatricia@gmail.com");
		User maria = new User(null, "Maria Fernanda", "nanda@gmail.com");
		
		repo.saveAll(Arrays.asList(paulo, patricia, maria));
	}

}
