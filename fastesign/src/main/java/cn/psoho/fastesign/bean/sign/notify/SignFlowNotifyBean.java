package cn.psoho.fastesign.bean.sign.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * https://open.esign.cn/doc/opendoc/notify3/sblzg8#kDrh0
 * <p>
 * 签署回调通知接收说明
 */
@Data
public class SignFlowNotifyBean {

    /**
     * 是否是某个指定的Action
     *
     * @param action
     * @return boolean
     */
    public boolean isAction(Action action) {
        return action != null && action.toString().equals(this.action);
    }

    /**
     * 是否签署完成
     *
     * @return boolean
     */
    public boolean isSigned() {
        return Integer.valueOf(2).equals(this.signResult);
    }

    /**
     * 是否拒签
     *
     * @return boolean
     */
    public boolean isReject() {
        return Integer.valueOf(4).equals(this.signResult);
    }

    public enum Action {
        OPERATOR_READ // 签署方-已读通知
        , SIGN_MISSON_COMPLETE // 签署方-签署结果通知
        , SIGN_FLOW_COMPLETE // 流程结束通知
        , SIGN_FLOW_INITIATED // 签署发起成功通知
        , OPERATOR_CORRECT_IDENTITY // 签署人更正个人信息回调通知
        , TRANSMISS_SIGN // 经办人转交签署任务通知
        , FILE_UNAVAILABLE // 文件已加密/已损坏通知
        , SIGN_SEAL_EXAMINE_REJECTED // 用印审批驳回通知
        , SIGN_FILE_RESCISSION_INITIATE // 合同发起解约通知
        , SIGN_FILE_RESCINDED // 合同解约成功通知
    }

    /**
     * 标记该通知的业务类型
     */
    String action;              // 标记该通知的业务类型，该通知固定为：SIGN_FLOW_COMPLETE
    Date timestamp;             // 回调通知触发时间（如重试多次均返回第一次时间，毫秒级时间戳格式）
    String signFlowId;          // 签署流程ID
    String customBizNum;        // 自定义业务编号 该参数取值设置签署区的时候设置的customBizNum参数
    Integer signOrder;          // 当前签署顺序
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date readTime;              // 已读时间，格式：yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date operateTime;

    /**
     * 签署结果
     * <p>
     * 2 - 签署完成，3 - 失败，4 - 拒签
     */
    Integer signResult;
    /**
     * 拒签或失败时，附加的原因描述
     */
    String resultDescription;

    /**
     * 操作人信息
     */
    Operator operator;

    /**
     * 机构签署方
     */
    Organization organization;

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

}
