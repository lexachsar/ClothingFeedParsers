package com.lexach.clothing.feed.parsers.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "gender")
    private Set<Product> products;

    @OneToMany(mappedBy = "gender")
    private Set<User> users;

    @OneToMany(mappedBy = "gender")
    private Set<SearchFilter> searchFilters;

    public Gender() {
    }

    public Gender(String name) {
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<SearchFilter> getSearchFilters() {
        return searchFilters;
    }

    public void setSearchFilters(Set<SearchFilter> searchFilters) {
        this.searchFilters = searchFilters;
    }
}
