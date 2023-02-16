package com.example.javaee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Category
{
    int category_id;
    String name;
    Date create_time;
    int state;
}
