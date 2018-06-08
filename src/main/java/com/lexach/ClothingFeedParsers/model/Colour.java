package com.lexach.ClothingFeedParsers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

// Все модели должны быть аннотированы данной аннотацией.
@Entity
// Аннотация описывает детали таблички, к которой данная модель прилепится.
@Table(name = "Colour")
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name ;

    @Column(name = "hex", nullable = false, unique = true)
    private String hex;

    @OneToMany(mappedBy = "colour")
    private Set<ProductColour> productColours;

    @OneToMany(mappedBy = "colour")
    private Set<SearchFilter> searchFilters;

    public Colour() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public Set<ProductColour> getProductColours() {
        return productColours;
    }

    public void setProductColours(Set<ProductColour> productColours) {
        this.productColours = productColours;
    }

    public Set<SearchFilter> getSearchFilters() {
        return searchFilters;
    }

    public void setSearchFilters(Set<SearchFilter> searchFilters) {
        this.searchFilters = searchFilters;
    }
}
