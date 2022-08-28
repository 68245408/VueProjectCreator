package com.wwg.vue.creator;

import com.wwg.vue.creator.mbg.mapper.SysVueResourceMapper;
import com.wwg.vue.creator.mbg.model.SysVueResource;
import com.wwg.vue.creator.mbg.model.SysVueResourceExample;
import com.wwg.vue.creator.service.VueProjectService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VueCreater {

    @Autowired
    private VueProjectService vueProjectService;

    @RequestMapping("/vueProject")
    public void vueProject(Model model) throws IOException {
        vueProjectService.createRouter();
    }

}
