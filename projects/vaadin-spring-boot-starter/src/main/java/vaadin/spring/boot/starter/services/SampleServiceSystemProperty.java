package vaadin.spring.boot.starter.services;

import vaadin.spring.boot.starter.SampleApplicationException;
import vaadin.spring.boot.starter.system.SampleSystemStatus;
import vaadin.spring.boot.starter.db.SampleEntitySystemProperty;
import vaadin.spring.boot.starter.db.SampleRepoSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SampleServiceSystemProperty {

    private static final String STATUS = "status";

    @Autowired
    SampleRepoSystemProperty repoSystemProperty;


    public Optional<SampleEntitySystemProperty> getProperty(String property) {
        return repoSystemProperty.findByProperty(property);
    }

    public String getPropertyValue(String property) throws SampleApplicationException {
        Optional<SampleEntitySystemProperty> opt = repoSystemProperty.findByProperty(property);
        if (opt.isPresent()) {
            return opt.get().getValue();
        } else {
            throw new SampleApplicationException("Propiedad del sistema no encontrada: " + property);
        }
    }

    public boolean isReady() {
        return repoSystemProperty.findByProperty(STATUS).isPresent();
    }

    public void setReady() {
        SampleEntitySystemProperty status = SampleEntitySystemProperty.build(STATUS, SampleSystemStatus.READY.name());
        repoSystemProperty.save(status);
    }
}
