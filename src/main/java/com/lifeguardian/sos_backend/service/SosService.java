package com.lifeguardian.sos_backend.service;

import com.lifeguardian.sos_backend.dto.SosRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SosService {

    @Value("${twilio.whatsapp-from}")
    private String from;

    public void sendSos(SosRequest request) {

        String locationUrl =
                "https://maps.google.com/?q=" +
                request.getLatitude() + "," +
                request.getLongitude();

        String messageBody = """
            🚨 SOS ALERT 🚨

            %s needs immediate help!

            📍 Location:
            %s

            Sent via LifeGuardian+
            """.formatted(request.getUserName(), locationUrl);

        for (String to : request.getContacts()) {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    messageBody
            ).create();
        }
    }
}
