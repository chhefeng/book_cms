package com.hef.book.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookQuery {

    private String title;
    private Long subjectId;
}
