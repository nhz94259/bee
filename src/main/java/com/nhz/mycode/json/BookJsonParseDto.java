package com.nhz.mycode.json;

import lombok.Data;

@Data
@JsonPathProperty(path = "$.store.book")
public class BookJsonParseDto {

    @JsonPathProperty(path = {"$.category1","$.category"})
    private String category;
    @JsonPathProperty(path = "$.author")
    private String author;
    @JsonPathProperty(path = "$.title")
    private String title;
    @JsonPathProperty(path = "$.price",alias = "value")
    private String price;
    @JsonPathProperty(path = "$.no-exist-path-or-value-empty",alias = "other",defaultValue = "default-value")
    private String others;
}
