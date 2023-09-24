package com.abhinav.kaiburr;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "OS")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OSSystem {

    @Id
    private Integer id;
    private String name;
    private String language;
    private String framework;



}
