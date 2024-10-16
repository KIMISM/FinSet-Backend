package com.kb.dict.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDTO {
    private long dino;
    private String word;
    private String content;
    int status;
}
