package cn.psoho.fastesign.bean.auth;

import cn.psoho.fastesign.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/auth3/vssvtu
 * <p>
 * 查询个人认证信息
 * <p>
 * 【注意事项】
 * <p>
 * 入参中psnId、psnAccount和psnIDCardNum三个参数只选择一个传入即可查询个人的认证信息。
 * <p>
 * 查询优先级为 psnId > psnAccount > psnIDCardNum
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityInfoRequest {

    /**
     * 个人账号ID
     */
    String psnId;

    /**
     * 个人账号ID
     */
    String psnAccount;

    /**
     * 个人账号ID
     */
    String psnIDCardNum;

    /**
     * 个人账号ID
     */
    PsnIDCardType psnIDCardType;

    /**
     * 获取参数
     *
     * @return
     */
    public String toParam() {
        List<String> params = new ArrayList<>();
        if (StringUtils.isNotBlank(psnAccount)) {
            params.add("psnAccount=" + psnAccount);
        }
        if (StringUtils.isNotBlank(psnId)) {
            params.add("psnId=" + psnId);
        }
        if (StringUtils.isNotBlank(psnIDCardNum)) {
            params.add("psnIDCardNum=" + psnIDCardNum);
        }
        if (psnIDCardType != null) {
            params.add("psnIDCardType=" + psnIDCardType.toString());
        }
        if (params.isEmpty()) {
            return null;
        }
        return String.join("&", params);
    }


    public enum PsnIDCardType {
        /**
         * 中国大陆居民身份证
         */
        CRED_PSN_CH_IDCARD,

        /**
         * 香港来往大陆通行证
         */
        CRED_PSN_CH_HONGKONG,

        /**
         * 澳门来往大陆通行证
         */
        CRED_PSN_CH_MACAO,

        /**
         * 台湾来往大陆通行证
         */
        CRED_PSN_CH_TWCARD,

        /**
         * 护照
         */
        CRED_PSN_PASSPORT;

    }

}
