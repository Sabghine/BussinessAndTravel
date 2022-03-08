package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.spring.entities.BadWord;

@Repository

public interface badwordRepo extends JpaRepository <BadWord,Long> {

}
