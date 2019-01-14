package br.com.sms.urlshortener.resource;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "sum_short_url_map")
public class ShortUrlMap {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@Column(name = "sum_ds_short_url", length = 6, nullable = false, unique = true)
	private String shortUrl;
	//@Column(name = "sum_ds_original_url", length = 300, nullable = false)
	private String originalUrl;
	//@Column(name = "sum_nu_access")
	private Integer numberAccess;
	//@Column(name = "sum_dt_creation")
	//@Temporal(TemporalType.TIMESTAMP)
	//@CreatedDate
	private Date creationDate;

}
