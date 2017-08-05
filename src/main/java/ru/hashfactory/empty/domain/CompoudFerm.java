package ru.hashfactory.empty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compoudferm")
public class CompoudFerm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private int amount;

    @OneToOne
    @JoinColumn(name = "compoud_fk", nullable = false)
    private Compoud compoud;

    @OneToOne
    @JoinColumn(name = "ferm_fk", nullable = false)
    private Ferm ferm;

    @Order
    private int ord;
}
