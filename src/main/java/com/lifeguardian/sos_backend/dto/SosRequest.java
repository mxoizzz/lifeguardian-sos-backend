package com.lifeguardian.sos_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class SosRequest {
    private String userName;
    private double latitude;
    private double longitude;
    private List<String> contacts;
}
