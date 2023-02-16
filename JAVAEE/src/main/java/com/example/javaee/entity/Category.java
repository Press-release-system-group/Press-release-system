package com.example.javaee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category
{
    int category_id;
    String name;
    Date create_time;
    int state;
}
