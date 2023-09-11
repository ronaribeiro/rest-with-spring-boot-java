package br.com.ronaribeiro.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ronaribeiro.data.vo.v1.PersonVO;
import br.com.ronaribeiro.exceptions.ResourceNotFoundException;
import br.com.ronaribeiro.mapper.LocalModelMapper;
import br.com.ronaribeiro.model.Person;
import br.com.ronaribeiro.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	
	public List<PersonVO> findAll() {
		
		logger.info("buscando todas as pessoas!");
		
		return LocalModelMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
		
	}
	
	
	public PersonVO findById(Long id) {
		
		logger.info("buscando uma pessoa!");
		
		return LocalModelMapper.parseObject(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no data found")),PersonVO.class);
		
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Criando uma pessoa");
		
		var entity = LocalModelMapper.parseObject(person, Person.class);
		
		var vo = LocalModelMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}


	public PersonVO update(PersonVO person) {
		logger.info("Alterando uma pessoa");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("no data found"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = LocalModelMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deletando uma pessoa");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no data found"));
		
		repository.delete(entity);
	}
	
	

}
