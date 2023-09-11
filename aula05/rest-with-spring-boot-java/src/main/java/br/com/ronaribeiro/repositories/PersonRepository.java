package br.com.ronaribeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ronaribeiro.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
