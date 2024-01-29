# 启航电商ERP系统


启航电商ERP系统是一套为中小电商企业构建的一套简单实用的电商系统，本项目采用Java SpringBoot+Vue2前后端分离开发。

支持供应商一件代发和仓库发货两种发货方式，主体流程覆盖采购、网店订单处理、供应商一件代发、仓库发货、网店售后、仓库出入库、采购结算、代发结算等功能，基本上覆盖了电商日常业务。

支持电商平台订单、售后、发货等接口对接，包括：淘宝、拼多多、抖店、快手小店、小红书等。

![预览](preview.png)

## 一、功能模块
### 供应链管理
+ 供应商管理：管理供应商信息
+ 采购订单管理：管理采购流程，包括供应商选择、采购订单生成、采购合同管理等。
+ 采购退货管理
+ 采购物流管理：跟踪采购订单物流信息。
+ 供应商代发管理：管理一件代发订单。

#### 采购流程

```mermaid
graph LR
A[创建采购订单] -->B(审核)
    B --> C[供应商确认]
    C --> E[供应商发货]
    E --> F1(生成物流信息)
    F1 --> G1[确认收货]
    G1 --> G3[生成入库单]
    G3 --> G4(入库)
    G1 --> G2[生成财务应付及明细]
    G4 --> H(完成)
    G2 --> H

```

### 销售管理
+ 创建订单：手动创建订单。
+ 店铺订单管理：处理和管理多平台订单的流程，包括订单录入、处理、发货等。
  + 支持淘宝、拼多多、抖店、快手小店、小红书平台订单接口；
  + 支持淘宝订单excel导入；
  + 支持手动添加订单；
  + 订单确认到仓库；
+ 订单查询：查询所有订单信息。
+ 店铺管理：管理店铺信息、店铺商品上下架信息等。

#### 订单处理流程
```mermaid
graph LR
A[录入订单] -->B(确认订单)
    B --> C[供应商代发]
    B --> D[仓库发货]
    C --> E[供应商发货]
    E --> F1(生成应付)
    D --> H
    F1 --> H(完成)


```
### 发货管理
+ 订单备货：生成拣货单；
+ 拣货出库：拣货出库、生成出库单减库存；
+ 打包发货：记录包裹信息、物流发货、同步发货状态；
+ 物流跟踪：跟踪发货快递物流；

##### 发货流程
```mermaid
graph TD
A[查询备货清单] -->B(生成拣货单)
    B --> C[拣货出库]
    C --> E(减库存)
    E --> F[打包发货]
    F --> F1(记录包裹信息)
    F1 --> G(填写物流信息)
    G --> G1[同步发货状态]
    G1 --> H(完成)
    G --> G2[生成订单应收款项]
    G2 --> H

```

### 售后管理
对退货、换货、维修等售后处理进行管理，包括退款审核、退货入库、退款处理等环节。
+ 店铺售后管理：处理和管理多平台售后包括录入售后数据、退货入库、换货处理等。
  + 支持拼多多、抖店、快手小店、小红书平台售后接口；
  + 支持手动录入、备注；
+ 退货处理：数据录入、仓库收货确认、库存处理等。
+ 换货处理：数据录入、仓库收货确认、仓库发货、库存处理等。

#### 退款退货流程
```mermaid
graph LR
A[录入退款退货] -->B(仅退款)
    B --> C[生成销售应付]
    A --> D(退货退款)
    D --> E[仓库收货]
    E --> F1(生成销售应付)
    E --> F[退货入库处理]
    C --> H
    F --> H
    F1 --> H(完成)
```


### 库存管理

+ 入库管理
+ 出库管理
+ 库存查询：跟踪和管理库存，包括批次管理、库存盘点、库存调整、库存预警等。
+ 库位管理

### 财务管理
+ 应收管理
  + 商品销售收入
  + 退款款项
  + 平台服务费
+ 应付管理
  + 代发账单
  + 采购账单
  + 采购退货款项
  + 发货物流账单
  + 营销费用
  + 其他费用
+ 存货报表
  每日凌晨生成报表（日报表、月报表、季报表、年报表、查看存货明细）
  


### 客户管理
+ 客户管理：对客户信息进行管理，包括客户档案、客户历史订单信息、客户反馈等内容。

### 商品管理
商品信息、分类信息、属性信息等管理。

### 市场洞察
+ 数据采集：行业信息采集、热搜词采集、商品榜单采集、其他采集；
+ 数据分析：收集和分析数据，帮助企业做出决策和优化运营。
+ 统计报表：生成各种报表和统计数据，用于监控业务和分析绩效。

## 二、技术栈
### 1、技术栈
+ vue2 + elementUI
+ SpringBoot2.x
+ Java 17

### 2、存储栈
+ MySQL8数据库
+ minio文件存储

### 3、中间件
+ Redis（缓存：在线用户、字典、系统配置）
+ Nacos配置中心
+ quartz定时任务


## 三、如何使用？
### 0、开发环境配置
+ 运行MySQL脚本`db\sql\qihang-erp.sql`导入数据到主库`qihang-erp`

+ 运行MySQL脚本`db\sql\nacos.sql`导入数据到nacos库`nacos`

+ 启动nacos
  + 进入nacos文件夹`db\nacos\bin`
  + 双击`startup.cmd`
  
+ 启动Redis
  + 进入项目redis文件夹`db\redis`
  + 启动：双击`start.bat`
  
+ 启动minio
  + 进入项目minio文件夹`db\minio`
  + 启动：CMD运行`minio.exe server data`
  
### 1、启动后端`ecerp-api`
+ IDEA启动项目

### 2、启动前端 `ecerp-vue`
+ `npm install`
+ `npm run dev`

### 3、访问web
+ 访问地址：`http://localhost`
+ 登录名：`admin`
+ 登录密码：`admin123`

## 商业使用
本软件允许商用，商用前请务必先提前测试有需要的功能，以免出现偏差。也可以找作者获得商用支持！

本软件采用`MIT LICENSE`协议授权，根据 MIT 许可证（MIT License）的规定，在软件及其所有副本中都必须包含版权声明和许可声明。


💖 如果觉得有用记得点 Star⭐


欢迎一起交流！



## 捐献作者
作者为兼职做开源,平时还需要工作,如果帮到了您可以请作者吃个盒饭(目前还希望接收大家的捐献可以搭建一个演示环境！)
 
![](./db/zhifubao.jpg=500x500)
![](./db/weixinzhifu.jpg=500x500)

<div align=center>
    <img src="./db/weixinzhifu.jpg" style="width: 300px;">
</div>