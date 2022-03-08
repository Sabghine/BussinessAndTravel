package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

}
