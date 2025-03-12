package me.robbie.spring.freemarker

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "freemarker.plus.template")
class FreeMarkerPlusConfig {
    var map: Map<String, String> = HashMap()
}
