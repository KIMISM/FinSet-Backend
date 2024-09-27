package com.kb.search.controller;

import com.kb.member.dto.Member;
import com.kb.search.dto.Keyword;
import com.kb.search.service.SearchService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Api(value = "SearchController", tags = "검색 정보")
public class SearchController {
    private final SearchService service;

    @PostMapping("") // 최신 키워드 추가
    public ResponseEntity<Keyword> create(@RequestBody Keyword keyword) {
        return ResponseEntity.ok(service.createKeyword(keyword));
    }

}