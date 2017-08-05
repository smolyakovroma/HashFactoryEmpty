package ru.hashfactory.empty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profit")
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "ferm_fk", nullable = false)
    private Ferm ferm;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date postedDate;

    private float profit;
}
