package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Organization {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Basic
    @Getter
    @Setter
    @Column(name = "name", nullable = true, length = -1)
    private String name;
}
