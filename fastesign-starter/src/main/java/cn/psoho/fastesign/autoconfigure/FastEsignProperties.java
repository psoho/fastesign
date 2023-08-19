package cn.psoho.fastesign.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * spring boot starter properties
 *
 * @author Alex小新 fastposter@163.com
 * @see 
 */
@Data
@ConfigurationProperties("fastesign")
public class FastEsignProperties{

    /**
     * 是否沙盒模式
     */
    boolean sandbox;

    /**
     * 是否开启调试
     */
    boolean debug;

    /**
     * appId
     */
    String appId;

    /**
     * 密钥
     */
    String secret;

}
