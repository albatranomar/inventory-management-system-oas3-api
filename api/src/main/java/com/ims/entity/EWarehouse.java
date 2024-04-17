package com.ims.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Warehouse")
public class EWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id", unique = true)
    private Long id;

    @Column(name = "warehouse_name")
    private String name;

    @Column(name = "warehouse_location")
    private String location;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "manager_id")
    private EManager manager;

    @ToString.Exclude
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = EStock.class)
    private List<EStock> stocks;
}
