package vaadin.spring.boot.starter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepoSystemProperty extends JpaRepository<SampleEntitySystemProperty, Integer> {

    public Optional<SampleEntitySystemProperty> findByProperty(String property);
}
