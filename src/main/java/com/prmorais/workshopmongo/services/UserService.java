package com.prmorais.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prmorais.workshopmongo.domain.User;
import com.prmorais.workshopmongo.dto.UserDTO;
import com.prmorais.workshopmongo.repository.UserRepository;
import com.prmorais.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User insert(User obj) {
		return userRepo.insert(obj);
	}
	
	public void deleteById(String id) {
		this.findById(id);
		this.userRepo.deleteById(id);
	}

	public User update(User obj) {
		Optional<User> newObj = userRepo.findById(obj.getId());
		updateData(newObj, obj);
		return userRepo.save(newObj.get());
	}
	
	private void updateData(Optional<User> newObj, User obj) {
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
