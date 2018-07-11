package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@NamedQuery(name = "findAllUsers", query = "select c from Userinfo c")
@Table(schema = "public", name = "userinfo")
public class Userinfo {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private UUID id;

    @Basic
    @Getter
    @Setter
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Getter
    @Setter
    @Column(name = "phone", nullable = true, precision = 0)
    private BigInteger phone;


    @Setter
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Organization.class)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organizationByOrganizationId;
}
