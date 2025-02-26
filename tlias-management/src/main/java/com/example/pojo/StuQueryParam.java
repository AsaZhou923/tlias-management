package com.example.pojo;

import lombok.Data;

@Data
public class StuQueryParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;
    private Integer pageSize = 5;
}
