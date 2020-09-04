package com.bugtracker.services.utils;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonString {
    public final String content;

    public JsonString (String content) {
        this.content = content;
    }
}
