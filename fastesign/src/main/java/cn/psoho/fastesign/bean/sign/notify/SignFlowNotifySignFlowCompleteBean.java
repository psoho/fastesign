package cn.psoho.fastesign.bean.sign.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * https://open.esign.cn/doc/opendoc/notify3/glqgy1
 * <p>
 * 流程结束通知
 */
@Data
public class SignFlowNotifySignFlowCompleteBean extends SignFlowNotifyBean {

    String signFlowTitle;        // 签署流程标题

    /**
     * 签署流程最终状态
     * <p>
     * 2 - 已完成（所有签署方完成签署）
     * <p>
     * 3 - 已撤销（发起方撤销签署任务）
     * <p>
     * 5 - 已过期（签署截止日到期后触发）
     * <p>
     * 7 - 已拒签（签署方拒绝签署）
     */
    String signFlowStatus;

    /**
     * 当流程非签署完成，其他原因结束时，附加原因描述
     */
    String statusDescription;

    // 签署流程创建时间（Unix时间戳格式，单位：毫秒）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date signFlowCreateTime;

    // 签署流程开启时间（Unix时间戳格式，单位：毫秒）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date signFlowStartTime;

    // 签署流程完结时间（Unix时间戳格式，单位：毫秒）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date signFlowFinishTime;


}
