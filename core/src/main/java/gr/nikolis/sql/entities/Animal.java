package gr.nikolis.sql.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"tricksSet"})
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_name")
    private String name;

    @Column(name = "animal_species")
    private String specie;

    @Transient
    private List<String> tricks = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "animal_trick",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "trick_id"))
    private Set<Trick> tricksSet = new HashSet<>();

    public Animal(String animalName, String animalSpecies, Set<Trick> tricksSet) {
        this.name = animalName;
        this.specie = animalSpecies;
        this.tricksSet = tricksSet;
    }
}
