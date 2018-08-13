package com.prmorais.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.prmorais.workshopmongo.domain.Post;
import com.prmorais.workshopmongo.domain.User;
import com.prmorais.workshopmongo.repository.PostRepository;
import com.prmorais.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User paulo = new User(null, "Paulo Roberto", "prmorais@gmail.com");
		User patricia = new User(null, "Patricia Nunes", "srtapatricia@gmail.com");
		User maria = new User(null, "Maria Fernanda", "nanda@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("04/05/2030"), "Partiu viagem", "Vou viajar para a Suíça. Abraços.", maria);
		Post post2 = new Post(null, sdf.parse("13/10/2018"), "Grato Senhor", "Incorporação publicada. Grato a todos.", paulo);
		
		userRepo.saveAll(Arrays.asList(paulo, patricia, maria));
		postRepo.saveAll(Arrays.asList(post1, post2));
	}

}
