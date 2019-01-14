package br.com.sms.urlshortener.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import br.com.sms.urlshortener.controller.ShortenerURL;
import br.com.sms.urlshortener.resource.ShortUrlMap;

@Service
public class ShortUrlMapService {

	// @Autowired
	// private ShortUrlMapRepository repository;
	private static Map<String, ShortUrlMap> repository = new HashMap<>();

	public void generateShortURL(ShortenerURL shortenerURL) throws Exception {
		validateOriginal(shortenerURL);

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte[] digest = m.digest(shortenerURL.getOriginalUrl().getBytes());
			String base64 = Base64.encodeBase64URLSafeString(digest);
			base64 = base64.substring(0, 6);
			String newLink = getUrlBase() + base64;
			ShortUrlMap shortUrlMap = create(base64, shortenerURL.getOriginalUrl());
			shortenerURL.setShortUrl(newLink);
			shortenerURL.setCreationDate(shortUrlMap.getCreationDate().toString());
			shortenerURL.setNumberAccess(shortUrlMap.getNumberAccess().toString());

		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Error generating URL", e);
		}
	}

	public ShortUrlMap getOriginalURL(String shortURL) throws Exception {
		ShortUrlMap shortener = findShortUrl(shortURL);
		if (shortener == null) {
			throw new Exception("URL not found");
		}
		shortener.setNumberAccess(shortener.getNumberAccess().intValue() + 1);
		// repository.save(shortener);
		repository.put(shortURL, shortener);
		return shortener;
	}

	private ShortUrlMap findShortUrl(String shortURL) throws Exception {
		// return repository.findByShortUrl(shortURL);
		return repository.get(shortURL);
	}

	private ShortUrlMap create(String shortURL, String originalUrl) throws Exception {
		ShortUrlMap shortUrlMap = findShortUrl(shortURL);
		if (shortUrlMap == null) {
			shortUrlMap = new ShortUrlMap();
			shortUrlMap.setShortUrl(shortURL);
			shortUrlMap.setOriginalUrl(originalUrl);
			shortUrlMap.setNumberAccess(0);
			shortUrlMap.setCreationDate(new Date());
		}
		repository.put(shortURL, shortUrlMap);
		return shortUrlMap;
	}

	private void validateOriginal(ShortenerURL shortenerURL) throws Exception {
		if (shortenerURL == null || shortenerURL.getOriginalUrl() == null) {
			throw new Exception("The original URL must be informed.");
		}

	}

	private String getUrlBase() {
		ResourceBundle rb = ResourceBundle.getBundle("application");
		return rb.getString("base_url");
	}
}
