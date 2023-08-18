package cn.psoho.fastesign.autoconfigure;

import cn.psoho.fastesign.api.FastEsignService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FastEsignAutoConfiguration AutoConfiguration
 *
 * @author Alex小新 fastposter@163.com
 * @see
 */
@Configuration
@AllArgsConstructor
@ComponentScan(basePackages = {"cn.psoho.fastesign"})
@EnableConfigurationProperties(FastEsignProperties.class)
public class FastEsignAutoConfiguration {

    private final FastEsignProperties properties;

    @Bean
    @ConditionalOnProperty(value = "fastesign.appId")
    FastEsignService esignService() {
        FastEsignService bean = new FastEsignService();
        bean.setDebug(properties.isDebug());
        bean.setSandbox(properties.isSandbox());
        bean.setSecret(properties.getSecret());
        bean.setAppId(properties.getAppId());
        return bean;
    }

}
