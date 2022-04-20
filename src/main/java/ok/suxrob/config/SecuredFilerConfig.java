package ok.suxrob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilerConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> filterRegistrationBean() {

        FilterRegistrationBean<JwtTokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(jwtTokenFilter);
        bean.addUrlPatterns("/profile/private/*");
        bean.addUrlPatterns("/ShoOrCoPost/private/*");
        bean.addUrlPatterns("/ShoOrCoPost/private/*");
        bean.addUrlPatterns("/announcement/private/*");
        bean.addUrlPatterns("/make/private/*");
        bean.addUrlPatterns("/attach/private/*");

        return bean;
    }

}
