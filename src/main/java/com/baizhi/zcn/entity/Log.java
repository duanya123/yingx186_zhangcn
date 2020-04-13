package com.baizhi.zcn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private String id;
    private String adminName;
    private Date data;
    private String operation;
    private String status;
}
