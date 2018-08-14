package com.prmorais.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prmorais.workshopmongo.domain.Post;
import com.prmorais.workshopmongo.resources.util.URL;
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
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text) {		
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minData,
			@RequestParam(defaultValue = "") String maxData) {		
		text = URL.decodeParam(text);
		Date min = URL.convertData(minData, new Date(0L));
		Date max = URL.convertData(maxData, new Date());
		List<Post> list = service.searchFull(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
