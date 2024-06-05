package com.example.hotel_customer.ui.core.myui.components.item.pickDate;

import com.example.hotel_customer.ui.core.events.I_OnEvent;

import java.util.Date;

public interface I_OnPickDate extends I_OnEvent {
    public void execute(Date date);
}
