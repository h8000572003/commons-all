package io.github.h800572003.hcp.start;


import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.HcpService;
import io.github.h800572003.hcp.IHcpService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(HcpProperties.class)
public class HcpAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public IHcpService hcpService(HcpProperties hcpProperties) throws Exception {
        HcpOption hcpOption = new HcpOption(hcpProperties.getRest(), hcpProperties::getToken);
        return new HcpService(hcpOption);
    }
}
