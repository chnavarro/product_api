package com.tizo.productapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Entity
@Table(name = "[PRODUCT]")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "PROD_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "PROD_NAME", nullable = false, length = 50)
    private String productName;

    @Column(name = "PROD_COST", precision = 10, scale = 2)
    private BigDecimal productCost;

    @Column(name = "PROD_PRICE", precision = 10, scale = 2)
    private BigDecimal productPrice;

    @Column(name = "PROD_TAGS")
    private String productTags;

    @Column(name = "PROD_CREATE_DATE", insertable = false, updatable = false, nullable = false)
    @Temporal( value = TemporalType.TIMESTAMP )
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date productCreateDate;

    @Temporal( value = TemporalType.TIMESTAMP )
    @Column(name = "PROD_LAST_UPDATE")
    private Date productLastUpdate;

    @Column(name = "PROD_STATUS", columnDefinition = "BIT")
    @ColumnDefault("1")
    private Boolean productStatus;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "CAT_ID", nullable = false)
    private Category category;
}
