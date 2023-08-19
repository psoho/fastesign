package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.bean.auth.*;
import lombok.RequiredArgsConstructor;

/**
 * 授权认证
 */
@RequiredArgsConstructor
public class PsnAuthService {

    final FastEsignService fastEsignService;

    /**
     * https://open.esign.cn/doc/opendoc/auth3/rx8igf
     * <p>
     * 获取个人认证&授权页面链接
     *
     * @param request
     * @return
     */
    public EsignResponse<PsnAuthResponse> authUrl(PsnAuthRequest request) {
        String path = "/v3/psn-auth-url";
        return fastEsignService.post(path, request, PsnAuthResponse.class);
    }

    /**
     * https://open.esign.cn/doc/opendoc/auth3/vssvtu
     * <p>
     * 查询个人认证信息
     *
     * @param request
     * @return
     */
    public EsignResponse<IdentityInfoResponse> identityInfo(IdentityInfoRequest request) {
        String path = "/v3/persons/identity-info?" + request.toParam();
        return fastEsignService.get(path, IdentityInfoResponse.class);
    }


    /**
     * https://open.esign.cn/doc/opendoc/auth3/nurtvw
     * <p>
     * 查询个人用户授权详情
     *
     * @param psnId
     * @return
     */
    public EsignResponse<AuthorizedInfoResponse> authorizedInfo(String psnId) {
        String path = "/v3/persons/identity-info?psnId=" + psnId;
        return fastEsignService.get(path, AuthorizedInfoResponse.class);
    }

}
