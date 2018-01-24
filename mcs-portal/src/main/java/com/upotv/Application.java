package com.upotv;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot提倡基于Java的配置。尽管你可以使用XML源调用SpringApplication.run()，不过还是建议你使用@Configuration 类作为主要配置源
 * <p>
 * SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration
 * <p>
 * EnableAutoConfiguration 注解到你的main类上，这样就隐式地定义了一个基础的包搜索路径（search package），
 * 以搜索某些特定的注解实体（比如@Service，@Component等）,一般只用于主类，
 * EnableAutoConfiguration 是无xml配置启动的关键部分,明确指定了扫描包的路径为其修饰的主类的包（这也就是为什么主类要放在根包路径下的原因）
 * <p>
 * ComponentScan 进行包的扫描，扫描路径由@EnableAutoConfiguration指定了
 * 不需要将所有的 @Configuration 放进一个单独的类， @Import 注解可以用来 导入其他配置类。
 * 另外，你也可以使用 @ComponentScan 注解自动收集所有 Spring组件，包括 @Configuration
 * <p>
 * 如果必须使用XML配置，建议你仍旧从一个 @Configuration 类开始，然后使用@ImportResource 注解加载XML配置文件。
 * <p>
 * Created by tianapple on 2017/5/9.
 *
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@RestController
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
