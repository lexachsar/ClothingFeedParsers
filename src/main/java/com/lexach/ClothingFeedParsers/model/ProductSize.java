package com.lexach.ClothingFeedParsers.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ProductSize")
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idProduct", nullable = false)
    private Long idProduct;

    @Column(name = "idSize", nullable = false)
    private Long idSize;

}
