package com.example.spring.data.model;

import java.io.Serializable;
import java.time.Instant;

public class BaseModel implements Serializable {

    private Instant modifiedDate;


    public BaseModel(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
