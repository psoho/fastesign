package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.bean.doc.DocTemplateEditUrlRequest;
import cn.psoho.fastesign.bean.doc.DocTemplateEditUrlResponse;
import cn.psoho.fastesign.bean.doc.DocTemplateResponse;
import cn.psoho.fastesign.bean.doc.DocTemplatesResponse;
import lombok.RequiredArgsConstructor;

/**
 * 文件&模板
 */
@RequiredArgsConstructor
public class DocTemplateService {

    final FastEsignService fastEsignService;

    /**
     * 查询合同模板中控件详情
     *
     * @param docTemplateId 合同模板文档ID
     * @return 合同模板
     */
    public EsignResponse<DocTemplateResponse> get(String docTemplateId) {
        String path = "/v3/doc-templates/" + docTemplateId;
        return fastEsignService.get(path, DocTemplateResponse.class);
    }

    /**
     * 查询合同模板列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public EsignResponse<DocTemplatesResponse> docTemplates(int pageNum, int pageSize) {
        // 注意参数需要按照排序
        String path = "/v3/doc-templates?pageNum=" + pageNum + "&pageSize=" + pageSize;
        return fastEsignService.get(path, DocTemplatesResponse.class);
    }

    /**
     * 获取编辑合同模板页面
     *
     * @param request
     * @return
     */
    public EsignResponse<DocTemplateEditUrlResponse> editUrl(DocTemplateEditUrlRequest request) {
        String path = "/v3/doc-templates/" + request.getDocTemplateId() + "/doc-template-edit-url";
        return fastEsignService.post(path, request, DocTemplateEditUrlResponse.class);
    }

}
