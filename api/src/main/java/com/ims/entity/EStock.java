package com.ims.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Stock")
public class EStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", unique = true)
    private Long id;

    @Column(name = "stock_name")
    private String name;

    @Column(name = "stock_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "stock_tags")
    @ElementCollection
    @CollectionTable(name = "stocks_tags", joinColumns = @JoinColumn(name = "stock_id"))
    private Set<String> tags;

    @Column(name = "stock_available")
    private BigInteger availableQuantity;

    @Column(name = "stock_price")
    private BigDecimal price;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private EWarehouse warehouse;

    public enum Status {
        AVAILABLE,
        RESERVED,
        OUT_OF_STOCK,
        DAMAGED,
        DISCONTINUED
    }
}