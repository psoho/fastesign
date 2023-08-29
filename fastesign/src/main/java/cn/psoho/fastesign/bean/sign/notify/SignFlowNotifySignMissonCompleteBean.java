package cn.psoho.fastesign.bean.sign.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * https://open.esign.cn/doc/opendoc/notify3/zzcdf8
 * <p>
 * 签署方-签署结果通知
 */
@Data
public class SignFlowNotifySignMissonCompleteBean extends SignFlowNotifyBean {

    String customBizNum;        // 自定义业务编号 该参数取值设置签署区的时候设置的customBizNum参数
    Integer signOrder;          // 当前签署顺序

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date operateTime;              // 已读时间，格式：yyyy-MM-dd HH:mm:ss

    Integer signResult;             // 签署结果：2 - 签署完成，3 - 失败，4 - 拒签

    String resultDescription;       // 拒签或失败时，附加的原因描述

    Operator operator;              // 操作人信息

    Organization organization;      // 机构签署方

    @Data
    public static class Operator {
        String psnId;
        PsnAccount psnAccount;

        @Data
        public static class PsnAccount {
            String accountMobile;
            String accountEmail;
        }
    }

    @Data
    public static class Organization {
        String orgId;
        String orgName;
    }

    // 是否签署完成
    public boolean isSigned() {
        return Integer.valueOf(2).equals(signResult);
    }

    // 签署失败
    public boolean isFailure() {
        return Integer.valueOf(3).equals(signResult);
    }

    // 拒签
    public boolean isReject() {
        return Integer.valueOf(4).equals(signResult);
    }

}
