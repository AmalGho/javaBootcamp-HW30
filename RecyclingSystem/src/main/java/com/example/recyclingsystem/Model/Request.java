package com.example.recyclingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "waste type should not be empty")
    @Column(columnDefinition = "varchar(40) not null")
    private String waste_type;

    @NotNull(message = "weight should not be empty")
    @Column(columnDefinition = "double not null")
    private Double weight;


    @ManyToOne
    @JoinColumn(name = "resident_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "company_id" , referencedColumnName = "user_id")
    @JsonIgnore
    private Company company;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    @PrimaryKeyJoinColumn
    private RequestDetail requestDetail;

}
