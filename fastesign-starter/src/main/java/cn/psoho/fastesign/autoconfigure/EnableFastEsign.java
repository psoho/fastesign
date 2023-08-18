package cn.psoho.fastesign.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableFastEsign
 *
 * @author Alex小新 fastposter@163.com
 * @see
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FastEsignAutoConfiguration.class)
public @interface EnableFastEsign {

}
