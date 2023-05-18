package com.gh.boot.demo.data;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/5/17
 */
@Data
@Builder
public class JSONTestClassA {
    private String a;
    private Integer b;
    private int c;
    private Double d;
    @SerializedName("word")
    private double e;
    private Boolean f;
    private boolean g;
    private Long h;
    private long i;
    private Float j;
    private float k;
    private Short l;
    private short m;
    private Byte n;
    private byte o;
    private Character p;
    private char q;
    private BigDecimal r;
    private Date s;
    private java.sql.Date t;
    private LocalDateTime time;
    private Timestamp timestamp;
}
