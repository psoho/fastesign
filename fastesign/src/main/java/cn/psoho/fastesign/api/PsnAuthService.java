package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.bean.auth.IdentityInfoRequest;
import cn.psoho.fastesign.bean.auth.IdentityInfoResponse;
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
     * <p>
     * 【注意事项】
     * <p>
     * 入参中psnId、psnAccount和psnIDCardNum三个参数只选择一个传入即可查询个人的认证信息。
     * <p>
     * 查询优先级为 psnId > psnAccount > psnIDCardNum
     *
     * @param request
     * @return
     */
    public EsignResponse<IdentityInfoResponse> identityInfo(IdentityInfoRequest request) {
        String path = "/v3/persons/identity-info?" + request.toParam();
        return fastEsignService.get(path, IdentityInfoResponse.class);
    }

}
