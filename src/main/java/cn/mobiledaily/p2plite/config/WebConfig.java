package cn.mobiledaily.p2plite.config;

/**
 * Created by johnson on 14-10-6.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by johnson on 14-10-6.
 */
@Configuration
@ComponentScan("cn.mobiledaily.p2plite.web")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String PROP_FILE_ENCODING = "GBK";

    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("config.properties")};
        configurer.setLocations(resources);
        configurer.setFileEncoding(PROP_FILE_ENCODING);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/idcard/**").addResourceLocations("file:///c:/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}