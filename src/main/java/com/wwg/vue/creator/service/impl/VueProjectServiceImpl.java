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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
            params.put("package","io.demo.generator");
            params.put("mapper","dao");
            params.put("author",list.get(1).getName());
            params.put("date",ft.format(dNow));
            params.put("comments","这是router模板");
            params.put("className","GenerateFile");
            params.put("mapperSuffix","Mapper");

            //给文件赋值
            Template template = configuration.getTemplate("router.js");
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
}
