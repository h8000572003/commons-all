package io.github.h800572003.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.github.h800572003.generator.NewFileException;

import java.io.IOException;
import java.io.StringWriter;

public class FreeMarkerUtils {


    public static String toString(String freeFileName, Object obj) {
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_19);
        cfg.setClassForTemplateLoading(FreeMarkerUtils.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");
        StringWriter sw = new StringWriter();
        try {
            cfg.getTemplate(freeFileName).process(obj, sw);
        } catch (TemplateException e) {
            throw new NewFileException(e);
        } catch (IOException e) {
            throw new NewFileException(e);
        }
        return sw.toString();
    }
}
