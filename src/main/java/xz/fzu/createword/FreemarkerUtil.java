package xz.fzu.createword;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * freemarker 工具类
 * @author LITM
 * @since 2019年5月22日
 */
public class FreemarkerUtil {

    private static Configuration config = null;

    /**
     * Static initialization.
     *
     * Initialize the configuration of Freemarker.
     */
    static{
        config = new Configuration();
    }

    public static Configuration getConfiguration(){
        return config;
    }

    /**
     * @param template
     * @param variables
     * @return
     * @throws Exception
     */
    public static String generate(String ftlName, Object data,String ftlPath) throws IOException, TemplateException {
        Configuration config = getConfiguration();
        config.setDefaultEncoding("UTF-8");
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        Template tp = config.getTemplate(ftlName);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        tp.setEncoding("UTF-8");
        tp.process(data, writer);
        String htmlStr = stringWriter.toString();
        writer.flush();
        writer.close();
        return htmlStr;
    }
}
