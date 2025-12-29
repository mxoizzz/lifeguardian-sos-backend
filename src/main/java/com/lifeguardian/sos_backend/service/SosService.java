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

        // ✅ Live tracking web page URL
        String trackingUrl =
                "https://lifeguardianplus-daeb0.web.app/track.html?uid="
                        + request.getUid();

        String messageBody = """
🚨 SOS ALERT 🚨

%s needs immediate help!

📍 Live Location (updates in real-time):
%s

⚠️ Please open this link to track the location live.

Sent via LifeGuardian+
""".formatted(request.getUserName(), trackingUrl);

        for (String to : request.getContacts()) {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    messageBody
            ).create();
        }
    }
}
