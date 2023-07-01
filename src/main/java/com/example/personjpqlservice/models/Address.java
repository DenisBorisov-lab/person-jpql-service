package com.example.personjpqlservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long houseId;

    @Column(name = "street_name", nullable = false, columnDefinition = "TEXT")
    private String streetName;

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Person person;
}
