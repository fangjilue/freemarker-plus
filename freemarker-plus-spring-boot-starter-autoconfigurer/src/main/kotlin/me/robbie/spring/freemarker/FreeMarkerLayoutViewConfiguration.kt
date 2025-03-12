package me.robbie.spring.freemarker

import jakarta.servlet.Servlet
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver


@Configuration
@EnableConfigurationProperties(FreeMarkerPlusConfig::class)
open class FreeMarkerLayoutViewConfiguration {

    @Bean
    //@ConditionalOnProperty(prefix = "freemarker.plus.template", name = ["default"], matchIfMissing = false)
    //@ConditionalOnBean(value = [FreeMarkerViewResolver::class])
    open fun customFreeMarkerViewResolver(freeMarkerViewResolver: FreeMarkerViewResolver): CommandLineRunner {
        println("customFreemarker----FreeMarkerViewResolver------" + freeMarkerViewResolver)
        return CommandLineRunner { //增加视图
            freeMarkerViewResolver.setViewClass(FreeMarkerLayoutView::class.java)
        }
    }
}
