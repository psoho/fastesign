package cn.psoho.fastesign.bean.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/auth3/vssvtu
 * <p>
 * 查询个人认证信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityInfoResponse {

    /**
     * 用户在e签宝的实名认证状态
     * <p>
     * 0 - 未实名，1 - 已实名
     */
    Integer realnameStatus;

    /**
     * 是否授权相关信息给当前应用
     * <p>
     * true - 已授权，false - 未授权
     */
    Boolean authorizeUserInfo;

    /**
     * 个人账号ID
     */
    String psnId;

}
