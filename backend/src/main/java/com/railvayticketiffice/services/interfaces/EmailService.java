package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;

public interface EmailService {

    void sendEmail(User user, Ticket ticket, String to);

}
