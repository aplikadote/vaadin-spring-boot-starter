package vaadin.spring.boot.starter.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepoPerson extends JpaRepository<SampleEntityPerson, Integer> {

}