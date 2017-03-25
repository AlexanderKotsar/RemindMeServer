package xyz.kots.remindme.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration   // конфигурация, которую нужно выполнить перед тем, как разварачивать контекст спринга
@EnableWebMvc    // вкл. режим WebMVC -> дает возможность исп. Controller & RESTController
@ComponentScan("xyz.kots.remindme.server")   // указываем, где искать  Bean, Controller и т.д.
public class WebConfig extends WebMvcConfigurerAdapter {


}
