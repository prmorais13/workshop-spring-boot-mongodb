package com.prmorais.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prmorais.workshopmongo.domain.Post;
import com.prmorais.workshopmongo.repository.PostRepository;
import com.prmorais.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	
	public Post findById(String id) {
		Optional<Post> obj = postRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public List<Post> findByTitle(String text) {
		//return postRepo.findByTitleContainingIgnoreCase(text);
		return postRepo.searchTitle(text);
	}
	
	public List<Post> searchFull(String text, Date minData, Date maxData) {
		maxData = new Date(maxData.getTime() + 24 * 60 * 60 * 1000);
		return postRepo.searchFull(text, minData, maxData);
	}

}
