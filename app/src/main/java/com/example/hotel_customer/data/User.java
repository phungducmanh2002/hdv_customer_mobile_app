package com.example.hotel_customer.data;

import com.example.hotel_customer.data.responseData.UserAvatar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    int id;
    String username;
    int idAccount, idRole, userStatus;
    UserAvatar userAvatar;
}
