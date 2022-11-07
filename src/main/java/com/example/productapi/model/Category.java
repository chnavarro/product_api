package com.example.productapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Entity
@Table(name = "[CATEGORY]")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "CAT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "CAT_NAME", nullable = false, length = 50)
    private String categoryName;

    @Column(name = "CAT_CREATE_DATE", insertable = false, updatable = false, nullable = false)
    @Temporal( value = TemporalType.TIMESTAMP )
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date categoryCreateDate;

    @Temporal( value = TemporalType.TIMESTAMP )
    @Column(name = "CAT_LAST_UPDATE")
    private Date categoryLastUpdate;

    @Column(name = "CAT_STATUS", columnDefinition = "BIT")
    @ColumnDefault("1")
    private Boolean categoryStatus;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "category")
    @Setter(AccessLevel.PRIVATE)
    private List<Product> products = new ArrayList<>();

}
