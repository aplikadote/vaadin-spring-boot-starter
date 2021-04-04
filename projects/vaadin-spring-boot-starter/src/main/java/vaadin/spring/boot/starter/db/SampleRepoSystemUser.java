package vaadin.spring.boot.starter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepoSystemUser extends JpaRepository<SampleEntitySystemUser, Integer> {

    public Optional<SampleEntitySystemUser> findByUsername(String username);

}
