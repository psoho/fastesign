package cn.psoho.fastesign.bean.doc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/lgb2go
 * <p>
 * 获取编辑合同模板页面
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateEditUrlRequest {

    String docTemplateId;


}
