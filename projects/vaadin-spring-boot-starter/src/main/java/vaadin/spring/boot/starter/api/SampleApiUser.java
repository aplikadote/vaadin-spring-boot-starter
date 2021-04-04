package vaadin.spring.boot.starter.api;

import vaadin.spring.boot.starter.db.SampleRepoSystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SampleApiUser {

    @Autowired
    SampleRepoSystemUser repo;

    @GetMapping()
    public Object findAll() {
        return repo.findAll();
    }

}
