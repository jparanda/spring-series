package com.juan.spring.series.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String dni;
    private String email;

}
