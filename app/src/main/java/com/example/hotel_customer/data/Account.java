package com.example.hotel_customer.data;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    int id, gender, accountStatus;
    String firstName, lastName;
    String email, password, accountCode;
    Date birthDay;
}
