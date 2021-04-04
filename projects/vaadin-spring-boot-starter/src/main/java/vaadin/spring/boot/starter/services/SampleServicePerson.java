package vaadin.spring.boot.starter.services;

import vaadin.spring.boot.starter.db.SampleEntityPerson;
import vaadin.spring.boot.starter.db.SampleRepoPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServicePerson {

    @Autowired
    private SampleRepoPerson repoPerson;

    public void saveAll(List<SampleEntityPerson> persons) {
        repoPerson.saveAll(persons);
    }

    public void update(SampleEntityPerson bean) {
        repoPerson.save(bean);
    }
}
