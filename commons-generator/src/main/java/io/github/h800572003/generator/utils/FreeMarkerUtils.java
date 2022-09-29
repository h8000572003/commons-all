package io.github.h800572003.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class FreeMarkerUtils {


    public static String toString(String freeFileName, Object obj){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        //指定樣版所在位置 (這裡是classpath的templates資料夾底下)
        cfg.setClassForTemplateLoading(FreeMarkerUtils.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");

        //封裝資料的物件，可以是一般物件，也可以是List, Map 等集合物件


        //取得樣版處理後輸出到 StringWriter
        StringWriter sw = new StringWriter();
        try {
            cfg.getTemplate(freeFileName).process(obj, sw);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //透過StringWriter的toString()取得內容，後續可以進行處理
        return  sw.toString();
    }
}
