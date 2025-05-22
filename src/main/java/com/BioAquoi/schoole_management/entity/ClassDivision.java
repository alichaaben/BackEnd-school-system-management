package com.BioAquoi.schoole_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_divisions")
public class ClassDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    
    @OneToMany(mappedBy = "classDivision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;
    
    @OneToMany(mappedBy = "classDivision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;
}
