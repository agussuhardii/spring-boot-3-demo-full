package com.agussuhardi.springdemofull.vo;


import com.google.common.base.Strings;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class QueryVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String q;

    public String getQ() {
        if (Strings.isNullOrEmpty(this.q)) return "";
        return q;
    }
}
