package com.example.recyclingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetail {
    @Id
    private Integer id;

    @NotEmpty(message = "status should not be empty")
    @Column(columnDefinition = "varchar(10) check (status = \"confirmed\" or status = \"pending\")")
    @Pattern(regexp = "\\W*((?i)confirmed|pending(?-i))\\W*", message = "status should be pending or confirmed only")
    private String status;

    private LocalDateTime request_date;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request;
}
