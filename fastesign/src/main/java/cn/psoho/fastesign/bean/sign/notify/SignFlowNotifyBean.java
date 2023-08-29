package cn.psoho.fastesign.bean.sign.notify;

import cn.psoho.fastesign.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * https://open.esign.cn/doc/opendoc/notify3/sblzg8#kDrh0
 * <p>
 * 签署回调通知接收说明
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowNotifyBean {

    public enum ActionType {
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
        ;

    }

    private static Map<String, Class<? extends SignFlowNotifyBean>> clazzMap;

    static {
        clazzMap = new HashMap<>();
        register(ActionType.OPERATOR_READ.name(), SignFlowNotifyOperatorReadBean.class);
        register(ActionType.SIGN_FLOW_COMPLETE.name(), SignFlowNotifySignFlowCompleteBean.class);
        register(ActionType.SIGN_MISSON_COMPLETE.name(), SignFlowNotifySignMissonCompleteBean.class);
    }

    protected static void register(String name, Class<? extends SignFlowNotifyBean> clazz) {
        clazzMap.put(name, clazz);
    }

    public static SignFlowNotifyBean build(String text) {
        SignFlowNotifyBean ac = JsonUtils.parseObject(text, SignFlowNotifyBean.class);
        Class<? extends SignFlowNotifyBean> clazz = clazzMap.get(ac.getAction());
        if (clazz == null) {
            System.err.println("没有匹配的类型: " + ac.getAction());
            return ac;
        }
        return JsonUtils.parseObject(text, clazz);
    }

    public static <T extends SignFlowNotifyBean> T build(String text, Class<T> clazz) {
        return JsonUtils.parseObject(text, clazz);
    }

    /**
     * 标记该通知的业务类型
     */
    protected String action;              // 标记该通知的业务类型，该通知固定为：SIGN_FLOW_COMPLETE

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date timestamp;             // 回调通知触发时间（如重试多次均返回第一次时间，毫秒级时间戳格式）

    protected String signFlowId;          // 签署流程ID

    public boolean isAction(ActionType action) {
        return action.toString().equals(action);
    }

}
