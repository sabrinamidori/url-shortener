package br.com.sms.urlshortener.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenerURL {
	private String originalUrl;
	private String shortUrl;
	private String numberAccess;
	private String creationDate;
}
