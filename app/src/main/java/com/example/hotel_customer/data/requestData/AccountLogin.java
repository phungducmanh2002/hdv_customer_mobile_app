package com.example.hotel_customer.data.requestData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLogin {
    String email, password;
    int idRole;
}
