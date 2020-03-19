package com.ch.example.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Description: TODO
 * @Author chenhao
 * @Date 2020/3/19
 **/
@Data
@Document(indexName = "esdemo",type = "demo")
public class EsDemo {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("description")
    private String description;
    @ApiModelProperty("startTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startTime;
}
