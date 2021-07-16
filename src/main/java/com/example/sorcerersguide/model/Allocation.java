package com.example.sorcerersguide.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "allocations")
public class Allocation {

    @Id
    @Column(unique = true)
    private String caseId;
    private String asin;
    private String reviewerId;
    private String managerId;
    private String date;
    private String isCompleted;
    @Column(columnDefinition = "TEXT")
    private String comment;

}
