# gh-jasperreports (jasperreports报表功能包)

### 配置
```
http:
  encoding.charset: UTF-8
  encoding.enable: true
  encoding.force: true

web:
  agent:
    predicate-name: gh-wr
springdoc:
  model-and-view-allowed: true
  api-docs:
    title: 报表服务api接口文档
    description: 报表服务api接口文档
    terms-of-service: https://github.com/ghuan
gh-jasperreports:
  ## 报表px图片所在服务地址，用于报表px图片地址修正
  px-image-server-address: #http://localhost:1001


