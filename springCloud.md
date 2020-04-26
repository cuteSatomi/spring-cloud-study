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

- com.zzx.springcloud.com.zzx.springcloud.controller.PaymentController

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

- com.zzx.springcloud.com.zzx.springcloud.controller.OrderController

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

- com.zzx.springcloud.com.zzx.springcloud.controller.PaymentController

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

- com.zzx.springcloud.com.zzx.springcloud.controller.OrderConsulController

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

#### 9.1.3.主启动类OrderFeignMain8888

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

- com.zzx.springcloud.com.zzx.springcloud.controller.OrderFeignController

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



**==重要概念==**

- 服务降级

  > 服务器忙请稍后再试，不让客户端等待并立刻返回一个友好提示

  出现服务降级的原因：

  - 程序运行异常
  - 超时
  - 服务熔断触发服务降级
  - 线程池/信号量打满也会导致服务降级

- 服务熔断

  > 类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电

  导致服务熔断的原因：

  ​	服务的降级-->进而熔断-->恢复调用链路

- 服务限流

  > 秒杀高并发等操作，严禁一窝蜂过来拥挤。



### 10.1.Hystrix支付微服务构建

> 新建子模块cloud-provider-hystrix-payment8001

#### 10.1.1.POM文件

```xml
<dependencies>
        <!--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency><!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.zzx.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
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
```

#### 10.1.2.YML文件

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-provider-hystrix-payment

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
      defaultZone: http://eureka7001.com:7001/eureka
```

#### 10.1.3.主启动类PaymentHystrixMain8001

- com.zzx.springcloud.PaymentHystrixMain8001

```java
@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class, args);
    }
}
```

#### 10.1.4.Service层

- com.zzx.springcloud.service.PaymentService

```java
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_OK,id:\t" + id + "\t( ˘ ³˘)♥";
    }

    //演示超时导致服务降级
    public String paymentInfo_TimeOut(Integer id) {
        int sleepTime = 3;
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOut,id:\t" + id + "\t (ಥ_ಥ) " + "耗时(秒):" + sleepTime;
    }
}
```

#### 10.1.5.Controller层

- com.zzx.springcloud.com.zzx.springcloud.controller.PaymentController

```java
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("=======result:"+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("=======result:"+result);
        return result;
    }
}
```

#### 10.1.6.访问测试

- 访问http://localhost:8001/payment/hystrix/ok/514成功

![image-20200424134608543](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4t3f8c34j30ru090gmu.jpg)

- 访问http://localhost:8001/payment/hystrix/timeout/514成功

![image-20200424134639848](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4t3x5jfej30ye06agn0.jpg)



### 10.2.启动JMeter进行压力测试

- 新建线程组

![image-20200424142145615](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4u4fzeekj31dw0u07ak.jpg)

- 新建HTTP请求

![image-20200424142230926](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4u584d2gj31dw0u00zs.jpg)

### 10.3.Hystrix订单微服务构建

> 新建子模块cloud-consumer-feign-hystrix-order8888

#### 10.3.1.POM文件

```xml
<dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.zzx.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础通用配置-->
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

#### 10.3.2.YML文件

```yaml
server:
  port: 8888

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
```

#### 10.3.3.主启动类OrderHystrixMain8888

- com.zzx.springcloud.OrderHystrixMain8888

```java
@SpringBootApplication
@EnableFeignClients
public class OrderHystrixMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain8888.class, args);
    }
}
```

#### 10.3.4.Service层

- com.zzx.springcloud.service.PaymentHystrixService

```java
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
```

#### 10.3.5.Controller层

- com.zzx.springcloud.com.zzx.springcloud.controller.OrderHystrixController

```java
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
}
```

#### 10.3.6.访问测试

- 访问http://localhost:8888/consumer/payment/hystrix/ok/514成功

![image-20200424145725887](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4v5jx5e8j30u2084myh.jpg)

#### 10.3.7.暴露问题分析

- 8001同一层次的其他接口服务被困死，因为tomcat线程池里的工作线程已经被挤占完毕
- 8888(订单微服务)调用8001(支付微服务)，客户端访问响应缓慢，转圈圈



==解决==

- 对方服务(8001)超时了，调用者(8888)不能一直卡死等待，必须有服务降级
- 对方服务(8001)down机了，调用者(8888)不能一直卡死等待，必须有服务降级
- 对方服务(8001)OK，调用者(8888)自己出故障或有自我要求(自己的等待时间)



### 10.4.服务降级

#### 10.4.1.8001自身找问题

- 修改com.zzx.springcloud.service.PaymentService

```java
@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
        //指定3秒钟以内访问到该接口是正常的，否则返回执行配置的fallbackMethod方法
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
public String paymentInfo_TimeOut(Integer id) {
    int sleepTime = 5;//此处从之前的3秒修改为了5秒
    try {
        TimeUnit.SECONDS.sleep(sleepTime);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOut,id:\t" + id + "\t (ಥ_ಥ) " + "耗时(秒):" + sleepTime;
}

public String paymentInfo_TimeOutHandler(Integer id) {
    return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOutHandler,id:\t" + id + "\t ( ̤இॕ⌓இॕ ̤) ° ";
}
```

- 在主启动类com.zzx.springcloud.PaymentHystrixMain8001上新增注解

```java
@EnableCircuitBreaker
```

- 重启8001微服务测试，访问http://localhost:8001/payment/hystrix/timeout/514，发现成功执行paymentInfo_TimeOutHandler方法

![image-20200424152551144](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4vz4ofc5j30vs072jss.jpg)

#### 10.4.2.修改8888

- 在8888的YML配置文件中新增配置

```yaml
feign:
  hystrix:
    enabled: true
```

- 在8888的主启动类新增注解

```java
@EnableHystrix
/*点开@EnableHystrix的源码
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableCircuitBreaker 发现已经有这个注解了，因此只需要新增@EnableHystrix就足够了
public @interface EnableHystrix {

}
*/
```

- Controller层的修改与8001的Service层基本相同

```java
@GetMapping("/consumer/payment/hystrix/timeout/{id}")
@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
})
//@HystrixCommand
public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
    int age = 10/0;
    String result = paymentHystrixService.paymentInfo_TimeOut(id);
    return result;
}
public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
    return "我是消费者8888,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
}
```

- 测试，访问http://localhost:8888/consumer/payment/hystrix/timeout/514

![image-20200424160024360](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4wz2vy8mj311q06ejt1.jpg)



#### 10.4.3.Hystrix全局服务降级

- 在8888的controller层新增方法

```java
// 下面是全局fallback方法
public String payment_Global_FallbackMethod() {
    return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
}
```

- 在controller类上新增注解

```java
//全局fallbacl方法注解
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
```

- 修改8888的paymentInfo_TimeOut方法，防止fallback方法冲突，先注释掉具体的fallback方法，让他执行我们刚才配置的全局fallbak方法

```java
@GetMapping("/consumer/payment/hystrix/timeout/{id}")
//@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//      @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//})
@HystrixCommand
public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
  int age = 10/0;
  String result = paymentHystrixService.paymentInfo_TimeOut(id);
  return result;
}
```

- 测试，访问http://localhost:8888/consumer/payment/hystrix/timeout/514，执行的是payment_Global_FallbackMethod，说明配置成功

![image-20200424160845720](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4x7rnba8j30um06o0ty.jpg)

#### ==10.4.4.模拟宕机服务降级==

- ##### 新增PaymentFallbackService类

  com.zzx.springcloud.service.PaymentFallbackService

```java
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back (ಗдಗ。)°";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall back (ಗдಗ。)°";
    }
}
```

- ##### 在PaymentHystrixService中新增注解属性

```java
//在@FeignClient中新增fallback属性
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
```

- ##### 访问测试

- 访问http://localhost:8888/consumer/payment/hystrix/ok/514成功

![image-20200424162636677](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4xqcqwv1j30w606awfs.jpg)

- 关闭8001微服务模拟服务器宕机

- 再次访问http://localhost:8888/consumer/payment/hystrix/ok/514，成功执行PaymentFallbackService中定义的fallback方法

![image-20200424162741742](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4xrh23pnj30um07m0tx.jpg)



### 10.5.服务熔断

> 熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时,会进行服务的降级，进而熔断该节点微服务的调用,快速返回错误的响应信息。
> ==当检测到该节点微服务调用响应正常后，恢复调用链路。==



#### 10.5.1.代码演示

- 在cloud-provider-hystrix-payment8001的PaymentService新增以下代码

```java
//在10秒窗口期中10次请求有6次是请求失败的,断路器将起作用
@HystrixCommand(
  	fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期，时间范围
    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
})
public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
  if (id < 0) {
    throw new RuntimeException("*****id不能是负数");
  }
  String serialNumber = IdUtil.simpleUUID();
  return Thread.currentThread().getName() + "\t" + "调用成功,流水号:" + serialNumber;
}

public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
  return "id 不能负数,请稍后重试,o(╥﹏╥)o id:" + id;
}
```

- 在PaymentController新增如下代码

```java
//=====服务熔断=====
@GetMapping("/payment/circuit/{id}")
public String paymentCircuitBreaker(@PathVariable("id") Integer id){
    String result = paymentService.paymentCircuitBreaker(id);
    log.info("****result: "+result);
    return result;
}
```

- 测试

  - 访问http://localhost:8001/payment/circuit/514

  ![image-20200424171727064](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4z78qxnfj30zo06qta5.jpg)

  - 访问http://localhost:8001/payment/circuit/-514

  ![image-20200424171920936](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4z97pw4mj30nu06ymy4.jpg)

  - 多次访问http://localhost:8001/payment/circuit/-514，触发服务熔断，此时再次访问http://localhost:8001/payment/circuit/514发现已经无法成功访问，确定此时输入的id是正数514

  ![image-20200424172034249](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4zai0wodj30nw06wdgt.jpg)

  - 经过一段时间的等待，再次访问http://localhost:8001/payment/circuit/514成功，此时调用链路已经成功恢复。

  ![image-20200424172258522](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge4zebbfnuj30yy06e0u5.jpg)

#### 10.5.2.三个重要参数

==快照时间窗、请求总数阀值、错误百分比阀值==

1. 快照时间窗：断路器确定是否打开需要统计一些请求和错误数据, 而统计的时间范围就是快照时间窗，默认为最近的10秒。
2. 请求总数阀值：在快照时间窗内，必须满足请求总数阀值才有资格熔断。默认为20,意味着在10秒内，如果该hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开。
3. 错误百分比阀值：当请求总数在快照时间窗内超过了阀值，比如发生了30次调用,如果在这30次调用中，有15次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%阀值情况下，这时候就会将断路器打开。



#### 10.5.3.断路器开启或关闭的条件

- 当满足一定的阀值的时候( 默认10秒内超过20个请求次数)
- 当失败率达到一定的时候(默认10秒内超过50%的请求失败)
- 到达以上阀值，断路器将会开启
- 当开启的时候，所有请求都不会进行转发
- 一段时间之后(默认是5秒)，这个时候断路器是半开状态，会让其中一一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5



### 10.6.Hystrix图形化

> 创建子模块cloud-consumer-hystrix-dashboard9001

#### 10.6.1.POM文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
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
```
#### 10.6.2.YML文件

```yaml
server:
  port: 9001
```

#### 10.6.3.主启动类

- com.zzx.springcloud.HystrixDashboardMain9001

```java
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
```

#### 10.6.4.所有的Provider都需要监控依赖配置

> 8001，8002

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 10.6.5.访问测试

访问http://localhost:9001/hystrix出现如下图所示页面，则配置成功

![image-20200424183007194](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge51av5oxej31ea0u0k4g.jpg)

#### 10.6.6.修改8001主启动类

- 在cloud-provider-hystrix-payment8001中的PaymentHystrixMain8001新增

```java
/**
 *此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
 *ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
 *只要在自己的项目里配置上下面的servlet就可以了
 */
@Bean
public ServletRegistrationBean getServlet() {
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/hystrix.stream");
    registrationBean.setName("HystrixMetricsStreamServlet");
    return registrationBean;
}
```

#### 10.6.7.配置9001监控8001

填写监控地址http://localhost:8001/hystrix.stream

![image-20200424183816573](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge51jcnx1yj31uq0u04av.jpg)

#### 10.6.8.查看8001微服务

- 多次访问http://localhost:8001/payment/circuit/514的结果

![image-20200424184111854](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge51me26wej31ja0qqjwe.jpg)

- 多次访问http://localhost:8001/payment/circuit/-514的结果

![image-20200424184332492](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge51ouorpcj313c0qewis.jpg)





## 11.Gateway网关

### 11.1.三大核心概念

- #### Route(路由)

> 路由是构建网关的基本模块，它由ID,目标URI,一系列的断言和过滤器组成，如果断言为true则匹配该路由

- #### Predicate(断言)

> 参考的是Java8的java.util.function.Prdicate
> 开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数)，如果请求和断言相匹配则进行路由

- #### Filter(过滤)

> 指的是Spring框架中GatewayFilter的实例，使用过滤器可以在请求被路由前或后对请求进行修改



### 11.2.入门配置

> 新建子模块cloud-gateway-gateway9527

#### 11.2.1.POM文件

```xml
<dependencies>
  <!--gateway-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
  </dependency>
  <!--eureka-client-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
  <dependency>
    <groupId>com.zzx.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
  </dependency>
  <!--一般基础配置类-->
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

#### 11.2.2.YML文件

```yaml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
            #- After=2020-02-21T15:51:37.485+08:00[Asia/Shanghai]
            #- Cookie=username,zzyy
            #- Header=X-Request-Id, \d+  # 请求头要有X-Request-Id属性并且值为整数的正则表达式

eureka:
  instance:
    hostname: cloud-gateway-service
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka
```

#### 11.2.3.主启动类GateWayMain9527

com.zzx.springcloud.GateWayMain9527

```java
@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
```

#### 11.2.4.测试

- 添加网关前，访问http://localhost:8001/payment/get/514

![image-20200424194833077](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge53kgtc87j30rs0gijt9.jpg)

- 添加网关后，访问http://localhost:9527/payment/get/514

![image-20200424194910490](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge53l45ojtj30s40gk0um.jpg)



### 11.3.通过微服务名实现动态路由

> 默认情况下Gateway会根据注册中心的注册列表，以注册中心上微服务名为路径创建动态路由进行转发，从而实现动态路由的功能

#### 11.3.1.前提条件

需要启动一个eureka7001和两个微服务提供者8001和8002

#### 11.3.2.POM文件

需要引入

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

#### 11.3.3.YML文件

修改9527YML配置文件，修改后的YML如下

```yaml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001         #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
            #- After=2020-02-21T15:51:37.485+08:00[Asia/Shanghai]
            #- Cookie=username,zzyy
            #- Header=X-Request-Id, \d+  # 请求头要有X-Request-Id属性并且值为整数的正则表达式

eureka:
  instance:
    hostname: cloud-gateway-service
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka
```

#### 11.3.4.测试

访问http://localhost:9527/payment/lb，可以看到8001和8002端口在依次切换

![image-20200424202057100](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge54i6awxkj30lc07egm9.jpg)

![image-20200424202145099](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge54j09f6tj30l008a74x.jpg)



### 11.4.Predicate的使用

#### 11.4.1.After Route Predicate

- 在yml中新增配置，下面这段配置是只有在After后面配置的时间之后的访问才有效

```yaml
spring:
  cloud:
    gateway:
      routes:
          predicates:
          	#这个时间是由ZonedDateTime.now()得到的
            - After=2020-04-24T20:32:39.605+08:00[Asia/Shanghai]
```

- 如果将时间从8:32修改到9:32，这样访问肯定不会在9:32之后，页面会报404异常

![image-20200424203805944](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5500snjlj31fw0l4wkd.jpg)

#### 11.4.2.Before Route Predicate

> Before和After同理这里不再赘述和实验了

#### 11.4.3.Between Route Predicate

> Between和After同理，唯一不同的是Between需要两个时间，即一个时间范围。

#### 11.4.4.Cookie Route Predicate

> Cookie Route Predicate需要两个参数，一个是Cookie name，一个是正则表达式
>
> 路由规则会通过获取相应的Cookie name值和正则表达式去匹配，如果匹配上就会执行路由，反之则不执行

- 在YML中新增配置，这段配置是只有带着username-zzx键值对来才能成功访问

```yaml
spring:
  cloud:
    gateway:
      routes:
          predicates:
            - Cookie=username,zzx
```

- 在终端中使用`curl http://localhost:9527/payment/lb`命令来访问，发现也报了404error

![image-20200424205116098](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge55dq5r95j313m09utck.jpg)

- 当请求路径后加了cookie并且符合上面配置的格式，成功返回了8001和8002端口

![image-20200424205444049](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge55hbsu5qj30ve04gq3z.jpg)

#### 11.4.5.Header Route Predicate

> 两个参数：一个是属性名称一个是正则表达式，这个属性值和正则表达式匹配执行

- 在YML文件中新增配置，该配置要求请求头要有X-Request-Id属性并且值为整数，\d+是正则表达式

```yaml
spring:
  cloud:
    gateway:
      routes:
          predicates:
          	- Header=X-Request-Id, \d+  
```

- 访问测试，由此可见只有请求路径带上符合正则表达式的请求头才能成功访问，否则又是404

![image-20200424210052388](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge55nq5gqpj30v406oq61.jpg)

#### 11.4.6.Host Route Predicate

#### 11.4.7.Method Route Predicate

#### 11.4.8.Path Route Predicate

> Path就是已经配置的那个Path

#### 11.4.9.Query Route Predicate



### 11.5.Filter的使用

。。。



## 12.SpringCloud Config

> 简介：在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件。Spring Cloud Config项目是就是这样一个解决分布式系统的配置管理方案。它包含了Client和Server两个部分，server提供配置文件的存储、以接口的形式将配置文件的内容提供出去，client通过接口获取数据、并依据此数据初始化自己的应用。

SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为==各个不同微服务应用==的所有环境提供了一个==中心化的外部配置==。



- SpringCloud Config作用
  - 集中管理配置文件
  - 不同环境不同配置，动态化的配置更新,分环境部署比如dev/test/prod/beta/release
  - 运行期间动态调整配置，不再需要在每个服务部署的机器.上编写配置文件，服务会向配置中心统一拉取配置自己的信息
  - 当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
  - 将配置信息以REST接口的形式暴露



### 12.1.Config服务端配置

#### 12.1.1.新建远程仓库

> 在GitHub上新建一个名为springcloud-config的Repository

#### 12.1.2.获取git地址

> HTTPS:https://github.com/cuteSatomi/springcloud-config.git
>
> SSH:git@github.com:cuteSatomi/springcloud-config.git



==**新建子模块cloud-config-center3344**==



#### 12.1.3.POM文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
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
```
#### 12.1.4.YML文件

```yaml
server:
  port: 3344

spring:
  application:
    name:  cloud-config-center #注册进Eureka服务器的微服务名
  cloud:
    config:
      server:
        git:
        	#这里的uri使用SSH会报错，只能用https，是什么原因目前还没搞清楚
          uri: https://github.com/cuteSatomi/springcloud-config.git #GitHub上面的git仓库名字
          ####搜索目录
          search-paths:
            - springcloud-config
      ####读取分支
      label: master

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

#### 12.1.5.主启动类ConfigCenterMain3344

- com.zzx.springcloud.ConfigCenterMain3344

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
```

#### 12.1.6.在/etc/hosts下新增映射

```
127.0.0.1	config-3344.com
```

#### 12.1.7.上传配置文件到GitHub

- 初始化本地仓库

![image-20200425131909093](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5xxn5qq3j313u01q3z2.jpg)

- 新建config-dev.yml并提交到本地库

![image-20200425131958187](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5xyg6xorj317803un1k.jpg)

- 新建远程仓库地址别名

![image-20200425132054090](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5xzf8n3nj31je04cdil.jpg)

- 推送到远程库

![image-20200425132122712](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5xzxv2bnj310607ymz8.jpg)

- 查看远程库上传成功

![image-20200425132203163](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5y0mj6euj31jc0ko0x8.jpg)

#### 12.1.8.读取配置规则

> Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上直接访问就可以。

```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

{application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如上面创建的配置文件。

{profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-sit.yml、application-prod.yml。

{label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。

#### 12.1.9.访问测试

- 访问http://config-3344.com:3344/master/config-dev.yml成功看到了config-dev.yml的内容

![image-20200425133746069](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5ygyxuwaj30v8076q48.jpg)



### 12.2.Config客户端配置

> 新建子模块cloud-config-client3355

#### 12.2.1.POM文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
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
```
#### 12.2.2.YML文件

> application.yml是用户级的资源配置项
>
> bootstrap.yml是系统级的，优先级更高
>
> 因此要将Client模块下的application.yml改为bootstrap.yml，因为bootstrap.yml是比application.yml先加载的。

```yaml
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址k

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

#### 12.2.3.主启动类

- com.zzx.springcloud.ConfigClientMain3355

```java
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
```

#### 12.2.4.controller层

- com.zzx.springcloud.com.zzx.springcloud.controller.ConfigClientController

```java
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

#### 12.2.5.访问测试

通过3355客户端访问服务器成功访问到了config-dev.yml下的config.info内容

http://localhost:3355/configInfo

![image-20200425171251294](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge64oryr49j30qe06mt9p.jpg)

### 12.3.分布式配置的动态刷新问题

#### 12.3.1.修改config-dev.yml

> 将版本从1更新到2

![image-20200425172118237](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge64xkait4j30u60cu402.jpg)

#### 12.3.2.刷新3344发现已经响应

http://config-3344.com:3344/master/config-dev.yml

![image-20200425172240526](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge64yzadkjj30v406wmyg.jpg)

#### 12.3.3.刷新3355发现仍为旧值

http://localhost:3355/configInfo，3355除非重新加载或者重启，否则无法读取修改后的配置文件，重启后重新访问，发现才读取到了最新的配置

![image-20200425173008444](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge656r7o0kj30qk06c75a.jpg)

### 12.4.动态刷新问题的解决

> 避免每次修改配置都需要重新启动微服务3355，需要修改cloud-config-client3355模块

#### 12.4.1.POM引入actuator监控

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 12.4.2.修改YML，暴露监控端口

> 在YML文件新增配置，以下配置直接配置在根结点上

```yaml
# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

#### 12.4.3.修改ConfigClientController

> 在ConfigClientController类上新增注解@RefreshScope

#### 12.4.4.重启3355后修改config-dev.yml

> 修改config-dev.yml版本号为3

![image-20200425173708323](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge65e11b0wj30wa0cymyr.jpg)

#### 12.4.5.进行测试

- 访问http://config-3344.com:3344/master/config-dev.yml，发现读取到的是最新的version3的配置

![image-20200425173850054](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge65fsqr4nj30uw06k75k.jpg)

- 访问http://localhost:3355/configInfo，发现读取到的还是version=2，配置并没有生效

![image-20200425173008444](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge656r7o0kj30qk06c75a.jpg)

#### 12.4.6.需要发送POST请求

> 动态刷新的前提是修改配置后需要发送一个POST请求来刷新3355

curl -X POST "http://localhost:3355/actuator/refresh"

![image-20200425175259232](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge65uvh6iej30ro01mweu.jpg)

#### 12.4.7.再次访问测试

访问http://localhost:3355/configInfo发现成功读取到了版本3的配置

![image-20200425175207559](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge65vzl3adj30qe0680tq.jpg)