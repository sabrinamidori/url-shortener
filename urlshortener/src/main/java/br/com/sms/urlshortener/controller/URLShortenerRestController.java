package br.com.sms.urlshortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.urlshortener.resource.ShortUrlMap;
import br.com.sms.urlshortener.service.ShortUrlMapService;

@RestController
public class URLShortenerRestController {
	@Autowired
	private ShortUrlMapService shortenerURLService;

	@RequestMapping(value = "/shorturl", method = RequestMethod.POST)
	public ResponseEntity<Object> getShortUrl(@RequestBody ShortenerURL shortUrl) throws Exception {
		shortenerURLService.generateShortURL(shortUrl);

		return new ResponseEntity<Object>(shortUrl, HttpStatus.OK);
	}

	@RequestMapping(value = "/sms/{linkId}", method = RequestMethod.GET)
	public void getOriginalURL(HttpServletResponse response, @PathVariable("linkId") String linkId) throws Exception {
		ShortUrlMap originalURL = shortenerURLService.getOriginalURL(linkId);

		response.sendRedirect(originalURL.getOriginalUrl());
	}
}
