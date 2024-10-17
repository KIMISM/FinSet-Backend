package com.kb.testService.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockToken {
    private String accessToken;
    private String accessTokenTokenExpired;
    private String tokenType;
    private long expiresIn;
}
