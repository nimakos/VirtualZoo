package gr.nikolis.sql.dao;

import gr.nikolis.handlers.validations.customValidator2.AnimalName;
import gr.nikolis.handlers.validations.customValidator1.FieldsValueMatch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
//@JsonIgnoreProperties({"tricksSet"})
@FieldsValueMatch.List({ // for validations
        @FieldsValueMatch(
                field = "name",
                fieldMatch = "specie"
        )
})
@ApiModel(description = "Documentation about animal")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_name")
    @AnimalName
    @ApiModelProperty(notes="Documentation about this property")
    private String name;

    @Size(min = 2, message = "Name should have at least 2 characters")
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
