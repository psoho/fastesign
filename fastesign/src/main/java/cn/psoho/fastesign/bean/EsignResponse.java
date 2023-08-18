package cn.psoho.fastesign.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * e签宝响应
 *
 * @param <T>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsignResponse<T> {

    /**
     * 编码
     */
    Integer code;

    /**
     * 消息
     */
    String message;

    /**
     * 类型
     */
    T data;

}
