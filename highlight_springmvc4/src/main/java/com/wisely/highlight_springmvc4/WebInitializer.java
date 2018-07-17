package com.wisely.highlight_springmvc4;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * WebApplicationInitializer是spring提供用来配置servlet3.0+配置的接口，从而实现了替代web.xml的位置
 * 实现此接口将会自动被springServletContainerInitializer获取到
 */
public class WebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 新建webApplicationContext,注册配置类，并将其和当前servletContext关联
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);
        //注册springMVC 的dispatherServlet
        Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }
}
