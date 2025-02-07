package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.bean.doc.FilesCreateByDocTemplateRequest;
import cn.psoho.fastesign.bean.doc.FilesCreateByDocTemplateResponse;
import cn.psoho.fastesign.bean.sign.*;
import lombok.RequiredArgsConstructor;

/**
 * 签署
 */
@RequiredArgsConstructor
public class SignFlowService {

    final FastEsignService fastEsignService;

    /**
     * 填写模板生成文件
     *
     * @param request
     * @return
     */
    public EsignResponse<FilesCreateByDocTemplateResponse> filesCreateByDocTemplate(FilesCreateByDocTemplateRequest request) {
        String path = "/v3/files/create-by-doc-template";
        return fastEsignService.post(path, request, FilesCreateByDocTemplateResponse.class);
    }

    /**
     * 基于文件发起签署
     *
     * @param request
     * @return
     */
    public EsignResponse<SignFlowCreateByFileResponse> createByFile(SignFlowCreateByFileRequest request) {
        String path = "/v3/sign-flow/create-by-file";
        return fastEsignService.post(path, request, SignFlowCreateByFileResponse.class);
    }

    /**
     * 添加平台方自动盖章签署区
     *
     * @param request
     * @return
     */
    public EsignResponse<SignflowsSignfieldsPlatformSignResponse> signfieldsPlatformSign(SignflowsSignfieldsPlatformSignRequest request) {
        String path = "/v1/signflows/" + request.getSignFlowId() + "/signfields/platformSign";
        return fastEsignService.post(path, request, SignflowsSignfieldsPlatformSignResponse.class);
    }

    /**
     * 获取签署页面链接
     *
     * @param request
     * @return
     */
    public EsignResponse<SignFlowSignUrlResponse> signUrl(SignFlowSignUrlRequest request) {
        String path = "/v3/sign-flow/" + request.getSignFlowId() + "/sign-url";
        return fastEsignService.post(path, request, SignFlowSignUrlResponse.class);
    }

    /**
     * 查询签署流程详情
     *
     * @param signFlowId
     * @return
     */
    public EsignResponse<SignFlowDetailResponse> detail(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/detail";
        return fastEsignService.get(path, SignFlowDetailResponse.class);
    }

    /**
     * 下载已签署文件及附属材料
     *
     * @param signFlowId
     * @return
     */
    public EsignResponse<SignFlowFileDownloadResponse> fileDownload(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/file-download-url";
        return fastEsignService.get(path, SignFlowFileDownloadResponse.class);
    }


    /**
     * 开启签署流程
     *
     * @param signFlowId
     * @return
     */
    public EsignResponse<SignFlowStartResponse> start(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/start";
        return fastEsignService.post(path, null, SignFlowStartResponse.class);
    }


    /**
     * 完结签署流程
     *
     * @param signFlowId
     * @return
     */
    public EsignResponse<SignFlowFinishResponse> finish(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/finish";
        return fastEsignService.post(path, null, SignFlowFinishResponse.class);
    }


}
