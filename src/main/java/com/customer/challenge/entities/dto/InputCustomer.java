package com.customer.challenge.entities.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InputCustomer {

    private Long idCustomer;

    private String name;

    private String email;

    private String cpf;

    private Date dateOfBirth;
}
