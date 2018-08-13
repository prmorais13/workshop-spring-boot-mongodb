package com.prmorais.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.prmorais.workshopmongo.domain.Post;
import com.prmorais.workshopmongo.domain.User;
import com.prmorais.workshopmongo.dto.AuthorDTO;
import com.prmorais.workshopmongo.dto.CommentDTO;
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
		
		userRepo.saveAll(Arrays.asList(paulo, patricia, maria));
		
		Post post1 = new Post(null, sdf.parse("04/05/2030"), "Partiu viagem", "Vou viajar para a Suíça. Abraços.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("13/10/2018"), "Grato Senhor", "Incorporação publicada. Grato a todos.", new AuthorDTO(paulo));
		Post post3 = new Post(null, sdf.parse("13/09/2018"), "Senhor", "Dai-me força para vencer meu próprio mal.", new AuthorDTO(paulo));
		
		CommentDTO comment1 = new CommentDTO("Que bom Nandinha. Deus de abençoe filha.", sdf.parse("04/10/2030"), new AuthorDTO(paulo));
		CommentDTO comment2 = new CommentDTO("Graças a Deus que deu tudo certo pai. Beijos.", sdf.parse("13/10/2018"), new AuthorDTO(maria));
		CommentDTO comment3 = new CommentDTO("Deus vai dar toda força que precisas pai. Te amo.", sdf.parse("13/09/2018"), new AuthorDTO(maria));
		
		post1.getComments().add(comment1);
		post2.getComments().addAll(Arrays.asList(comment2));
		post3.getComments().addAll(Arrays.asList(comment3));
		
		postRepo.saveAll(Arrays.asList(post1, post2, post3));
		
		maria.getPosts().addAll(Arrays.asList(post1));
		paulo.getPosts().addAll(Arrays.asList(post2, post3));
		
		userRepo.saveAll(Arrays.asList(maria, paulo));
		
	}

}
