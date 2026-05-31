package org.moonzhou.cookie.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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


	/////////////////////////////////配置http https//////////////////////////////////////
	@Bean
	public ServletWebServerFactory servletContainer() {

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

			@Override
			protected void postProcessContext(Context context) {

				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	/**
	 * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
	 * 但是不能同时在application.yml中同时配置两个connector，
	 * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
	 *
	 * @return Connector
	 */
	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(80); // http端口
		connector.setSecure(false);
		connector.setRedirectPort(9006); // application.yml中配置的https端口
		return connector;
	}
}