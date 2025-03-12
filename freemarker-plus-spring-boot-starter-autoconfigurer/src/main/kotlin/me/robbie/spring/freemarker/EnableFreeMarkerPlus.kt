package me.robbie.spring.freemarker

import org.springframework.context.annotation.Import

/**
 * 代替主应用入口
 *  @Import(FreeMarkerLayoutViewConfiguration::class)
 *  scanBasePackages ="me.robbie.spring.freemarker"
 *  与 org.springframework.boot.autoconfigure.AutoConfiguration.imports 二选一
 */
/*
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Import(
    FreeMarkerLayoutViewConfiguration::class
)*/
annotation class EnableFreeMarkerPlus