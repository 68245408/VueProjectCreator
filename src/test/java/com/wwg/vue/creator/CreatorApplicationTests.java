package com.wwg.vue.creator;

import com.github.pagehelper.PageHelper;
import com.wwg.vue.creator.mbg.mapper.SysVueResourceMapper;
import com.wwg.vue.creator.mbg.model.SysVueResource;
import com.wwg.vue.creator.mbg.model.SysVueResourceExample;
import com.wwg.vue.creator.service.VueProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreatorApplicationTests {
    @Autowired
    private SysVueResourceMapper mapper;

    @Autowired
    private VueProjectService vueProjectService;

    @Test
    public void contextLoads() {
    }
    @Test
    public void test1(){
        vueProjectService.createRouter();
        vueProjectService.createMainJs();
        vueProjectService.createIndexHtml();
    }

}
