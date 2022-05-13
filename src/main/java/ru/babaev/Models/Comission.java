package ru.babaev.Models;


import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class Comission {
    @Expose
    public BigDecimal comission;


    public Comission(){

    }
    public Comission(BigDecimal comission){
        this.comission = comission;
    }

}
