# gh-common-data-redis 
## spring-data-redis封装及reddison封装

### 配置
```
spring:
  servlet:
    multipart:
      # 单个文件最大大小
      max-file-size: 100MB
      # 单次请求最大大小
      max-request-size: 100MB
knife4j:
  enable: true
  setting:
    language: zh-CN
    swagger-model-name: 实体类列表
    enable-footer-custom: true
    footer-custom-content: Apache License 2.0 | Copyright  2023 - [github](https://github.com/ghuan)
  home:
    title: knife4j示例接口文档
    description: knife4j示例接口文档111
    author: huan.gao
    terms-of-service: github
    version: 1.0.0
  group-configs:

    - group: '文件相关'
      paths-to-match: '/download/**'
      packages-to-scan: com.gh.common.knife4j.controller

    - group: '用户模块'
      paths-to-match:
        - '/user/**'
        - '/download/**'
  cors: false
  production: false
  basic:
    enable: false
    username: test
    password: 12313