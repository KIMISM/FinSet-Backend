package com.kb.testService.controller;


import com.kb.testService.dto.StockToken;
import com.kb.testService.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {
    private final TokenService tokenService;
    @GetMapping("")
    public ResponseEntity<StockToken> fetchToken() throws UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok(tokenService.fetch());
    }


}
