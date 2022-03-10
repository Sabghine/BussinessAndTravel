package pidev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pidev.spring.entities.Planning;

public interface PlanningRepository extends JpaRepository<Planning, Long> {

}
