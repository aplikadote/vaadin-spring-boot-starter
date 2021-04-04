package vaadin.spring.boot.starter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class SampleApiAdmin {

    @GetMapping()
    public Object findAll() {
        return "ADMIN";
    }

}
