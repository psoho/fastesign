## 介绍


由于官方提供的demo，没有进行API接口的封装，导致使用成本很高，维护也麻烦。

项目中使用到了e签宝，所以特意进行了封装。

## 快速上手

一、编辑pom.xml文件，添加以下依赖

```xml
<dependency>
    <groupId>cn.psoho</groupId>
    <artifactId>fastesign-starter</artifactId>
    <version>1.0.0</version>
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

```


## 扩展接口

目前只封装了最常用的API接口。

如果不满足需求，自己扩展新接口即可。

扩展非常方便，定义好请求和响应实体，扩展对应的service方法即可。

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


