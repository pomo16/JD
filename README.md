# JD

### 简介

用 Java Web 模拟京东的登录注册系统，以及简单的后台图片上传功能。这里有四个子项目，记录了我自学 Java Web 的技术变更。四个项目的按时间排序如下:

| 项目  | 版本        | 技术简介                                                     |
| ----- | ---------- | ------------------------------------------------------------ |
| jd    | 初代 beta 版  | 前端三剑客 + 后台 Servlet (Servlet + JSP + Filter)           |
| jd_ui | 前端重构版   | 在 jd 项目的基础上用 jQuery 和 Bootstrap 等重构了前端部分    |
| mySSM | 后端重构版   | 后台使用 spring + springMVC + myBatis 重构                   |
| jdSSM | 后端终极版   | 在 mySSM 的基础上使用 Maven 重构项目，另外追加 JSR303 后端校验、REST风格请求等功能 |



### 项目详情

#### jd：初代 beta 版

+ 项目介绍：这是 JD 项目的第一代，前端使用 low low 的 HTML + CSS + JS ，后端也是老掉牙的 Servlet...

+ 开发环境：
  + Java 9
  + MySQL 8.0
  + Tomcat 9.0.10

+ 技术栈：
  + 前端：HTML + CSS + JS
  + 后台：广义 Servlet 吧 ( Servlet + JSP + Filter )
  + 数据库：MySQL
  + 安全性：MD5 加密算法



#### jd_ui：前端重构版

+ 项目介绍：Java 9 的支持尚未完善，改用 Java 8 做后台。前端部分使用了 jQuery 以及它的一些小组件，比如validation，fileupload，colorbox 等。后端部分基本同 jd ，只是稍微对前端的修改做一些适配。

+ 开发环境：
  + Java 8 
  + MySQL 8.0
  + Tomcat 9.0.10

+ 技术栈：
  + 前端：HTML + CSS + JS ( jQuery + Bootstrap 等)
  + 后台：同 jd
  + 数据库：MySQL
  + 安全性：MD5加密算法



#### mySSM：后端重构版

- 项目介绍：使用 ssm (spring + springMVC + myBatis) 框架重构后台，并使用 log4j 记录日志

- 开发环境：
  - Java 8 
  - MySQL 8.0
  - Tomcat 9.0.10

- 技术栈：
  - 前端：同 jd_ui
  - 后台：ssm (spring + springMVC + myBatis)
  - 数据库：MySQL
  - 安全性：MD5加密算法



#### jdSSM：后端终极版

- 项目介绍：使用 maven 重构项目，重新编写 ssm 配置文件，并添加 JSR 303 后端校验，pageHelper 自动分页功能，使用 REST 风格的  URL，并且新增单元测试方便测试 DAO 并支持批量修改数据库。

- 开发环境：
  - Java 8 
  - MySQL 8.0
  - Tomcat 9.0.10

- 技术栈：
  - 前端：在 jd_ui 基础上添加新的页面来完善项目功能
  - 后台：ssm + maven + junit4 (单元测试)
  - 数据库：MySQL
  - 安全性：MD5加密算法 + JSR 303 后端校验
