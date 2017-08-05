package ru.hashfactory.empty.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compoud")
@ToString
public class Compoud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float price;
    private int amount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jnd_compoundFerms",
            joinColumns = @JoinColumn(name = "ferm_fk"),
            inverseJoinColumns = @JoinColumn(name = "compound_fk"))
    @JsonBackReference
    private List<Ferm>  listFerm;

    private int ord;

}
