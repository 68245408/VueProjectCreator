package com.wwg.vue.creator.service.impl;

import com.wwg.vue.creator.mbg.mapper.SysVueResourceMapper;
import com.wwg.vue.creator.mbg.model.SysVueResource;
import com.wwg.vue.creator.mbg.model.SysVueResourceExample;
import com.wwg.vue.creator.service.VueProjectService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VueProjectServiceImpl implements VueProjectService {
    @Autowired
    private SysVueResourceMapper sysVueResourceMapper;

    @Override
    public void createRouter(){
        Configuration configuration = new Configuration(new Version("2.3.3"));
        configuration.setDefaultEncoding("utf-8");
        String absolutePath = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\js";
        try{
            // 加载.ftl配置文件所在路径
            configuration.setDirectoryForTemplateLoading(new File(absolutePath));

            // 放模板变量的值
            Map<String,Object> params = new HashMap<>();
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            SysVueResourceExample example=new SysVueResourceExample();
            SysVueResourceExample.Criteria criteria=example.createCriteria();
            List<SysVueResource> list =sysVueResourceMapper.selectByExample(example);
            params.put("list",list);
            params.put("mapper","dao");
            params.put("author",list.get(1).getName());
            params.put("date",ft.format(dNow));
            params.put("comments","这是router模板");
            params.put("className","GenerateFile");
            params.put("mapperSuffix","Mapper");

            //给文件赋值
            Template template = configuration.getTemplate("router.js.ftl");
            // 文件输出路径
            FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") +"\\output\\router.js");
            OutputStreamWriter out = new OutputStreamWriter(file, "utf-8");
            template.process(params,out);
            System.out.println("router.js创建成功！");
            out.close();
        }catch (IOException | TemplateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void createMainJs(){
        Configuration configuration = new Configuration(new Version("2.3.3"));
        configuration.setDefaultEncoding("utf-8");
        String absolutePath = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\js";
        try{
            // 加载.ftl配置文件所在路径
            configuration.setDirectoryForTemplateLoading(new File(absolutePath));

            // 放模板变量的值
            Map<String,Object> params = new HashMap<>();
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            params.put("package","io.demo.generator");
            params.put("mapper","dao");
            params.put("author","wwg");
            params.put("date",ft.format(dNow));
            params.put("comments","freemarker模板");
            params.put("className","GenerateFile");
            params.put("mapperSuffix","Mapper");

            //给文件赋值
            Template template = configuration.getTemplate("main.js.ftl");
            // 文件输出路径
            FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") +"\\output\\main.js");
            OutputStreamWriter out = new OutputStreamWriter(file, "utf-8");
            template.process(params,out);
            out.close();
        }catch (IOException | TemplateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void createIndexHtml(){
        Model model=new ConcurrentModel();
        model.addAttribute("users", "wwg");

        //创建模版加载器
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");  //模板文件的所在目录
        resolver.setSuffix(".html");       //模板文件后缀
        //创建模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        //将加载器放入模板引擎
        templateEngine.setTemplateResolver(resolver);
        //创建字符输出流并且自定义输出文件的位置和文件名
        File dest = new File(System.getProperty("user.dir") +"/output/", "index.html");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(dest, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //创建Context对象(存放Model)
        Context context = new Context();
        //放入数据
        context.setVariable("hello","msg");
        //创建静态文件,"text"是模板html名字
        templateEngine.process("index",context,writer);
    }
}
