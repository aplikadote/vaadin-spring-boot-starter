package vaadin.spring.boot.starter.system;

import vaadin.spring.boot.starter.db.SampleEntityPerson;
import vaadin.spring.boot.starter.db.SampleEntitySystemRole;
import vaadin.spring.boot.starter.db.SampleEntitySystemUser;
import vaadin.spring.boot.starter.services.SampleServiceSystemProperty;
import vaadin.spring.boot.starter.services.SampleServiceSystemRole;
import vaadin.spring.boot.starter.services.SampleServices;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringComponent
@Slf4j
public class SampleSystemStarter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData(SampleServices services) {
        return args -> {
            SampleServiceSystemProperty ecosystem = services.getServiceSystemProperty();
            if (ecosystem.isReady()) {
                log.info("El sistema ya se encuentra configurado");
                return;
            }
            int seed = 123;

            log.info("Generating demo data");

            log.info("... generating 100 Sample Person entities...");
            ExampleDataGenerator<SampleEntityPerson> generator = new ExampleDataGenerator<>(SampleEntityPerson.class, LocalDateTime.of(2021, 4, 2, 0, 0, 0));
            generator.setData(SampleEntityPerson::setId, DataType.ID);
            generator.setData(SampleEntityPerson::setFirstName, DataType.FIRST_NAME);
            generator.setData(SampleEntityPerson::setLastName, DataType.LAST_NAME);
            generator.setData(SampleEntityPerson::setEmail, DataType.EMAIL);
            generator.setData(SampleEntityPerson::setPhone, DataType.PHONE_NUMBER);
            generator.setData(SampleEntityPerson::setDateOfBirth, DataType.DATE_OF_BIRTH);
            generator.setData(SampleEntityPerson::setOccupation, DataType.OCCUPATION);
            generator.setData(SampleEntityPerson::setImportant, DataType.BOOLEAN_10_90);
            services.getServicePerson().saveAll(generator.create(100, seed));

            log.info("...  generando roles del sistema ...");
            SampleServiceSystemRole srvRole = services.getServiceSystemRole();
            Stream.of(SampleSystemRole.values()).forEach(e -> {
                srvRole.save(SampleEntitySystemRole.build(e));
            });

            log.info("...  generando administrador del sistema ...");
            SampleEntitySystemUser admin = new SampleEntitySystemUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(Stream.of(srvRole.findAdmin()).collect(Collectors.toSet()));
            services.getServiceSystemUser().save(admin);

            log.info("...  generando usuario del sistema ...");
            SampleEntitySystemUser user = new SampleEntitySystemUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRoles(Stream.of(srvRole.findUser()).collect(Collectors.toSet()));
            services.getServiceSystemUser().save(user);

            log.info("...  sistema iniciado ...");
            services.getServiceSystemProperty().setReady();

            log.info("Sistema inicializado correctamente");
        };
    }

}