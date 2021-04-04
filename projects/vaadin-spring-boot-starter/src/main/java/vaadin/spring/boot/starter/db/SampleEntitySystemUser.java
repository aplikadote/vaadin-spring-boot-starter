package vaadin.spring.boot.starter.db;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sysuser")
@Data
public class SampleEntitySystemUser extends SampleAbstractEntity {

    @Column(name = "username", length = 128, unique = true)
    private String username;
    //
    @Column(name = "password", length = 128)
    private String password;
    //
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sysuser_sysrole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<SampleEntitySystemRole> roles = new HashSet<>();

}
