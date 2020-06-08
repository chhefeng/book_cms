package com.hef.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subject {
    @Id
    @GeneratedValue
    private String id;
    private String name;

}
