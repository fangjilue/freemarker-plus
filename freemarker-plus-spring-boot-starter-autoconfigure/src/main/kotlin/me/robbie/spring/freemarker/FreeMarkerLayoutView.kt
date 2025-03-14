package me.robbie.spring.freemarker

import freemarker.template.Template
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanFactoryUtils
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties
import org.springframework.web.servlet.view.freemarker.FreeMarkerView
import java.io.IOException
import java.util.*


/**
 * 支持布局的FreeMarkerView
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-22 23:22
 * @since [产品/模块版本]
 */
class FreeMarkerLayoutView : FreeMarkerView() {
    private val log: Logger = LoggerFactory.getLogger(FreeMarkerLayoutView::class.java)

    private var suffix = FreeMarkerProperties.DEFAULT_SUFFIX

    private var fragmentTemplateMap: Map<String, String> = HashMap()

    /**
     * 获取模板后缀名
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        log.info("FreeMarkerLayoutView: {} , {}", this, suffix)
        super.afterPropertiesSet()

        val freeMarkerProperties = BeanFactoryUtils.beanOfTypeIncludingAncestors(
            applicationContext!!, FreeMarkerProperties::class.java
        )
        suffix = freeMarkerProperties.suffix


        val freeMarkerConfig = BeanFactoryUtils.beanOfTypeIncludingAncestors(
            applicationContext!!, FreeMarkerPlusConfig::class.java
        )
        fragmentTemplateMap = freeMarkerConfig.map
        log.info("fragmentTemplateMap size = {}", fragmentTemplateMap.size)
    }

    /**
     * 通过视图url 获取fragment文件路径并注入到model
     * fragment@layout: 页面片断@布局
     * @param model
     * @param request
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun exposeHelpers(model: MutableMap<String, Any>, request: HttpServletRequest) {
        super.exposeHelpers(model, request)
        model["fragment_file_path"] = "/$url"
    }

    /**
     * 通过视图名获取真正的模板
     * @param name
     * @param locale
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun getTemplate(name: String, locale: Locale): Template {
        val index = name.indexOf(suffix)
        if (index > 0) {
            val keyName = name.substring(0, index)
            if (fragmentTemplateMap.containsKey(keyName)) {
                val layout = fragmentTemplateMap[keyName]

                return super.getTemplate(layout!!, locale)
            }
        }

        return super.getTemplate(name, locale)
    }

}
