package vaadin.spring.boot.starter.db;

import vaadin.spring.boot.starter.system.SampleSystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepoSystemRole extends JpaRepository<SampleEntitySystemRole, Integer> {

    public Optional<SampleEntitySystemRole> findByRole(SampleSystemRole role);

}
