package com.example.sorcerersguide.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "updates")
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String heading;
    private String body;
    private String updateDate;
    private String addedBy;

}
