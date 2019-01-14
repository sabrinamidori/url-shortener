package br.com.sms.urlshortener.repository;

import br.com.sms.urlshortener.resource.ShortUrlMap;

//@Repository
public interface ShortUrlMapRepository { // extends CrudRepository<ShortUrlMap, Long> {
	// @Query("from ShortUrlMap where shortUrl = :urlKey")
	public ShortUrlMap findByShortUrl(String urlKey);

}
