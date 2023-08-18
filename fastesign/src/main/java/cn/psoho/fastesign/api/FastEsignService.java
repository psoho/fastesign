package cn.psoho.fastesign.api;

import cn.psoho.fastesign.bean.EsignResponse;
import cn.psoho.fastesign.utils.Base64Utils;
import cn.psoho.fastesign.utils.DigestUtils;
import cn.psoho.fastesign.utils.JsonUtils;
import cn.psoho.fastesign.utils.StringUtils;
import lombok.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FastEsignService {

    /**
     * 是否沙盒模式
     */
    @Setter
    boolean sandbox;

    /**
     * 是否开启调试
     */
    @Setter
    boolean debug;

    /**
     * appId
     */
    @Setter
    String appId;

    /**
     * 密钥
     */
    @Setter
    String secret;

    @Getter
    SignFlowService signFlowService = new SignFlowService(this);

    @Getter
    DocTemplateService docTemplateService = new DocTemplateService(this);

    @Getter
    PsnAuthService psnAuthService = new PsnAuthService(this);

    // 沙盒地址
    private static final String endpointSandbox = "https://smlopenapi.esign.cn";
    private static final String endpoint = "https://openapi.esign.cn";
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

    protected OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .addInterceptor(new Interceptor() {

                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
//                    System.out.println(chain.request());
                    if (debug) {
                        System.out.println("----------------------------------------------------");
                        System.out.println(chain.request().url().url());
                        System.out.println("-------------");
                        System.out.println("headers");
                        chain.request().headers().forEach(item -> {
                            System.out.println(item.getFirst() + ": " + item.getSecond());
                        });
                        if (chain.request().body() != null) {
                            System.out.println("-------------");
                            System.out.println("body");
                        }
                        System.out.println("----------------------------------------------------");
                        System.out.println();
                    }
                    return chain.proceed(chain.request());
                }
            })

            .build();

    @SuppressWarnings("ALL")
    public <T> EsignResponse<T> post(String path, Object request, Class<T> clazz) {
        String text = request(path, "POST", JsonUtils.toJson(request));
        return JsonUtils.parseObject(text, EsignResponse.class, clazz);
    }


    @SuppressWarnings("ALL")
    public <T> EsignResponse<T> get(String path, Class<T> clazz) {
        String text = request(path, "GET", null);
        return JsonUtils.parseObject(text, EsignResponse.class, clazz);
    }

    @NotNull
    @SneakyThrows
    protected String request(String path, String method, String payload) {

        String url = getUrl(path);

        //
        // 请求签名鉴权方式说明：https://open.esign.cn/doc/opendoc/dev-guide3/tggw2e
        //
        String contentMD5 = StringUtils.isBlank(payload) ? "" : Base64Utils.encodeToString(DigestUtils.md5Digest(payload.getBytes()));

//        // 解决URL参数排序问题
//        UriComponents uc = UriComponentsBuilder.fromUriString(url).build();
//        MultiValueMap<String, String> map = uc.getQueryParams();
//        String query = map.keySet().stream().sorted().map(item -> item + "=" + map.get(item).get(0)).collect(Collectors.joining("&"));
//        query = StringUtils.isBlank(query) ? "" : "?" + query;
//        String pathAndParameters = uc.getPath() + query;

        String pathAndParameters = url.replace(getUrl(""), "");

        String signString = String.join("\n", method, "*/*", contentMD5, CONTENT_TYPE, "", pathAndParameters);
        String sign = doSignatureBase64(signString, secret);

        if (debug) {
            System.out.println("signString=" + signString);
            System.out.println("sign=" + sign);
        }

        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("X-Tsign-Open-App-Id", appId)
                .header("X-Tsign-Open-Auth-Mode", "Signature")
                .header("X-Tsign-Open-Ca-Timestamp", String.valueOf(System.currentTimeMillis()))
                .header("X-Tsign-Open-Ca-Signature", sign)
                .header("Date", "")
                .header("Accept", "*/*")
                .header("Content-MD5", contentMD5)
                .header("Content-Type", CONTENT_TYPE);

        RequestBody body = null;
        if (StringUtils.isNotBlank(payload)) {
            body = RequestBody.create(payload.getBytes(), MediaType.parse(CONTENT_TYPE));
        }
        builder.method(method, body);

        Request httpRequest = builder.build();
        Response r = httpClient.newCall(httpRequest).execute();
        if (!r.isSuccessful()) {
            System.err.println("请求异常: r=" + r);
            return null;
        }

        String text = r.body().string();
        if (debug) {
            System.out.println(text);
            System.out.println();
        }

        return text;
    }

    @NotNull
    private String getUrl(String path) {
        return (sandbox ? endpointSandbox : endpoint) + path;
    }


    /***
     * 计算请求签名值
     *
     * @param message 待签名字符串
     * @param secret  密钥APP KEY
     * @return HmacSHA256计算后摘要值的Base64编码
     * @throws Exception 加密过程中的异常信息
     */
    @SneakyThrows
    private String doSignatureBase64(String message, String secret) {
        String algorithm = "HmacSHA256";
        Mac hmacSha256;
        hmacSha256 = Mac.getInstance(algorithm);
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
        // 使用HmacSHA256对二进制数据消息Bytes计算摘要
        byte[] digestBytes = hmacSha256.doFinal(messageBytes);
        // 把摘要后的结果digestBytes使用Base64进行编码
        return Base64Utils.encodeToString(digestBytes);
    }


}
