package vaadin.spring.boot.starter.db;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sysproperty")
@Data
public class SampleEntitySystemProperty extends SampleAbstractEntity {

    @Column(name = "property", unique = true)
    public String property;
    //
    @Column(name = "value")
    public String value;

    public static SampleEntitySystemProperty build(String property, String value) {
        SampleEntitySystemProperty e = new SampleEntitySystemProperty();
        e.property = property;
        e.value = value;
        return e;
    }
}
