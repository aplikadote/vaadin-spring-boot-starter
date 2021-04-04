package vaadin.spring.boot.starter.db;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@Data
public class SampleEntityPerson extends SampleAbstractEntity {

    @Column(name = "firstName", length = 128)
    private String firstName;
    //
    @Column(name = "lastName", length = 128)
    private String lastName;
    //
    @Column(name = "email", length = 256)
    private String email;
    //
    @Column(name = "phone", length = 20)
    private String phone;
    //
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    //
    @Column(name = "occupation", length = 128)
    private String occupation;
    //
    @Column(name = "important")
    private boolean important;

}
