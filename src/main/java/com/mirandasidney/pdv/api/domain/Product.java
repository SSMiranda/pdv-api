package com.mirandasidney.pdv.api.domain;

import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

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
