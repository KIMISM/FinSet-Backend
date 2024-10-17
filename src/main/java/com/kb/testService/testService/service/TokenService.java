package com.kb.testService.service;


import com.kb.finance.dto.Stock;
import com.kb.testService.dto.StockToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor

public class TokenService {
    private final String BASE_URL="https://openapi.koreainvestment.com:9443";
    private final String apiUri="/oauth2/tokenP";

    private String makeUrl() throws UnsupportedEncodingException {
        return BASE_URL+apiUri;
    }
    public String makeStockUrl(String stockCode) throws UnsupportedEncodingException {
        return BASE_URL+"/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd="+stockCode;
    }

    public String makeStockChartUrl(String stockCode, String startDate, String endDate) throws UnsupportedEncodingException {
        return BASE_URL+"/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice?fid_cond_mrkt_div_code=J&fid_input_iscd="+stockCode+"&fid_input_date_1="+startDate+"&fid_input_date_2="+endDate+"&fid_period_div_code=D&fid_org_adj_prc=1";
    }

    public HttpHeaders getSymbolHeaders() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Accept", "*/*");
        headers.add("User-Agent","PostmanRuntime/7.39.0");
        headers.add("tr_id", "FHKST01010100");
        headers.add("appkey","PSGQoDtMQ8dtfkANK5tFCFTReEgcr1jZ04df");
        headers.add("appsecret","C53Fo+1Cy2m95kJ7FCWKKCELaQ/8Q6lC3Ip0FMgSRAaBAi6WuuQpGRNO2I064qtaaC0IBYSFrklbeKy5yEEIKt32TTmHMEgA8nnX7x56OHBRHU+xTjkp5E4EpTqOzOY1BlaMCdowglHd6SU5P3FbDRrphLfl0EjUzpbxDd07ee74i/JjgUY=");

        return headers;
    };


    public HttpHeaders getChartHeaders() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Accept", "*/*");
        headers.add("User-Agent","PostmanRuntime/7.39.0");
        headers.add("tr_id", "FHKST03010100");
        headers.add("appkey","PSGQoDtMQ8dtfkANK5tFCFTReEgcr1jZ04df");
        headers.add("appsecret","C53Fo+1Cy2m95kJ7FCWKKCELaQ/8Q6lC3Ip0FMgSRAaBAi6WuuQpGRNO2I064qtaaC0IBYSFrklbeKy5yEEIKt32TTmHMEgA8nnX7x56OHBRHU+xTjkp5E4EpTqOzOY1BlaMCdowglHd6SU5P3FbDRrphLfl0EjUzpbxDd07ee74i/JjgUY=");

        return headers;
    };

    public StockToken getDefaultStockToken() throws UnsupportedEncodingException {
        StockToken stockToken=new StockToken();
        stockToken.setAccessToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImExNzUwZWYxLTU0YWEtNDNmYi1iMjkxLTE0ZDkxNGY3OGJhZCIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTcyOTEyODU3OCwiaWF0IjoxNzI5MDQyMTc4LCJqdGkiOiJQU0dRb0R0TVE4ZHRma0FOSzV0RkNGVFJlRWdjcjFqWjA0ZGYifQ.EA0c27ChL3yWFPfkU54T-93dyFLW5YfhRJGWfEBPilMigG-cJQa0V-DXzN_Dkg6J5a_3KzAgFa7CGx386vU5Sw");
        stockToken.setAccessTokenTokenExpired("2024-10-17 10:29:38");
        stockToken.setTokenType("Bearer");
        stockToken.setExpiresIn(86400);
        return stockToken;
    }

    public StockToken fetch() throws UnsupportedEncodingException, ParseException {

        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate();
        // JSON
        JSONObject params= new JSONObject();

        params.put("grant_type", "client_credentials");
        params.put("appkey","PSGQoDtMQ8dtfkANK5tFCFTReEgcr1jZ04df");
        params.put("appsecret","C53Fo+1Cy2m95kJ7FCWKKCELaQ/8Q6lC3Ip0FMgSRAaBAi6WuuQpGRNO2I064qtaaC0IBYSFrklbeKy5yEEIKt32TTmHMEgA8nnX7x56OHBRHU+xTjkp5E4EpTqOzOY1BlaMCdowglHd6SU5P3FbDRrphLfl0EjUzpbxDd07ee74i/JjgUY=");

        // 헤더 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Accept", "*/*");
        headers.add("User-Agent","PostmanRuntime/7.39.0");

        // 데이터가 담긴 json을 http 객체에 담아 요청
        HttpEntity<?> entity = new HttpEntity<String>(params.toJSONString(), headers);
        ResponseEntity<String> responseEntity=restTemplate.exchange(makeUrl(), HttpMethod.POST, entity, String.class);
        System.out.println("stock Token : "+ responseEntity.getBody());


        // string을 stockToken으로변환
        StockToken stockToken = new StockToken();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONObject jsonObject = (JSONObject) obj;
        stockToken.setAccessToken(jsonObject.get("access_token").toString());
        stockToken.setTokenType(jsonObject.get("token_type").toString());
        stockToken.setAccessTokenTokenExpired(jsonObject.get("access_token_token_expired").toString());
        stockToken.setExpiresIn((long)jsonObject.get("expires_in"));

        return stockToken;
    }


}

