package ru.hashfactory.empty.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ferm")
@ToString
public class Ferm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "listFerm", fetch = FetchType.LAZY)
    @OrderBy("ord")
    @JsonManagedReference
    private List<Compoud> listCompoud;

    private int power;
    private int countVK;
    private int countPSU;

    private float profit;

    private int tarif1;
    private int tarif2;
    private int tarif3;

    private int procent1;
    private int procent2;
    private int procent3;

}
