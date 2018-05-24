package com.lexach.ClothingFeedParsers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Retailer")
public class Retailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rootUrl", nullable = false, unique = true)
    private String rootUrl;

    @Column(name = "parserClassName", nullable = false, unique = true)
    private String parserClassName;

}
