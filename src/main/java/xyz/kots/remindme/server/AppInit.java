package xyz.kots.remindme.server;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import xyz.kots.remindme.server.config.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInit implements WebApplicationInitializer {

    private final static  String DISPATCHER = "dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {

        // создаем контекст, который будем регестрировать в сервлет
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // регестрируем наш WebConfig
        ctx.register(WebConfig.class);

        // подгружаем контекст в сервлет
        servletContext.addListener(new ContextLoaderListener(ctx));

        // добавляем servletContext в DispatcherServlet и мапим его на определенный URL
        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));

        // указываем, по какому контекст-руту будет доступно наше приложение
        servlet.addMapping("/");

        // если мапим несколько сервлетов, указываем в каком порядке они должны быть инициализированы
        servlet.setLoadOnStartup(1);
    }
}
