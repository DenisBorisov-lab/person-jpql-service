package com.example.personjpqlservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "service_name", nullable = false, columnDefinition = "TEXT")
    private String serviceName;

    @Column(name = "phone_number", nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
