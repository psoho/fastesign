package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.bean.auth.PsnAuthRequest;
import cn.psoho.fastesign.bean.auth.PsnAuthResponse;
import lombok.RequiredArgsConstructor;

/**
 * 授权认证
 */
@RequiredArgsConstructor
public class PsnAuthService {

    final FastEsignService fastEsignService;

    /**
     * 获取个人认证&授权页面链接
     * https://open.esign.cn/doc/opendoc/auth3/rx8igf
     *
     * @param request
     * @return
     */
    public EsignResponse<PsnAuthResponse> authUrl(PsnAuthRequest request) {
        String path = "/v3/psn-auth-url";
        return fastEsignService.post(path, request, PsnAuthResponse.class);
    }

}
