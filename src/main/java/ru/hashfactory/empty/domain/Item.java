package ru.hashfactory.empty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String name;
    private float price;
    private int amount;
    private int reserve;
    private String producer;
    private String description;
    private String algoritm;
    private String speed;
    private int intake;
    private int prioritet;
    private int active;
    @Lob
    private byte[] pic;
    @Lob
    private byte[] pic1;
    @Lob
    private byte[] pic2;
    @Lob
    private byte[] pic3;
    @Lob
    private byte[] pic4;
    @Enumerated(EnumType.STRING)
    private TypeItem typeItem;
}
