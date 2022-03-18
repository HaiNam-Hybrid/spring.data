package com.example.spring.data.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public Date formatDateTime(String sDate) throws Exception {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        return formatter1.parse(sDate);
    }
}
