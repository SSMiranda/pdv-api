package com.mirandasidney.pdv.api.domain;

import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal costPrice;

    @Getter
    @Setter
    private BigDecimal salePrice;

    @Getter
    @Setter
    private Integer amount;

    @Getter
    @Setter
    private String createdAt = Util.formatDate();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
