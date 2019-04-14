package com.fdse.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
//@SpringBootApplication
//public class WxMpDemoApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(WxMpDemoApplication.class, args);
//    }
//}

@SpringBootApplication
public class WxMpDemoApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WxMpDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WxMpDemoApplication.class, args);
	}
}