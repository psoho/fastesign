package cn.psoho.fastesign.bean.sign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/kczf8g
 *
 * <p>
 * 下载已签署文件及附属材料
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowFileDownloadResponse {

    private List<Files> files; // 签署文件信息
    private List<Attachments> attachments; // 附属材料信息

    @Data
    public static class Files {
        private String fileId; // 签署文件ID
        private String fileName; // 签署文件名称
        private String downloadUrl; // 已签署文件下载链接（有效期60分钟）
    }

    @Data
    public static class Attachments {
        private String fileId; // 附属材料文件ID
        private String fileName; // 附属材料文件名称
        private String downloadUrl; // 附属材料文件下载链接（有效期60分钟）
    }

}

