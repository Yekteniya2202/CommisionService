package ru.babaev.Models.Response;


import com.google.gson.annotations.Expose;
import ru.babaev.Models.Comission;

import java.io.IOException;
import java.math.BigDecimal;

public class ComissionResponse extends Response{
    @Expose
    private BigDecimal comission;

    public ComissionResponse() {
    }
    public ComissionResponse(BigDecimal comission, int statusCode, String description) throws IOException {
        super(statusCode, description);
        this.comission = comission;
    }


    public boolean isValid(){
        if (comission != null || super.isValid()){
            return true;
        }
        return false;
    }
}