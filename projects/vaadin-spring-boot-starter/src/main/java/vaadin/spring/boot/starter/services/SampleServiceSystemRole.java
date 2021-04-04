package vaadin.spring.boot.starter.services;

import vaadin.spring.boot.starter.db.SampleEntitySystemRole;
import vaadin.spring.boot.starter.db.SampleRepoSystemRole;
import vaadin.spring.boot.starter.system.SampleSystemRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceSystemRole {

    @Autowired
    private SampleRepoSystemRole repoSystemRole;

    public void save(SampleEntitySystemRole sysuser) {
        repoSystemRole.save(sysuser);
    }

    public void saveAll(List<SampleEntitySystemRole> sysusers) {
        repoSystemRole.saveAll(sysusers);
    }

    public SampleEntitySystemRole find(SampleSystemRole role) {
        return repoSystemRole.findByRole(role).orElseThrow(() -> new RuntimeException("Rol no encontrado: " + role));
    }

    public SampleEntitySystemRole findAdmin() {
        return repoSystemRole.findByRole(SampleSystemRole.ADMIN).orElseThrow(() -> new RuntimeException("Rol no encontrado: ADMIN"));
    }

    public SampleEntitySystemRole findUser() {
        return repoSystemRole.findByRole(SampleSystemRole.USER).orElseThrow(() -> new RuntimeException("Rol no encontrado: USER"));
    }
}
