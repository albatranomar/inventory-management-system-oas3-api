package com.ims.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Manager")
public class EManager {
    @Id
    @GeneratedValue
    @Column(name = "manager_id", unique = true)
    private Long id;

    @Column(name = "manager_name")
    private String name;

    @Column(name = "manager_phone_number")
    private String phoneNumber;

    @ToString.Exclude
    @OneToOne(mappedBy = "manager")
    private EWarehouse warehouse;
}
