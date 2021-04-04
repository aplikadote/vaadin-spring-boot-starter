package vaadin.spring.boot.starter.db;

import vaadin.spring.boot.starter.system.SampleSystemRole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sysrole")
@Data
public class SampleEntitySystemRole extends SampleAbstractEntity {

    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    private SampleSystemRole role;

    public static SampleEntitySystemRole build(SampleSystemRole role) {
        SampleEntitySystemRole e = new SampleEntitySystemRole();
        e.role = role;
        return e;
    }
}
