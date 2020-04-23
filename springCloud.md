## 1.微服务概述

### 1.1.什么是微服务

微服务化的核心就是将传统的一站式应用，根据业务拆分成一一个一个的服务，彻底地去耦合,每-个微服务提供单个业务功能的服务，一个服务做一 件事,从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行单独启动或销毁，拥有自己独立的数据库。



### 1.2.微服务的优缺点

- 优点

1. 每个服务足够内聚，足够小，代码容易理解这样能聚焦一个指定的业务功能或业务需求
2. 开发简单、开发效率提高，一个服务可能就是专一的只干一件事。
3. 微服务能够被小团队单独开发,这个小团队是2到5人的开发人员组成。
4. 微服务是松耦合的，是有功能意义的服务,无论是在开发阶段或部署阶段都是独立的。
5. 微服务能使用不同的语言开发。
6. 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署,通过持续集成工具,如Jenkins, Hudson, bamboo。
7. 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。
8. 微服务允许你利用融合最新技术。
9. ==微服务只是业务逻辑的代码,不会和HTML,CSS或其他界面组件混合。==
10. ==每个微服务都有自己的存储能力，可以有自己的数据库。也可以有统-数据库。==

- 缺点

1. 开发人员要处理分布式系统的复杂性
2. 多服务运维难度，随着服务的增加，运维的压励也在增大
3. 系统部署依赖
4. 服务间通信成本
5. 数据一致性
6. 系统集成测试
7. 性能监控。。。



### 1.3.微服务的技术栈

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | SpringBoot,Spring,SpringMVC                                  |
| 服务配置与管理                           | Netflix公司的Archaius、阿里的Diamond等                       |
| 服务注册与发现                           | Eureka、Consul、Zookeeper等                                  |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断器                               | Hystrix、Envoy等                                             |
| 负载均衡                                 | Ribbon、Nginx等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign等                                                      |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                         | SpringCloudConfig、Chef等                                    |
| 服务路由（API网关）                      | Zuul等                                                       |
| 服务监控                                 | Zabbix、Nagios、Metrics、Specatator等                        |
| 全链路追踪                               | Zipkin、Brave、Dapper等                                      |
| 服务部署                                 | Docker、OpenStack、Kubernetes等                              |
| 数据流操作开发包                         | SpringCloud Stream(封装与Redis，Rabbit，Kafka等发送接收消息) |
| 事件消息总线                             | SpringCloud Bus                                              |



## 2.SpringCloud概述

> Spring的三大模块：SpringBoot（构建），Spring Cloud（协调），Spring Cloud Data Flow（连接）

### 2.1.SpringCloud是什么

- 分布式系统的简化版（官方介绍）
- SpringCloud基于SpringBoot提供了一整套微服务的解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于Netflix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件
- SpringCloud利用SpringBoot的开发便利性巧妙地简化了分布式系统的基础设施开发，SpringCloud为开发人员提供了快速构建分布式系统的一些工具，包括配置管理、服务发现、断路器、路由、微代理、事件总线，全局所、决策精选、分布式会话等等，他们都可以用SpringBoot的开发风格做到一键启动和部署。
- ==一句话概括：SpringCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的几何体，俗称微服务全家桶==



### 2.2. SpringCloud和SpringBoot的关系

SpringBoot：专注于快速方便的开发单个个体微服务（关注微观）

SpringCloud：关注全局的微服务协调治理框架，将SpringBoot开发的一个个单体微服务组合并管理起来（关注宏观）

==结论：SpringBoot可以离开SpringCloud独立使用，但是SpringCloud不可以离开SpringBoot，属于依赖关系==



## 3.技术选型

|  springcloud  |  Hoxton.SR1   |
| :-----------: | :-----------: |
|  springboot   | 2.2.2.RELEASE |
| cloud alibaba | 2.1.0.RELEASE |
|     Java      |     java8     |
|     Maven     |   3.5及以上   |
|     Mysql     |   5.7及以上   |



## 4.构建基本微服务

> 构建cloud2020父工程

### 4.1.POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
  			 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.zzx.springcloud</groupId>
  <artifactId>cloud2020</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <!-- 统一管理jar包版本 -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!-- 子模块继承之后，提供作用：锁定版本+子module不用写groupId和version  -->
  <dependencyManagement>
    <dependencies>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
        <optional>true</optional>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```



### 4.2.支付模块构建

> 在父项目cloud2020下新建cloud-provider-payment8001子模块

#### 4.2.1.POM文件

> 修改cloud-provider-payment8001的pom文件
>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.zzx.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

#### 4.2.2.YML文件

> 在cloud-provider-payment8001的resources目录下新建application.yml
>
> **注意：** mysql8的数据库驱动包是 com.mysql.cj.jdbc.Driver
>
> ​			mysql5是 com.mysql.jdbc.Driver

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service

  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: org.gjt.mm.mysql.Driver              # mysql驱动包
    url: jdbc:mysql:///springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.zzx.springcloud.entities    # 所有Entity别名类所在包
```

#### 4.2.3.创建启动类PaymentMain8001

- com.zzx.springcloud.PaymentMain8001

```java
@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
```

#### 4.2.4.SQL建表语句

```mysql
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `serial` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#### 4.2.5.相应的javabean

- com.zzx.springcloud.entities.Payment

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

- com.zzx.springcloud.entities.CommonResult

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
```

#### 4.2.6.Dao接口

- com.zzx.springcloud.dao.PaymentDao

```java
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
```

#### 4.2.7.PaymentMapper.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.zzx.springcloud.dao.PaymentDao">
    <!--由于在application.yml配置文件中已经配置了别名，因此无需再写类的全路径
    设置useGeneratedKeys参数值为true，在执行添加记录之后可以获取到数据库自动生成的主键ID，该参数只针对insert语句生效
    keyProperty声明了该表的主键是id，可以将使用useGeneratedKeys获取到自动生成的主键ID赋值给Payment类中的id字段-->
    <insert id="create" parameterType="Payment" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial) values (#{serial})
    </insert>

    <resultMap id="BaseResultMap" type="com.zzx.springcloud.entities.Payment">
        <id property="id" column="id" jdbcType="BIGINT"/>
        <result property="serial" column="serial" jdbcType="VARCHAR"/>
    </resultMap>
    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id = #{id}
    </select>
</mapper>
```

#### 4.2.8.Service接口及其实现类

- com.zzx.springcloud.service.PaymentService

```java
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
```

- com.zzx.springcloud.service.impl.PaymentServiceImpl

```java
@Service
public class PaymentServiceImpl implements com.zzx.springcloud.service.PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
```

#### 4.2.9.Controller层

- com.zzx.springcloud.controller.PaymentController

```java
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("╭(●｀∀´●)╯插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "♪（＾∀＾●）插入数据成功", result);
        } else {
            return new CommonResult(444, "(╥╯^╰╥) 插入数据失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("╭(●｀∀´●)╯查询结果:" + payment);

        if (payment != null) {
            return new CommonResult(200, "♪（＾∀＾●）查询成功", payment);
        } else {
            return new CommonResult(444, "(╥╯^╰╥) 没有ID为" + id + "的记录", null);
        }
    }
}
```

#### 4.2.10启动测试

- 访问http://localhost:8001/payment/get/514成功查询到了数据

![image-20200422161505037](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2m5q77jpj30ls0fu75t.jpg)

- 使用RestfulToolkit插件发送POST请求插入数据成功

![image-20200422144911382](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2jod13dhj30po0ast9o.jpg)



### 4.3.开启热部署DevTools

- 在子项目的pom文件中添加依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

- 在父项目的pom文件中添加依赖：

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
      <configuration>
        <fork>true</fork>
        <addResources>true</addResources>
      </configuration>
    </plugin>
  </plugins>
</build>
```

- 在设置中打开自动编译

> 勾选这四个选项

![image-20200422150403560](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2k3u1r60j315g0u0doy.jpg)

- Update the value of
  - 快捷键 shift + option + command + / 打开如下设置选择Regostry...

  ![image-20200422150725822](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2k7hy7e9j30ie07m3z4.jpg)

  - 勾选以下两个选项

  ![image-20200422151349068](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2kdznj2fj312d0u0q9b.jpg)

- 最后重启idea



### 4.4.消费者订单模块构建

> 在父项目cloud2020下新建cloud-consumer-order8888子模块

#### 4.4.1.POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.zzx.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order8888</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

#### 4.4.2.YML文件

```yaml
server:
  port: 8888
```

#### 4.4.3.创建启动类OrderMain8888

- com.zzx.springcloud.OrderMain8888

```java
@SpringBootApplication
public class OrderMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8888.class, args);
    }
}
```

#### 4.4.4.拷贝两个实体类

#### 4.4.5.RestTemplate

> RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务的模板类，是spring提供的用于访问Rest服务的客户端模板工具集。

- 配置类：com.zzx.springcloud.config.ApplicationContextConfig

```java
@Configuration
public class ApplicationContextConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```

#### 4.4.6.Controller层

- com.zzx.springcloud.controller.OrderController

```java
@RestController
@Slf4j
public class OrderController {
  
    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
```



### 4.5.工程重构

> 由于系统中有重复的实体类，因此需要将这些实体类提取出来重构
>
> 在cloud2020下新建cloud-api-commons子模块

- POM文件

```xml
<dependencies>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.1.0</version>
  </dependency>
</dependencies>
```

- 再对该模块进行clean和install
- 分别删除支付模块和消费者模块中的entities目录，分别在他们的POM文件中新增commons的依赖

```xml
<dependency>
    <groupId>com.zzx.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

- 重启项目测试



## 5.Eureka服务注册与发现

> [Eureka](https://github.com/Netflix/Eureka) 是 [Netflix](https://github.com/Netflix) 开发的，一个基于 REST 服务的，服务注册与发现的组件，以实现中间层服务器的负载平衡和故障转移。

### 5.1.Eureka基础知识

#### 5.1.1.Eureka的两大组件

- Eureka Client：一个Java客户端，用于简化与 Eureka Server 的交互（通常就是微服务中的客户端和服务端）


- Eureka Server：提供服务注册和发现的能力（通常就是微服务中的注册中心）

服务在Eureka上注册，然后每隔30秒发送心跳来更新它们的租约。如果客户端不能多次续订租约，那么它将在大约90秒内从服务器注册表中剔除。注册信息和更新被复制到集群中的所有eureka节点。来自任何区域的客户端都可以查找注册表信息（每30秒发生一次）来定位它们的服务（可能在任何区域）并进行远程调用

![image-20200422171229436](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2ntgyk36j313q0luq6z.jpg)


#### 5.1.2.Eureka 客户端与服务器之间的通信

服务发现有两种模式：一种是客户端发现模式，一种是服务端发现模式。Eureka采用的是客户端发现模式。

#### 5.1.3.Register（注册）

Eureka客户端将关于运行实例的信息注册到Eureka服务器。注册发生在第一次心跳。

#### 5.1.4.Renew（更新 / 续借）

Eureka客户端需要更新最新注册信息（续借），通过每30秒发送一次心跳。更新通知是为了告诉Eureka服务器实例仍然存活。如果服务器在90秒内没有看到更新，它会将实例从注册表中删除。建议不要更改更新间隔，因为服务器使用该信息来确定客户机与服务器之间的通信是否存在广泛传播的问题。

#### 5.1.5.Fetch Registry（抓取注册信息）

Eureka客户端从服务器获取注册表信息并在本地缓存。之后，客户端使用这些信息来查找其他服务。通过在上一个获取周期和当前获取周期之间获取增量更新，这些信息会定期更新(每30秒更新一次)。获取的时候可能返回相同的实例。Eureka客户端自动处理重复信息。

#### 5.1.6.Cancel（取消）

Eureka客户端在关机时向Eureka服务器发送一个取消请求。这将从服务器的实例注册表中删除实例，从而有效地将实例从流量中取出。



### 5.2.单机Eureka构建

> 在父项目下新建cloud-eureka-server7001子模块

#### 5.2.1.POM文件

```xml
<dependencies>
    <!--eureka-server-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
    <dependency>
        <groupId>com.zzx.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>
    <!--boot web actuator-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--一般通用配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
    </dependency>
</dependencies>
```

#### 5.2.2.YML文件

```yaml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost #eureka服务端实例名称
  client:
    register-with-eureka: false #表示不向注册中心注册自己
    fetch-registry: false #false表示自己就是注册中心，我的职责就是维护服务实例,并不区检索服务
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

#### 5.2.3.创建启动类EurekaMain7001

- com.zzx.springcloud.EurekaMain7001

```java
@SpringBootApplication
@EnableEurekaServer //开启EurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
```

#### 5.2.4.访问测试成功

![image-20200422180207553](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2p94jdenj31ed0u0wn3.jpg)



### 5.3.微服务入驻Eureka

#### 5.3.1.支付微服务8001入驻7001

- 新增POM依赖

```xml
<!--eureka-client-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

- 在YML中新增关于Eureka的配置

```yaml
eureka:
  client:
    register-with-eureka: true #表示向注册中心注册自己 默认为true
    fetch-registry: true #是否从EurekaServer抓取已有的注册信息，默认为true,单节点无所谓,集群必须设置为true才能配合ribbon使用负载均衡
    service-url:
      defaultZone: http://localhost:7001/eureka/ # 入驻地址
```

- 启动类加上注解@EnableEurekaClient

#### 5.3.2.订单微服务8888入驻7001

- 配置同上
- 访问测试，订单服务是UNKNOWN的原因是没有在它的yml文件中配置spring.application.name

![image-20200422184755811](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2qkrutiuj31ed0u0k0m.jpg)



### 5.4.集群Eureka构建

> **原理说明：**
>
> 服务注册：将服务信息注册到注册中心
>
> 服务发现：从注册中心获取服务信息
>
> 实质：存key服务名，取value调用地址

- 步骤：
  1. 先启动eureka注册中心
  2. 启动服务提供者payment支付服务
  3. 支付服务启动后，会把自身信息注册到eureka
  4. 消费者order服务在需要调用接口时，使用服务别名去注册中心获取实际的远程调用地址
  5. 消费者获得调用地址后，底层实际是调用httpclient技术实现远程调用
  6. 消费者获得服务地址后会缓存在本地jvm中，默认每30秒更新异常服务调用地址

- **问题：**微服务RPC远程调用最核心的是说明？

  高可用，如果注册中心只有一个，出现故障就麻烦了。会导致整个服务环境不可用。

  **解决办法**：搭建eureka注册中心集群，实现**负载均衡+故障容错**

  互相注册，相互守望

#### 5.4.1.构建Eureka7002

> 完全仿照单机Eureka构建新建cloud-eureka-server7002子模块，POM文件的依赖只需复制7001的即可

#### 5.4.2.修改hosts文件

新增映射

```shell
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
```

![image-20200422191617652](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2rea7vx5j30mo0cyabw.jpg)

#### 5.4.3.修改7001的YML文件

```yaml
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com #eureka服务端实例名称
  client:
    register-with-eureka: false #表示不像注册中心注册自己
    fetch-registry: false #false表示自己就是注册中心，我的职责就是维护服务实例,并不区检索服务
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://eureka7002.com:7002/eureka/
```

#### 5.4.4.修改7002的YML文件

```yaml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com #eureka服务端实例名称
  client:
    register-with-eureka: false #表示不像注册中心注册自己
    fetch-registry: false #false表示自己就是注册中心，我的职责就是维护服务实例,并不区检索服务
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://eureka7001.com:7001/eureka/
```

#### 5.4.5.测试

- 访问http://eureka7001.com:7001

![image-20200422194502402](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2s87a791j327y0ry0yi.jpg)

- 访问http://eureka7002.com:7002

![image-20200422194607438](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2s9bdgk0j327y0sigrg.jpg)



### 5.5.微服务注册到Eureka集群

#### 5.5.1.修改8001的YML配置文件

```yaml
#defaultZone: http://localhost:7001/eureka/ # 入驻地址
defaultZone: http://eureka7001.com:7001/eureka/, http://eureka7002.com:7002/eureka/
```

#### 5.5.2.8888的YML配置文件同理

#### 5.5.3.分别测试

http://eureka7001.com:7001和http://eureka7002.com:7002

![image-20200422195742906](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2sle1ybcj31q70u07cj.jpg)

![image-20200422195816448](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2slye7pcj31r60u0n5e.jpg)



### 5.6.搭建支付服务集群

> 参照8001搭建8002环境，注意在yml配置文件中要修改port为各自对应的端口

#### 5.6.1.初步测试

- 访问http://eureka7001.com:7001成功

![image-20200422202717625](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2tg5sh4ej31qu0u0470.jpg)

- 访问http://eureka7002.com:7002成功

![image-20200422202838838](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2thkbnn7j31r70u0jzw.jpg)

- 访问8001的支付服务成功http://localhost:8001/payment/get/514

![image-20200422202938398](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2tilwl3aj30ry0gimz2.jpg)

- 访问8002的支付服务成功http://localhost:8002/payment/get/514

![image-20200422203023106](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2tjdae60j30ru0gswge.jpg)

- 通过消费者服务访问成功http://localhost:8888/consumer/payment/get/514

![image-20200422203256529](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2tml0kw0j30rs0gitai.jpg)

#### 5.6.2.暴露的问题

发现无论通过消费者服务访问几次http://localhost:8888/consumer/payment/get/514，最后的结果端口永远是8001，这是由于在8888服务中写死了URL路径：`private static final String PAYMENT_URL = "http://localhost:8001";`，将其修改成`private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";`

#### 5.6.3.修改完成后再次访问

- 出现了error，部分报错如下

```java
Wed Apr 22 20:38:36 CST 2020
There was an unexpected error (type=Internal Server Error, status=500).
I/O error on GET request for "http://CLOUD-PAYMENT-SERVICE/payment/get/514": CLOUD-PAYMENT-SERVICE; nested exception is java.net.UnknownHostException: CLOUD-PAYMENT-SERVICE
```

- 原因是我们配置了以服务名称访问，但是该服务名称下有多个服务，我们需要给restTemplate配置访问策略，在getRestTemplate方法上加@LoadBalanced注解，默认是轮询问

- 修改完成后重启8888服务，再次测试成功，并且成功从8002端口的服务访问到了数据

![image-20200422204511215](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2tyrpi1wj30si0getao.jpg)



### 5.7.Eureka的自我保护机制

> 默认情况下，当Eureka server在一定时间内没有收到实例的心跳，便会把该实例从注册表中删除（默认是90秒），但是，如果短时间内丢失大量的实例心跳，便会触发eureka server的自我保护机制。

​	比如在开发测试时，需要频繁地重启微服务实例，但是我们很少会把eureka server一起重启（因为在开发过程中不会修改eureka注册中心），当一分钟内收到的心跳数大量减少时，会触发该保护机制。可以在eureka管理界面看到Renews threshold和Renews(last min)，当后者（最后一分钟收到的心跳数）小于前者（心跳阈值）的时候，触发保护机制，会出现红色的警告：
==EMERGENCY!EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT.RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEGING EXPIRED JUST TO BE SAFE.==

​	从警告中可以看到，eureka认为虽然收不到实例的心跳，但它认为实例还是健康的，eureka会保护这些实例，不会把它们从注册表中删掉。

​	在自我保护模式中，EurekaServer会保护服务注册表中的信息，不再注销任何服务实例。

​	综上，自我保护模式是一种应对网络异常的安全保护措施它的架构哲学是宁可同时保留所有微服务，也不忙保姆注销如何健康的微服务，使用自我保护模式，可以让Eureka集群更加健壮，稳定。

- 关闭Eureka的自我保护机制

  - 注册中心7001的yml配置

  ```yaml
  eureka: 
  	server:
    	enable-self-preservation: false # 关闭自我保护机制 保证不可用服务及时清除
    	eviction-interval-timer-in-ms: 2000
  ```

  - 生产者客户端8001的配置

  ```yaml
  eureka: 
  	instance:
  		lease-renewal-interval-in-seconds: 1 # eureka客户端向服务端发送心跳的时间间隔 单位秒 默认30
  		lease-expiration-duration-in-seconds: 2 # eureka客户端在收到最后一次心跳后等待时间上线 单位秒 默认90，超时将剔除服务
  ```

  

## 6.Consul

### 6.1.使用了docker来运行consul

#### 6.1.1.查找所有的consul镜像

- docker search comsul

![image-20200422223258231](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2x2xk5hrj31f20pm4a0.jpg)

#### 6.1.2.下载consul镜像

- `docker pull consul:1.6.1`

#### 6.1.3.查看镜像

- `docker images`，可以看到consul已经下载成功

![image-20200422223456475](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2x4zd9slj3102088418.jpg)

#### 6.1.4.在docker中运行consul

> 常用参数：
>
> agent: 表示启动 agent 进程
>  server: 表示 consul 为 server 模式
>  client: 表示 consul 为 client 模式
>  bootstrap: 表示这个节点是 Server-Leader
>  --restart=always:Docker 重启时,容器自动启动，如开始未指定，可以使用命令:
>  docker container update --restart=always 容器名字,进行修改。
>  ui: 启动 Web UI, 默认端口 8500
>  node: 指定节点名称, 集群中节点名称唯一
>  client: 绑定客户端接口地址, 0.0.0.0 表示所有地址都可以访问
>  –net=host docker参数, 使得docker容器越过了netnamespace的隔离，免去手动指定端口映射的步骤
>  -server consul支持以server或client的模式运行, server是服务发现模块的核心, client主要用于转发请求
>  -advertise 将本机私有IP传递到consul
>  -bootstrap-expect 指定consul将等待几个节点连通，成为一个完整的集群
>  -retry-join 指定要加入的consul节点地址，失败会重试, 可多次指定不同的地址
>  -client consul绑定在哪个client地址上，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1
>  -bind 该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是0.0.0.0
>  -allow_stale 设置为true, 表明可以从consul集群的任一server节点获取dns信息, false则表明每次请求都会经过consul server leader
>  --name DOCKER容器的名称
>  -client 0.0.0.0 表示任何地址可以访问。

- `docker run -d --restart=always -p 8500:8500 -v /Users/hds0m3zzx/data/consul:/consul/data --name=myconsul consul agent -server -bootstrap -ui -client='0.0.0.0'`，将日志文件保存到/Users/hds0m3zzx/data/consul目录下，通过`docker ps -a`可以得出我已经执行过一次这个命令产生了consul容器了

![image-20200422224141156](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2xbzqp5wj324c05otau.jpg)

- 直接启动通过`docker start 5417a09f9bc5(容器id)`来启动consul，再通过`docker ps`命令查看发现consul已经启动成功

![image-20200422225422491](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2xp7217pj312s04kdih.jpg)

#### 6.1.5.访问http://localhost:8500/ui成功

![image-20200422225521004](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge2xq7iopgj31gg0q4n09.jpg)

### 6.2.服务提供者注册进consul

> 模仿上述8001(4.2.支付模块构建)构建新的子模块cloud-providerconsul-payment8006

#### 6.2.1.POM文件

```xml
<dependencies>
    <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
    <dependency>
        <groupId>com.zzx.springcloud</groupId>
        <artifactId>cloud-api-commons</artifactId>
        <version>${project.version}</version>
    </dependency>
    <!--SpringCloud consul-server -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
    <!-- SpringBoot整合Web组件 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--日常通用jar包配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>RELEASE</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### 6.2.2.YML文件

```yaml
###consul服务端口号
server:
  port: 8006

spring:
  application:
    name: consul-provider-payment
####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

#### 6.2.3.主启动类PaymentMain8006

- com.zzx.springcloud.PaymentMain8006

```java
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
```

#### 6.2.4.Controller层

- com.zzx.springcloud.controller.PaymentController

```java
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul() {
        return "springcloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
```

#### 6.2.5.访问测试

- 访问http://localhost:8500/ui/dc1/services/consul-provider-payment成功

![image-20200423141727950](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3odpcmzqj327y0oogqn.jpg)

- 访问http://localhost:8006/payment/consul成功

![image-20200423141756934](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3oe6pq7qj30xs0dygn3.jpg)

### 6.3.服务消费者注册进consul

> 模仿上述8888(4.4.消费者订单模块构建)构建新的子模块cloud-consumerconsul-order8888

#### 6.3.1.POM文件

```xml
<dependencies>
    <!--SpringCloud consul-server -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
    <!-- SpringBoot整合Web组件 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--日常通用jar包配置-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### 6.3.2.YML文件

```yaml
###consul服务端口号
server:
  port: 8888

spring:
  application:
    name: cloud-consumer-order
  ####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

#### 6.3.3.主启动类OrderConsulMain8888

- com.zzx.springcloud.OrderConsulMain8888

```java
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain8888.class, args);
    }
}
```

#### 6.3.4.配置类ApplicationContextConfig

- com.zzx.springcloud.config.ApplicationContextConfig

```java
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

#### 6.3.5.Controller层

- com.zzx.springcloud.controller.OrderConsulController

```java
@RestController
@Slf4j
public class OrderConsulController {
    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }
}
```

#### 6.3.6.访问测试

- 访问http://localhost:8500/ui/dc1/services成功看到了cloud-consumer-order

![image-20200423144209818](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3p3dseu5j31hy0rwdjs.jpg)

- 访问http://localhost:8888/consumer/payment/consul成功得到了返回的信息

![image-20200423144348434](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3p52cgdgj30wk0geq4h.jpg)

## 7.Eureka、Zookeeper、Consul三个注册中心的异同点

|  组件名   | 语言 | 健康检查 | 对外暴露接口 | CAP  | Spring Cloud 集成 |
| :-------: | :--: | :------: | :----------: | :--: | :---------------: |
|  Eureka   | Java | 可配支持 |     HTTP     |  AP  |       集成        |
|  Consul   |  Go  |   支持   |   HTTP/DFS   |  CP  |       集成        |
| Zookeeper | java |   支持   |    客户端    |  CP  |       集成        |



## 8.Ribbon

> 概述：Ribbon是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。

​		通过Spring Cloud的封装，可以让我们轻松地将面向服务的REST模版请求自动转换成客户端负载均衡的服务调用。Spring Cloud Ribbon虽然只是一个工具类框架，它不像服务注册中心、配置中心、API网关那样需要独立部署，但是它几乎存在于每一个Spring Cloud构建的微服务和基础设施中。因为微服务间的调用，API网关的请求转发等内容，实际上都是通过Ribbon来实现的，包括后续我们将要介绍的Feign，它也是基于Ribbon实现的工具。所以，对Spring Cloud Ribbon的理解和使用，对于我们使用Spring Cloud来构建微服务非常重要。

- **LB负载均衡(Load Balance)是什么?**

  简单的说就是将用户的请求分摊到各个服务器上，从而达到系统的HA(高可用)

  常见的负载均衡软件有NGINX，LVS，硬件F5等

- Ribbon本地负载均衡客户端和Nginx服务端负载均衡的区别

  - Nginx是服务器负载均衡，客户端所有的请求都会先交给Nginx，然后由Nginx实现转发。即负载均衡是由服务端实现的
  - Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册服务信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程调用技术



==IRule接口的各种实现==

- com.netflix.loadbalancer.RoundRobinRule：轮询
- com.netflix.loadbalancer.RandomRule：随机
- com.netflix.loadbalancer.RetryRule：先按照RoundRobinRule的策略获取服务，如果获取服务失败则会在指定的时间内重试，获取可用的服务
- WeightedResponseTimeRule：对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
- BestAvailableRule：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量小的服务
- AvailabilityFilteringRule：先过滤掉故障实例，再选择并发量小的实例
- ZoneAvoidanceRule：默认规则，复合判断server所在区域的性能和server的可用性选择服务器



### 8.1.8888模块负载规则替换

> 修改cloud-consumer-order8888(4.4.消费者订单模块构建)模块，将轮询策略替换为随机
>
> ==注意：**不能与主启动类在同一个包下**==

#### 8.1.1.新建package

- 新建package：com.zzx.myrule

#### 8.1.2.新建Rule类

- 在新建的包下新建com.zzx.myrule.MyselfRule类

```java
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();//定义为随机
    }
}
```

#### 8.1.3.在主启动类OrderConsulMain8888新增注解

```
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
```

#### 8.1.4.测试

- 访问http://localhost:8888/consumer/payment/get/514发现serverPort不再是8001和8002交替而是随机访问，替换负载规则成功

![image-20200423163149226](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3s9gbb3pj30ry0hcjtb.jpg)



### 8.2.Ribbon负载均衡算法

#### 8.2.1.Ribbon轮询算法

> **负载均衡轮询算法** :rest接口第几次请求次数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务器重启后，rest接口计数从1开始。

#### 8.2.2.Ribbon轮询算法源码

```java
private int incrementAndGetModulo(int modulo) {
    int current;
    int next;
    do {
        current = this.nextServerCyclicCounter.get();
        next = (current + 1) % modulo;
    } while(!this.nextServerCyclicCounter.compareAndSet(current, next));
		return next;
}
```

#### 8.2.3.手写轮询算法

- 在cloud-provider-payment8001和8002的PaymentController新增方法

```java
@GetMapping("/payment/lb")
public String getPaymentLB(){
    return serverPort;
}
```

- 注释掉cloud-consumer-order8888中ApplicationContextConfig的@LoadBalanced注解

- 在cloud-consumer-order8888创建接口LoadBalancer

==com.zzx.springcloud.lb.LoadBalancer==

```java
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
```

- 实现类MyLB

==com.zzx.springcloud.lb.MyLB==

```java
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("(•‾̑⌣‾̑•)✧˖°第" + next + "次访问");
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
```

- 在OrderController新增

```java
@Resource
private LoadBalancer loadBalancer;

@Resource
private DiscoveryClient discoveryClient;

@GetMapping("/consumer/payment/lb")
public String getPaymentLB() {
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    if (instances == null || instances.size() <= 0) {
        return null;
    }
    ServiceInstance serviceInstance = loadBalancer.instances(instances);
    URI uri = serviceInstance.getUri();
    return restTemplate.getForObject(uri + "/payment/lb", String.class);
}
```

- 测试，访问http://localhost:8888/consumer/payment/lb，8001和8002交替出现，替换成功

![image-20200423191039039](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge3wuq87c0j30qa09uq3s.jpg)



## 9.OpenFeign

> Feign是一个声明式的Web Service客户端。它的出现使开发Web Service客户端变得很简单。使用Feign只需要创建一个接口加上对应的注解，比如：FeignClient注解。Feign有可插拔的注解，包括Feign注解和JAX-RS注解。Feign也支持编码器和解码器，Spring Cloud Open Feign对Feign进行增强支持Spring MVC注解，可以像Spring Web一样使用HttpMessageConverters等。

==Feign集成了Ribbon==

利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务的调用

### 9.1.新建cloud-consumer-feign-order8888模块

#### 9.1.1.POM文件

```xml
<dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
            <!--解决默认返回xml格式问题-->
            <exclusions>
                <exclusion>
                    <groupId>com.fasterxml.jackson.dataformat</groupId>
                    <artifactId>jackson-dataformat-xml</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.zzx.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

#### 9.1.2.YML文件

```yaml
server:
  port: 8888

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/, http://eureka7002.com:7002/eureka/
```

#### 9.1.3.主启动类

- com.zzx.springcloud.OrderFeignMain8888

```java
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain8888.class, args);
    }
}
```

#### 9.1.4.Service层

- com.zzx.springcloud.service.PaymentFeignService

```java
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
```

#### 9.1.5.Controller层

- com.zzx.springcloud.controller.OrderFeignController

```java
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }
}
```

#### 9.1.6.测试

- 访问http://localhost:8888/consumer/payment/get/514成功

![image-20200423211415218](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge40fbynk0j30qs0he40f.jpg)



### 9.2.OpenFeign超时

#### 9.2.1.OpenFeign超时测试

- ##### 在cloud-provider-payment8001的PaymentController添加如下方法

```java
@GetMapping("/payment/feign/timeout")
public String paymentFeignTimeout() {
    try {
        // 暂停3秒钟 模拟超时任务
        TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return serverPort;
}
```

- ##### 在cloud-consumer-feign-order8888的PaymentFeignService中新增方法

```java
@GetMapping("/payment/feign/timeout")
public String paymentFeignTimeout();
```

- ##### 在cloud-consumer-feign-order8888的OrderFeignController中新增方法

```java
@GetMapping("/consumer/payment/feign/timeout")
public String paymentFeignTimeout(){
    //openfeign-ribbon客户端一般默认等待1秒钟
    return paymentFeignService.paymentFeignTimeout();
}
```

- ##### 测试

  - 首先8001自测，访问http://localhost:8001/payment/feign/timeout成功

  ![image-20200423212810734](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge40ttpvz7j30oo094gme.jpg)
  - 访问http://localhost:8888/consumer/payment/feign/timeout成功出现了错误页面，超时测试成功

  ![image-20200423212940723](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge40vdxmfxj31ds0no7bm.jpg)

#### 9.2.2.OpenFeign超时控制

- 在cloud-consumer-feign-order8888的YML文件中新增配置

```yaml
# 设置feign客户端超时时间(OpenFeign默认支持ribbon)
ribbon:
  # 指的是建立连接后从服务器读取到可用资源所用的时间
  ReadTimeout: 5000
  # 指的是建立连接所用的时间,适用于网络状态正常的情况下,两端连接所用的时间
  ConnectTimeout: 5000
```

- 再次访问http://localhost:8888/consumer/payment/feign/timeout成功

![image-20200423213654357](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge412wcwm9j30sg08kjsb.jpg)



### 9.3.OpenFeign日志增强

> openfeign提供了日志打印功能。
>
> Logger有四种类型：`NONE(默认)`、`BASIC`、`HEADERS`、`FULL`，通过注册Bean来设置日志记录级别

- com.zzx.springcloud.config.FeignConfig

```java
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
```
- YML文件新增配置

```yaml
logging:
  level:
    # feign日志以什么级别监控哪个接口
    com.zzx.springcloud.service.PaymentFeignService: debug
```



## 10.Hystrix

> 在微服务场景中，通常会有很多层的服务调用。如果一个底层服务出现问题，故障会被向上传播给用户。我们需要一种机制，当底层服务不可用时，可以阻断故障的传播。这就是断路器的作用。他是系统服务稳定性的最后一重保障。
>
> 在springcloud中断路器组件就是Hystrix。Hystrix也是Netflix套件的一部分。他的功能是，当对某个服务的调用在一定的时间内（默认10s），有超过一定次数（默认20次）并且失败率超过一定值（默认50%），该服务的断路器会打开。返回一个由开发者设定的fallback。
>
> fallback可以是另一个由Hystrix保护的服务调用，也可以是固定的值。fallback也可以设计成链式调用，先执行某些逻辑，再返回fallback。

==**Hystrix的作用**==

1. 对通过第三方客户端库访问的依赖项（通常是通过网络）的延迟和故障进行保护和控制。
2. 在复杂的分布式系统中阻止级联故障。
3. 快速失败，快速恢复。
4. 回退，尽可能优雅地降级。
5. 启用近实时监控、警报和操作控制。