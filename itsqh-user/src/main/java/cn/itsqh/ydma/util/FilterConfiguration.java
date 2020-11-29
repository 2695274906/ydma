package cn.itsqh.ydma.util;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> crosFilter(){

        FilterRegistrationBean<Filter> bean=new FilterRegistrationBean<Filter>();
        bean.setFilter(new CorsFilter());
        ArrayList<String> servletNames=new ArrayList<>();

        servletNames.add("dispatcherServlet");
        bean.setServletNames(servletNames);
        return  bean;

    }


}
