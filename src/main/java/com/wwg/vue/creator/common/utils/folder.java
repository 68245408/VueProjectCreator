package com.wwg.vue.creator.common.utils;

import java.io.File;

public class folder {
    public static void checkFolder(String path){
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
