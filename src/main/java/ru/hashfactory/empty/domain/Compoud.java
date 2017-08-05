package ru.hashfactory.empty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compoud")
public class Compoud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float price;
    private int amount;

    @ManyToMany
    @JoinTable(name = "compoundFerms",
            joinColumns = @JoinColumn(name = "ferm_fk"),
            inverseJoinColumns = @JoinColumn(name = "compound_fk"))
    private List<Ferm> listFerm;

}
