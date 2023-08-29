package cn.psoho.fastesign.bean.sign.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * https://open.esign.cn/doc/opendoc/notify3/ok58qr
 * <p>
 * 签署方-已读通知
 */
@Data
public class SignFlowNotifyOperatorReadBean extends SignFlowNotifyBean {

    String customBizNum;        // 自定义业务编号 该参数取值设置签署区的时候设置的customBizNum参数
    Integer signOrder;          // 当前签署顺序
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date readTime;              // 已读时间，格式：yyyy-MM-dd HH:mm:ss

    Operator operator;

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

}
