package com.example.sorcerersguide.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "updates")
public class Update {

    @Id
    @Column(unique = true)
    private String id;
    @Column(columnDefinition = "TEXT")
    private String heading;
    @Column(columnDefinition = "TEXT")
    private String body;
    private String updateDate;
    private String addedBy;

}
