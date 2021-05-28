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
@Table(name = "queries")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long queryId;
    private String queryLink;
    @Column(columnDefinition = "TEXT")
    private String queryQuestion;
    private String queryRequester;
    private String queryStatus;
    private String resolverLogin;
    @Column(columnDefinition = "TEXT")
    private String resolverResponse;
    private String createdDate;
    private String responseDate;

}
