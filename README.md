
<p align="center">
  <a href="https://github.com/psoho/fastesign" class="link github-link" target="_blank"><img style="max-width: 100px;" alt="GitHub Repo stars" src="https://img.shields.io/github/stars/psoho/fastesign?style=social"></a>
  <a href="https://gitee.com/psoho/fastesign" class="link gitee-link" target="_blank"><img style="max-width: 100px;" alt="gitee Repo stars" src="https://gitee.com/psoho/fastesign/badge/star.svg"></a>
  <img alt="csharp" src="https://img.shields.io/badge/language-java-red.svg">
  <img alt="license" src="https://img.shields.io/badge/license-MIT-blue.svg">
  <img alt="version" src="https://img.shields.io/badge/version-1.3.3-brightgreen">
</p>


## 介绍

由于e签宝官方提供的demo，杂乱无章并且没有进行API接口的封装，加密方式也比较特殊，导致开发接入成本很高。

所以特意进行了封装，方便开发人员快速接入。节省时间。

也欢迎大家对其他接口进行扩展。

## 快速上手

一、编辑pom.xml文件，添加以下依赖

```xml
<dependency>
    <groupId>cn.psoho</groupId>
    <artifactId>fastesign-starter</artifactId>
    <version>1.3.6</version>
</dependency>
```


二、增加以下配置

```yaml
fastesign:
  appId: 511******46
  secret: 603***************269f
```

三、开始使用

```java
public class EsignTest {

    @Autowired
    FastEsignService esignService;

    String docTemplateId = "d2dcab327b4******dfd1d7dd17";
    
    @Test
    void docTemplate() {
        EsignResponse<DocTemplateResponse> r = esignService.getDocTemplateService().get(docTemplateId);
        log.info("返回: r={}", JsonUtils.toPrettyJson(r));
    }

}
```


## 扩展接口

目前只封装了最常用的API接口，如果不满足需求，自己扩展新接口即可。

扩展非常方便，对照e签宝的文档，定义好请求和响应实体bean，扩展对应的service方法即可。

建议参考官方文档标准来扩展：

https://open.esign.cn/tools/api-debug


示例如下



```java
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

}
```


