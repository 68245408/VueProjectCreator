package com.wwg.vue.creator.service;


import org.springframework.ui.Model;

public interface VueProjectService {
    /**
     * 生成路由文件 router.js.ftl
     * 采用freeMarker模板
     */
    void createRouter();
    void createMainJs();

    /**
     * 生成首页
     * 采用thymeleaf模板
     */
    void createIndexHtml();
}
