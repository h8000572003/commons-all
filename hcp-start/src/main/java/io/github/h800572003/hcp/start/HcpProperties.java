package io.github.h800572003.hcp.start;

import io.github.h800572003.hcp.HcpOption;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "h800572003.hcp")
public class HcpProperties {


    private String rest;
    private String token;




}
