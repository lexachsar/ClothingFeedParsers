package com.lexach.ClothingFeed.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ProductColour")
public class ProductColour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idProduct", nullable = false)
    private Long idProduct;

    @Column(name = "idColour", nullable = false)
    private Long idColour;

}
