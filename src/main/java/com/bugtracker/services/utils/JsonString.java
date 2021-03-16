package com.bugtracker.services.utils;

import javax.xml.bind.annotation.XmlRootElement;
// TODO : Add comment
// TODO : Find out how to create custom types
@XmlRootElement
public class JsonString {
	public final String content;

	public JsonString(String content) {
		this.content = content;
	}
}
