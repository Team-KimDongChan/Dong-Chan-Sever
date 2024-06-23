package com.dongchan.babo.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private String secretKey;

    private long expiration;

    private long refreshExpiration;

}
