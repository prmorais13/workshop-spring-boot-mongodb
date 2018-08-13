package com.prmorais.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prmorais.workshopmongo.domain.Post;
import com.prmorais.workshopmongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		//Post post = service.findById(id);
		return ResponseEntity.ok().body(service.findById(id));
	}
}
