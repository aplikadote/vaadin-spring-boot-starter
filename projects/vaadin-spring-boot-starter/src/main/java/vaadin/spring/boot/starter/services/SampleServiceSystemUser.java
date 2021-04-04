package vaadin.spring.boot.starter.services;

import vaadin.spring.boot.starter.db.SampleEntitySystemUser;
import vaadin.spring.boot.starter.db.SampleRepoSystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceSystemUser {

    @Autowired
    private SampleRepoSystemUser repoSystemUser;

    public void save(SampleEntitySystemUser sysuser) {
        repoSystemUser.save(sysuser);
    }

    public void saveAll(List<SampleEntitySystemUser> sysusers) {
        repoSystemUser.saveAll(sysusers);
    }

}
