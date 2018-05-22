package com.lexach.ClothingFeedParsers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Product")

// EntityListener для инжекта значения в поля под аннотацией @CreatedDate.
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idRetailer", nullable = false)
    private Long idRetailer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "idCategory", nullable = false)
    private Long idCategory;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "brandName", nullable = false)
    private String brandName;

    @Column(name = "url", nullable = false)
    private String url;

    // TODO add spring CREATEDAT annotation

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date updaedAt;

    @Column(name = "idGeder")
    private Long idGender;

}
