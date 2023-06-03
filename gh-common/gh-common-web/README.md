# gh-commons-web (springboot web包)
定义了全局异常处理器,响应信息封装,异步线程池配置等

### 配置
```
async:
executor:
  core-pool-size: 10
  maxPoolSize: 200
  queue-capacity: 10