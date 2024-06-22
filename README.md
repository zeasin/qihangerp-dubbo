# 启航电商系统系列
## 一、项目介绍

在兄弟们的鼓励与支持下，经过半年多的时间优化和完善，启航电商系统系列已经形成了三大独立电商业务处理系统，分别覆盖不同场景：

+ **1、电商ERP系统**

`电商ERP系统`主要场景是处理电商业务的进销存整体流程。从采购到商品入库、再到订单出库、再到退换货处理等一系列涉及到仓库库存变化的全流程数据跟踪处理。

主要功能包括：采购下单、商品入库（库存批次）、网店订单API拉取、订单商品出库（先入先出法则）、网店售后拉取、售后处理（退货、换货、补发等库存类操作）等。

**订单发货支持添加赠品**

电商ERP系统侧重是对库存数据的全流程跟踪管理，做到库存追踪，数据可直接用于财务核算，所以对库存数据流有严谨的流程和规则。

[启航电商ERP更详细介绍](README-ERP.md)

+ **2、电商OMS系统**

`电商OMS系统`主要场景是店铺订单业务处理，包括：订单API拉取、电子面单打印、订单发货、售后API拉取、订单拦截、售后处理等，可以使用接口与ERP系统对接。

系统功能主要包括：店铺商品管理、店铺订单管理、店铺售后管理、电子面单打印等。

**目前支持：淘宝、京东、拼多多、抖店、微信视频号小店**，后续继续支持快手、小红书等。

[启航电商OMS更详细介绍](README-OMS.md)

+ **3、电商SCM系统**

`电商SCM系统`主要场景是针对供应链厂家为分销渠道订单一件代发，电商SCM系统有两个后台：供应链厂家发货后台、分销终端订单管理后台。

**主要流程是分销终端推送订单到厂家后台，厂家进行统一打单发货。**

[启航电商SCM更详细介绍](README-SCM.md)



## 二、主要使用到的技术
+ Java17
+ SpringBoot3
+ Redis
+ Nacos
+ Kafka
+ MyBatis-Plus
+ MySQL8
+ SpringCloudAlibaba
+ Vue
+ ElementUI

## 三、关注作者
### 3.1 开源背景
启航电商一系列系统可以说是我这五年以来的工作经验成果。

上一家公司从2019年开始，一直都是由我组建和带领一帮技术人员从零开始建设了一套完全适应公司业务需要的电商ERP系统，包括WMS仓库系统、OMS订单处理系统、财务系统、直播运营系统等子系统组成。

主要功能模块包括：采购模块、出入库模块、订单发货模块、网店订单管理模块、电子面单打印模块。公司ERP对接了批批网、1688、蘑菇街、淘宝天猫、拼多多、抖店、快手小店平台。

**2022年由于某些原因失业了，后来又换了一个新行业的工作，面对新行业感觉到跨行的艰辛。所以让我萌生了把自己以往的经验拿出来分享给大家，看能不能为开源贡献力量。**
让我没有想到的是，开源之后受到很大的关注，在兄弟们的支持和鼓励下，我业余也接了一些单，改善了一下我的生活，**感谢兄弟们的支持！**

**接下来我会继续优化完善系统，希望给大家带来更好用更实用更先进的电商系统。**


**感谢大家的关注与支持！希望利用本人从事电商10余年的经验帮助到大家提升工作效率！**

💖 如果觉得有用记得点 Star⭐


### 3.2 有偿服务
+ [提供本地部署服务](https://mp.weixin.qq.com/s/8U4NvMiAP0vDsTDBzlHJbw)
+ [提供一键演示包](https://mp.weixin.qq.com/s/MtXFijnq0Ti461hO5Sulhw)
+ [提供定制化开发服务](https://mp.weixin.qq.com/s/U-1FKfa84Dfz17WL9GHyqw)
+ [提供电商系统软著代申请服务（文档、源代码）](https://mp.weixin.qq.com/s/8N1PeNHw9jCBR__AsSjeqg)



### 3.3 捐助支持
作者为兼职做开源,平时还需要工作,如果帮到了您可以请作者吃个盒饭

<img src="docs/weixinzhifu.jpg" width="300px" />
<img src="docs/zhifubao.jpg" width="300px" />


### 3.4 关注公众号

更多服务，请关注作者微信公众号：qihangerp168

<img src="docs/公众号.jpg" width="300px" />


💖 欢迎一起交流！ 

