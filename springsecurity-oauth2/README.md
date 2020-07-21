# springsecurity-oauth2

springsecurity-oauth2是一个系统，clone 之后单独打开，例如

![](https://img2020.cnblogs.com/blog/1871532/202007/1871532-20200720221233264-1405757395.png)

## 获取令牌

localhost:8080/oauth/token（POST 请求方式）

**参数**

|    **Key**    |                       **Value**                        |
| :-----------: | :----------------------------------------------------: |
|   username    |     jonathanlee（SecurityConfig.java文件中配置的）     |
|   password    |         123（SecurityConfig.java文件中配置的）         |
|  grant_type   | password（AuthorizationServerConfig.java文件中配置的） |
|   client_id   | password（AuthorizationServerConfig.java文件中配置的） |
|     scope     |   all（AuthorizationServerConfig.java文件中配置的）    |
| client_secret |     （AuthorizationServerConfig.java文件中配置的）     |

## 通过令牌访问资源系统里面的资源

localhost:8080/user/hello?access_token=令牌（根据实际确认请求方式）

## 通过刷新令牌重新获取令牌

localhost:8080/oauth/token（POST 请求方式）

|    **Key**    |   **Value**   |
| :-----------: | :-----------: |
|  grant_type   | refresh_token |
| refresh_token |  刷新令牌值   |
|   client_id   |   password    |
| client_secret |      123      |