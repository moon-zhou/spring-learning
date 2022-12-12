package org.moonzhou.cookie.config;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration {
	@Bean
	public TomcatContextCustomizer sameSiteCookiesConfig() {
		return context -> {
			final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
			// 设置Cookie的SameSite
			cookieProcessor.setSameSiteCookies(SameSiteCookies.LAX.getValue());
			context.setCookieProcessor(cookieProcessor);
		};
	}

	// spring session
	/*@Bean
	public CookieSerializer cookieSerializer() {
		DynamicCookieMaxAgeCookieSerializer serializer = new DynamicCookieMaxAgeCookieSerializer();
		serializer.setCookieName("JSESSIONID");
		serializer.setDomainName("localhost");
		serializer.setCookiePath("/");
		serializer.setCookieMaxAge(3600);
		serializer.setSameSite("Lax");  // 设置SameSite属性
		serializer.setUseHttpOnlyCookie(true);
		serializer.setUseSecureCookie(false);
		return serializer;
	}*/
}