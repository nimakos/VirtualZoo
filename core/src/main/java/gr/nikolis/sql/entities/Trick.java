package gr.nikolis.sql.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trick")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trick implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(nullable = false)
    private Long id;

    @Column(name = "trick_name")
    private String trick;
}
