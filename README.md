<p align="center">
  <a href="http://xtoon-boot.xiangtoon.com/">
    <img width="250" src="http://xtoon.gitee.io/xtoon-boot-site/logo.png">
  </a>
</p>

<h1 align="center">xtoon-boot</h1>

<div align="center">

基于DDD领域模型设计并支持SaaS平台的企业级开发脚手架.

</div>

<div align="center">

![](https://img.shields.io/badge/language-java-red.svg)
[![ApiAdmin](https://img.shields.io/hexpm/l/plug.svg)](http://xtoon-boot.xiangtoon.com/)
[![ApiAdmin](https://img.shields.io/badge/release-1.2.0-blue.svg)](http://xtoon-boot.xiangtoon.com/)
[![ApiAdmin](https://img.shields.io/badge/build-passing-brightgreen.svg)](http://xtoon-boot.xiangtoon.com/)

</div>

<div align="center">

  [官网](http://xtoon-boot.xiangtoon.com/) |
  [在线体验](http://xtoon-boot.demo.xiangtoon.com/) |
  [Swagger](http://106.12.78.187:8080/xtoon-boot/swagger-ui/index.html) |
  [前端开源框架](https://gitee.com/xtoon/xtoon-boot-element) |
</div>

## 为何选择xtoon-boot
- 解决编写过程式和事务代码，造成后期维护逻辑混乱、维护成本高的痛点；
- 边界规范易维持，核心业务逻辑内聚在领域内，低耦合，高内聚，易于长期维护；
- 网上基本讲的都是DDD的理论很少有讲怎么落地，xtoon-boot提供了完整落地方案和企业级手脚架；
- 可以快速开发，框架提供了系统管理和组织架构等核心模块；
- 支持多租户的SaaS平台；

## 技术交流
如果有什么问题或建议可以 [提ISSUE](https://gitee.com/xtoon/xtoon-boot/issues) 或 加群（QQ：130950009），交流技术，分享经验。 <br >
如果你解决了某些bug，或者新增了一些功能，欢迎 [贡献代码](https://gitee.com/xtoon/xtoon-boot/pulls)，感激不尽~ <br >
大家多点 ⭐Star 支持下。


## 技术选型

-  Springboot
-  Apache Shiro
-  Mybatis-plus
-  Swagger
-  Hibernate-validator
-  Alibaba Druid
-  Element-ui


## 主要模块
1.  登录注册：账号、手机号验证登录，租户注册；
2.  用户管理：用户新增，分配角色，禁用等；
3.  角色管理：角色新增，查看，维护菜单等；
4.  菜单管理：树形菜单管理，可配置菜单和按钮权限等；
5.  租户管理：租户列表，禁用等；
6.  日志管理：记录操作日志记录和查询；

## 项目结构 
```
xtoon-boot
├─db     数据库SQL脚本
│ 
├─xtoon-common      通用模块
│    │ 
│    └─java 
│         ├─domain 领域通用类
│         ├─util 工具类
│         └─web  web通用类
│   
├─xtoon-start      启动模块
│    │ 
│    ├─java  启动类
│    └─resources 
│        ├─static.swagger       swagger文件
│        ├─application.yml      全局配置文件
│        └─logback-spring.xml   日志配置文件
│ 
├─xtoon-sys      系统子域
│    │ 
│    └─java 
│         ├─application 应用层
│         │    └─impl      应用接口实现
│         ├─domain 领域层（核心）
│         │    ├─factory        工厂
│         │    ├─model          领域模型
│         │    ├─repository     资源接口
│         │    ├─service        领域服务
│         │    ├─specification  规格校验
│         │    └─util           工具包
│         ├─infrastructure 基础设施层
│         │    ├─persistence.mybatis           mybatis持久化类（应该都熟悉就不展开了）
│         │    └─util           工具包
│         └─interfaces 接口层
│              ├─facede         门面类
│              └─web            controller类
│   
├─xtoon-org      组织架构子域（开发中）
│       

```

## 核心理念
### 六边形理论
<p align="center">
   <img width="650" src="http://xtoon.gitee.io/xtoon-boot-site/640.png">
</p>
Alistair Cockburn提出了六边形架构，又被称为端口和适配器架构。观察上图我们发现，对于核心的应用程序和领域模型来说，其他的底层依赖或实现都可以抽象为输入和输出两类。组织关系变为了一个二维的内外关系，而不是上下结构。每个io与应用程序之前均有适配器完成隔离工作，每个最外围的边都是一个端口。基于六边形架构设计的系统是DDD追求的最终形态。

### 数据驱动和领域驱动对比
<p align="center">
   <img width="650" src="http://xtoon.gitee.io/xtoon-boot-site/650.webp">
</p>
领域驱动设计与之前的系统设计开发过程有很大的不同：  
1. 就在于系统的参与角色，产品、开发、测试等，需要形成一套通用语言；  
2. 在于方案设计不再把db设计放在一个核心问题去解决，更加专注于业务模型本身，进行领域、业务聚合的设计，领域层的聚合及实体才是整个系统的核心内容；  
3. 真正的面向对象编程，由过程式的事务脚本方式，转变为真正的面向对象。

### 分层架构
<p align="center">
   <img width="650" src="http://xtoon.gitee.io/xtoon-boot-site/660.png">
</p>

-  用户界面层(或表示层)  
负责向用户显示信息和解释用户指令。这里指的用户可以是另一个计算机系统， 不一定是使用用户界面的人
-  应用层  
定义软件要完成的任务，并且指挥表达领域概念的对象来解决问题。这一层所负 责的工作对业务来说意义重大，也是与其他系统的应用层进行交互的必要渠道
应用层要尽量简单，不包含业务规则或者知识，而只为下一层中的领域对象协调 任务，分配工作，使它们互相协作。它没有反映业务情况的状态，但是却可以具有 另外一种状态，为用户或程序显示某个任务的进度
- 领域层(或模型层)  
负责表达业务概念，业务状态信息以及业务规则。尽管保存业务状态的技术细节 是由基础设施层实现的，但是反映业务情况的状态是由本层控制并且使用的。领域 层是业务软件的核心
- 基础设施层  
为上面各层提供通用的技术能力:为应用层传递消息，为领域层提供持久化机制， 为用户界面层绘制屏幕组件，等等。基础设施层还能够通过架构框架来支持4个层次 间的交互模式

## 为何开源
工作中一直有个困恼：为什么身边很多项目后期维护时业务逻辑变的混乱不堪，service层代码变的庞大难以变更，维护成本居高不下，有没有好的解决方式的？<br>
Eric Evans的DDD（Domain-Driven Design 领域驱动设计）正是为了解决复杂业务而提出。
我们1.1版本终于上线了，框架开源出来是想跟大家探讨并一起维护，希望今后有更多的项目和产品能使用基于框架开发。

## 相关文档
- [CSDN博客-DDD系列](https://blog.csdn.net/haoxin963/category_10708582.html)持续更新中，欢迎关注！


## 维护者

- [haoxin963](https://github.com/haoxin963) 


## 版权声明

<img alt="Apache-2.0 license" src="https://www.apache.org/img/ASF20thAnniversary.jpg" width="128">

本软件基于 Apache-2.0 协议进行分发和使用，更多信息参见 [协议文件](LICENSE)。