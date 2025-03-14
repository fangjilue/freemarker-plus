package me.robbie.spring.freemarker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


@Configuration
@EnableConfigurationProperties(FreeMarkerPlusConfig.class)
public class FreeMarkerLayoutViewConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "freemarker.plus.template", name = "enabled", matchIfMissing = true)
    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver){
        System.out.println("---------customFreemarker---------");
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                //增加视图
                resolver.setViewClass(FreeMarkerLayoutView.class);
            }
        };
    }
}
