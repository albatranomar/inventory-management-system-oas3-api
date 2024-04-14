package com.ims.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Provider")
public class EProvider {
    @Id
    @GeneratedValue
    @Column(name = "provider_id", unique = true)
    private Long id;

    @Column(name = "provider_name")
    private String name;

    @Column(name = "provider_type")
    private String type;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "stock_provider",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<EStock> stocks;
}
