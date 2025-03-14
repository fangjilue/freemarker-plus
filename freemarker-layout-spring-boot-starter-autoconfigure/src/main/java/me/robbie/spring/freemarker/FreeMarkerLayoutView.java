package me.robbie.spring.freemarker;

import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 支持布局的FreeMarkerView
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-22 23:22
 * @since [产品/模块版本]
 */
public class FreeMarkerLayoutView extends FreeMarkerView {

    public static final Logger logger = LoggerFactory.getLogger(FreeMarkerLayoutView.class);

    private String suffix = FreeMarkerProperties.DEFAULT_SUFFIX;

    private Map<String, String> fragmentTemplateMap = new HashMap();

    /**
     * 获取模板后缀名
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        FreeMarkerProperties freeMarkerProperties = BeanFactoryUtils.beanOfTypeIncludingAncestors(
                getApplicationContext(), FreeMarkerProperties.class);

        if (freeMarkerProperties.getSuffix() != null) {
            suffix = freeMarkerProperties.getSuffix();
        }

        FreeMarkerPlusConfig freeMarkerConfig = BeanFactoryUtils.beanOfTypeIncludingAncestors(
                getApplicationContext(), FreeMarkerPlusConfig.class
        );
        fragmentTemplateMap = freeMarkerConfig.getMap();
        logger.info("fragmentTemplateMap size = {}", fragmentTemplateMap.size());
    }

    /**
     * 通过视图url 获取fragment文件路径并注入到model
     * fragment@layout: 页面片断@布局
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);
        // index.suffix
        String path = "/" + getUrl();
        model.put("fragment_file_path", path);
    }

    /**
     * 通过视图名获取真正的模板
     *
     * @param name index.suffix
     * @param locale
     * @return
     * @throws IOException
     */
    @Override
    protected Template getTemplate(String name, Locale locale) throws IOException {
        int index = name.indexOf(suffix);
        if (index > 0) {
            String keyName = name.substring(0, index);
            if (fragmentTemplateMap.containsKey(keyName)) {
                String layout = fragmentTemplateMap.get(keyName);
                return super.getTemplate(layout, locale);
            }
        }

        return super.getTemplate(name, locale);
    }
}
