package ru.hashfactory.empty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ferm")
public class Ferm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "listFerm")
    private List<Compoud> listCompoud;

    private int power;

    private float profit;

    private int tarif1;
    private int tarif2;
    private int tarif3;

    private int procent1;
    private int procent2;
    private int procent3;

}
