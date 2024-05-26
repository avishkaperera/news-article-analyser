package com.reprisk.analyser.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Only necessary fields are included for the purpose of this assignment
 */

@Data
@JacksonXmlRootElement(localName = "news-item")
@AllArgsConstructor
public class NewsArticle {

    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty
    private String text;
}
