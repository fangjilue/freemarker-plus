package me.robbie.spring.freemarker

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver


@Configuration
@EnableConfigurationProperties(FreeMarkerPlusConfig::class)
open class FreeMarkerLayoutViewConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "freemarker.plus.template", name = ["enabled"], matchIfMissing = true)
    open fun customFreeMarkerViewResolver(freeMarkerViewResolver: FreeMarkerViewResolver): CommandLineRunner {
        println("customFreemarker----FreeMarkerViewResolver------" + freeMarkerViewResolver)
        return CommandLineRunner { //增加视图
            freeMarkerViewResolver.setViewClass(FreeMarkerLayoutView::class.java)
        }
    }
}
