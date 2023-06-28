package com.agussuhardi.springdemofull.vo;


import com.agussuhardi.springdemofull.validation.CategoryValidate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;


public record ProductUpdateVO(

        @NotNull(message = "name can not null")
        String name,

        String text,

        String image,
        @NotNull(message = "category can not null")
        @CategoryValidate
        String categoryId
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
