package com.example.hotel_customer.data;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    public static boolean canCancle(Booking booking){
        return booking.getBookingStatus() == 1 || booking.getBookingStatus() == 2;
    }

    int id, idRoom, createBy, bookingStatus;
    int paymentAmount, peopleNum;
    String customerName, customerPhoneNumber, customerEmail;
    Date checkinDate, checkoutDate;
}
