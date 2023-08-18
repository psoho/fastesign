package cn.psoho.fastesign.bean.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/auth3/rx8igf
 * <p>
 * 获取个人认证&授权页面链接
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsnAuthResponse {
    String authFlowId;
    String authUrl;
    String authShortUrl;
}
