package gr.nikolis.sql.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specie")
@Getter
@Setter
@NoArgsConstructor
public class Specie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specie_type")
    private String specieType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "specie_trick",
            joinColumns = @JoinColumn(name = "specie_id"),
            inverseJoinColumns = @JoinColumn(name = "trick_id"))
    private Set<Trick> tricksSet = new HashSet<>();

    public Specie(Long id, String specieType, Set<Trick> tricks) {
        this.id = id;
        this.specieType = specieType;
        this.tricksSet = tricks;
    }
}
