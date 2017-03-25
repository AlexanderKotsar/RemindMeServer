package xyz.kots.remindme.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("xyz.kots.remindme.server.repository") // говорим Spring контексту что будем исп. JPA и указываем, в каком пакете будут лежать репозитории
@EnableTransactionManagement //вкл. поддержку транзакций
@PropertySource("classpath:db.properties")
@ComponentScan("xyz.kots.remindme.server")
public class DataBaseConfig {

    @Resource
    // с пом. env сможем получать доступ к property файлам и получать из них опр. значения
    private Environment env;
    private Properties hibernateProperties;

    @Bean
    // бин для работы с БД
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean // метод, который нужно развернуть на момент развертывания приложения
    private DataSource dataSource(){

        BasicDataSource ds = new BasicDataSource();

        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));


        return ds;
    }

    public Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();

            //подгружаем файл с зависимостями
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");

            properties.load(is);

            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find hibernate.properties in classpath!", e);
        }

    }
}
