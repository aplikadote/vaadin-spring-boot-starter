package vaadin.spring.boot.starter.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class SampleServices {

    @Autowired
    SampleServicePerson servicePerson;

    @Autowired
    SampleServiceSystemUser serviceSystemUser;

    @Autowired
    SampleServiceSystemRole serviceSystemRole;

    @Autowired
    SampleServiceSystemProperty serviceSystemProperty;

}
