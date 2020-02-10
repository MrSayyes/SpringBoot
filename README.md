# 一、SpringBoot介绍

## 1、SpringBoot简介

> Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。

## 2、SpringBoot特点

- 可以创建独立的[Spring](https://baike.baidu.com/item/Spring/85061)应用程序，并且基于其Maven或Gradle插件，可以创建可执行的JARs和WARs；
- 内嵌Tomcat或Jetty等Servlet容器；
- 提供自动配置的“starter”项目对象模型（POMS）以简化[Maven](https://baike.baidu.com/item/Maven/6094909)配置；
- 尽可能自动配置Spring容器；
- 提供准备好的特性，如指标、健康检查和外部化配置；
- 绝对没有代码生成，不需要XML配置。

## 3、SpringBoot启动器

- **pom.xml**依赖spring-boot-starter-*来使用

# 二、SpringBoot学习过程

## 1、SpringBoot环境准备

- Java安装配置
- Maven安装配置
- Eclipse设置Maven

## 2、第一个SpringBoot程序（Hello World）

### 2.1 Eclipse创建Maven项目工程（jar）

- 见==“01-HelloWorld”==工程

### 2.2 导入SpringBoot的相关依赖（从远程仓库下载到本地仓库并依赖）

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.3.RELEASE</version>
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### 2.3 编写一个主程序：启动SpringBoot应用

```java
package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 标注一个主程序类说明SpringBoot应用
 * 
 * @author sayyes
 */
@SpringBootApplication
public class HelloWorldApplication {
	public static void main(String[] args) {
		// Spring应用启动起来
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}

```

### 2.4 编写相关的Controller类

==该类必须在主程序包子包下面，因为注解扫描扫描主程序包以及子包==

```java
package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller 处理主程序请求
 * @author sayyes
 */
@Controller
public class HelloController {

	// 接收来自浏览器的hello请求
	// 把返回返回给浏览器
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}
}

```

### 2.5 运行测试

- Eclipse使用Run AS -> Spring Boot App执行主程序
- Maven工程打成jar包，使用java -jar 工程jar包来运行

## 3、Hello World探究

### 3.1 pom.xml文件

#### 3.1.1 父项目依赖

```xml
<!-- 01-HelloWorld工程 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.3.RELEASE</version>
</parent>

<!-- 父项目工程，Spring Boot版本仲裁中心，里面包含的依赖不需要设置版本 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.3.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>

<!-- 父项目的父项目工程,这里设置了依赖包的版本 -->
<properties>
    <activemq.version>5.15.11</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    ...
    <xmlunit2.version>2.6.3</xmlunit2.version>
</properties>
```

#### 3.1.2 导入的web依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**spring-boot-starter**-==web==（这里依赖web模块）

spring-boot-starter：spring-boot场景启动器。 这里会帮忙导入所需模块正常运行所依赖的组件

SpringBoot将所有的功能场景都抽取出来，做成了一个个starters，项目根据所需进行导入所需启动器

### 3.2 主程序类

```java
package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 标注一个主程序类说明SpringBoot应用
 * 
 * @author sayyes
 */
@SpringBootApplication
public class HelloWorldApplication {
	public static void main(String[] args) {
		// Spring应用启动起来
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}

```

- @**SpringBootApplication**：主程序类，==很重要==，实现类SpringBootApplication.java

- SpringBootApplication.java实现类中有如下程序：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
    ...
}
```

@**SpringBootConfiguration**：SpringBoot的配置类
@**EnableAutoConfiguration**：开启自动配置功能；

逐步深入，可以一层一层看代码...

## 4、Eclipse使用Spring Starter Project快速创建SpringBoot项目（02-HelloWorldQuick）

IDE都支持使用Spring的项目创建向导快速创建（==需联网==）

### 4.1 向导界面

![向导界面](images\SpringStarterProject.png)

next >>>>>>>>>>>

![向导界面](images\SpringStarterProject2.png)

### 4.2 quick创建目录

- 删除多余文件

![目录](images\SpringStarterProject3.png)

### 4.3编写类测试

- 快速创建会帮忙创建好主程序类，所以编写Controller类即可

```java
package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody
// @org.springframework.stereotype.Controller
// => @RestController
@RestController
public class Controller {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World Quick";
	}
}

```

### 4.4目录说明

- java放java资源
- resources
  - static：静态资源；js、css、images
  - templates：模板页面资源；默认jsp不支持，要使用模板引擎
  - application.properties：修改默认配置，比如改8080默认端口

## 5、SpringBoot配置

### 5.1 默认两种类型的配置文件

- application.properties

```xml
server.port=8081
```

- application.yml

```yml
server:
  prot:8081
```

修改SpringBoot的默认配置（SpringBoot在底层自动配置好了）

### 5.2 YAML配置文件（需要进行单独学习语法）

- 语法不累述
- SpringBoot项目类注解@ConfigurationProperties(prefix = "这里是配置文件的id")与SpringBoot配置文件绑定获取值

### 5.3 SpringBoot配置文件与类的关联

#### 5.3.1新增类

- 对象类，用于Person类

```java
package com.test.bean;

public class Dog {
	private String name;
	private Integer age;

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}

```

- Person类，管理SpringBoot配置文件（==使用注解绑定==）

```java
package com.test.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties 与配置文件进行绑定 prefix 用于配置对应配置文件中的id
 * 
 *                          只有组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能（这里用@Component加入容器）
 * @author sayyes
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

	//@Value("")可以替换@ConfigurationProperties(prefix = "person")
	//@value需要每个属性都要配置，可以用公式
	private String lastName;
	private Integer age;
	private Boolean boss;
	private Date birth;
	private Map<String, Object> maps;
	private List<Object> lists;
	private Dog dog;

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", age=" + age + ", boss=" + boss + ", birth=" + birth + ", maps="
				+ maps + ", lists=" + lists + ", dog=" + dog + "]";
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getBoss() {
		return boss;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public List<Object> getLists() {
		return lists;
	}

	public void setLists(List<Object> lists) {
		this.lists = lists;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

}

```

#### 5.3.2 application.properties配置文件映射到Person中

- 这里的pom.xml可以加一个包依赖，可以进行提示

```xml
<!-- 配置文件处理器, 配置文件绑定值会进行提示 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

- 配置文件添加Person值

```xml
# 配置person的值
person.lastName=zhangsan
person.age=11
person.birth=2017/12/15
person.boss=false
person.maps.k1=v1
person.maps.k2=14
person.lists=a,b,c
person.dog.name=dog
person.dog.age=15
```

- SpringBoot测试类（注入Person进行测试）

```java
package com.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.bean.Person;

/**
 * SpringBoot单元测试
 * 可以注入其他容器内容进行测试
 * @author sayyes
 */
@SpringBootTest
class ApplicationTests {
	//@Autowired注入Person对象
	@Autowired
	Person person;

	@Test
	void contextLoads() {
		System.out.println(person);
	}

}

```

#### 5.3.3 @value与@ConfigurationProperties(prefix = "person")区别

|                | @ConfigurationProperties  | @value           |
| -------------- | ------------------------- | ---------------- |
| 功能           | 批量注入配置文件的属性    | 一个一个处理     |
| 松散绑定       | 支持（如大写和-小写同等） | 不支持           |
| SpEl           | 不支持                    | 支持（如#{1*2}） |
| JSR303数据校验 | 支持（如邮箱@Email）      | 不支持           |
| 复杂类型封装   | 支持                      | 不支持           |

配置文件yml和properties都可以，两种注入绑定取值区别的处理

==如果说，我们只是在某个业务逻辑中需要获取配置文件的某项值就使用@value==

==如果说，我们专门编写了一个javaBean来和配置文件进行映射就使用@ConfigurationProperties==

### 5.4 @PropertySource(value = {"classpath:"})

