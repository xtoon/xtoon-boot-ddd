<p align="center">
  <a href="http://xtoon-boot.xiangtoon.com/">
    <img width="250" src="http://xtoon.gitee.io/xtoon-boot-site/logo.png">
  </a>
</p>

<h1 align="center">xtoon-boot</h1>

<div align="center">

基于DDD领域模型并支持SaaS平台的企业级开发脚手架.

</div>

<div align="center">

![](https://img.shields.io/badge/language-java-red.svg)
[![ApiAdmin](https://img.shields.io/hexpm/l/plug.svg)](http://www.apiadmin.org/)
[![ApiAdmin](https://img.shields.io/badge/release-1.0.0-blue.svg)](http://www.apiadmin.org/)
[![ApiAdmin](https://img.shields.io/badge/build-passing-brightgreen.svg)](http://www.apiadmin.org/)

</div>

<div align="center">

  [官网](http://xtoon-boot.xiangtoon.com/) |
  [在线体验](http://xtoon-boot.demo.xiangtoon.com/) |
  [前端开源框架](https://gitee.com/xtoon/xtoon-boot-element) |
</div>

## 为何选择xtoon-boot
- 解决编写过程式和事务代码，造成后期维护逻辑混乱、维护成本高的痛点；
- 抛弃MVC框架，拥抱更适合复杂业务的开发框架；
- 网上基本讲的都是DDD的理论很少有讲怎么落地，xtoon-boot提供了完整落地方案和企业级手脚架；
- 可以快速开发，框架提供了系统管理和组织架构等核心模块；
- 支持多租户的SaaS平台；

## 为何开源
工作中一直有个困恼：为什么身边很多项目后期维护时业务逻辑变的混乱不堪，service层代码变的庞大难以修改，维护成本也越来越高，有没有好的解决方式的？<br>
Eric Evans的DDD（Domain-Driven Design 领域驱动设计）正是为了解决复杂业务而提出。
我们1.0版本终于上线了，这套框架开源出来想跟大家探讨并一起维护，希望今后有更多的项目和产品能使用这套框架。


## 技术选型

-  Springboot
-  Apache Shiro
-  Mybatis-plus
-  Alibaba Druid
-  Element-ui


## 主要模块
1.  登录注册：账号、手机号验证登录，租户注册；
2.  用户管理：用户新增，分配角色，禁用等；
3.  角色管理：角色新增，查看，维护菜单等；
4.  菜单管理：树形菜单管理，可配置菜单和按钮权限等；
5.  租户管理：租户列表，禁用等；
6.  操作日志：系统操作日志记录和查询；

## 项目结构 
```
xtoon-boot
├─db     数据库SQL脚本
│ 
├─src      管理后台
│    │ 
│    ├─java  模块
│    │    ├─application 应用层
│    │    │    └─impl      应用接口实现
│    │    ├─domain 领域层（核心）
│    │    │    ├─exception      自定义异常
│    │    │    ├─factory        工厂
│    │    │    ├─model          领域模型
│    │    │    ├─service        领域服务
│    │    │    ├─Repository     资源接口
│    │    │    ├─shared         共享类
│    │    │    ├─specification  规格校验
│    │    │    └─util           工具包
│    │    ├─infrastructure 基础设施层
│    │    │    ├─common         通用类
│    │    │    ├─persistence.mybatis           mybatis持久化类（应该都熟悉就不展开了）
│    │    │    └─util           工具包
│    │    └─interfaces 接口层
│    │         ├─common         通用类
│    │         └─sys            系统管理
│    │             ├─facede     门面类
│    │             └─web        Controller类
│    └─resources 
│        ├─static.swagger       swagger文件
│        ├─application.yml      全局配置文件
│        └─logback-spring.xml   日志配置文件
│       

```

## 核心思想
- 六边形理论
<p align="center">
   <img width="550" src="http://xtoon.gitee.io/xtoon-boot-site/640.png">
</p>
Alistair Cockburn提出了六边形架构，又被称为端口和适配器架构。观察上图我们发现，对于核心的应用程序和领域模型来说，其他的底层依赖或实现都可以抽象为输入和输出两类。组织关系变为了一个二维的内外关系，而不是上下结构。每个io与应用程序之前均有适配器完成隔离工作，每个最外围的边都是一个端口。基于六边形架构设计的系统是DDD追求的最终形态。

## 相关文档
- [CSDN博客-DDD系列](https://blog.csdn.net/haoxin963/category_10708582.html)持续更新中，欢迎关注！


## 技术交流
如果有什么问题或建议可以 [提ISSUE](https://gitee.com/xtoon/xtoon-boot/issues) 或 加群（QQ：130950009），交流技术，分享经验。 <br >
如果你解决了某些bug，或者新增了一些功能，欢迎 [贡献代码](https://gitee.com/xtoon/xtoon-boot/pulls)，感激不尽~ <br >
大家多点 ⭐Star 支持下。

## 维护者

- [haoxin963](https://github.com/haoxin963) 


## 版权声明

<img alt="Apache-2.0 license" src="https://www.apache.org/img/ASF20thAnniversary.jpg" width="128">

本软件基于 Apache-2.0 协议进行分发和使用，更多信息参见 [协议文件](LICENSE)。