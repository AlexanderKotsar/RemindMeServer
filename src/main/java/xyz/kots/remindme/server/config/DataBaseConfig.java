package xyz.kots.remindme.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@EnableJpaRepositories("xyz.kots.remindme.server.repository") // говорим Spring контексту что будем исп. JPA и указываем, в каком пакете будут лежать репозитории
@EnableTransactionManagement //вкл. поддержку транзакций
@PropertySource("classpath:db.properties")
@ComponentScan("xyz.kots.remindme.server")
public class DataBaseConfig {

    @Resource
    // с пом. env сможем получать доступ к property файлам и получать из них опр. значения
    private Environment env;

}
