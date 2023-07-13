package com.example.personjpqlservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "university_id")
    private List<Person> personList;

    @ManyToMany(mappedBy = "universities", fetch = FetchType.EAGER)
    private List<Country> countries = new ArrayList<>();

    public void addCountry(Country country) {
        this.countries.add(country);
    }
}
