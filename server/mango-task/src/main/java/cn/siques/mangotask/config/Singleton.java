package cn.siques.mangotask.config;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton {
//    private static final Singleton singleton  = new Singleton();

    public static final ConcurrentHashMap hashMap = new ConcurrentHashMap<>();

    private Singleton(){}


    public static ConcurrentHashMap getInstance(){
        return hashMap;
    }

}
