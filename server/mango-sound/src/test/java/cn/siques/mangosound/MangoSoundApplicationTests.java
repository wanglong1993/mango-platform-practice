package cn.siques.mangosound;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangosound.controller.SysSoundfileController;
import cn.siques.mangosound.entity.SysSoundfile;
import cn.siques.mangosound.mapper.SysSoundfileMapper;
import cn.siques.mangosound.service.SysSoundfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.krb5.internal.PAData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MangoSoundApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    SysSoundfileMapper sysSoundfileMapper;

    @Autowired
    SysSoundfileService sysSoundfileService;
        
    
    @Test
    void test1(){
//        List<SysSoundfile> all = sysSoundfileMapper.findAll();
//        System.out.println(all);

    }
    
    @Test
    void test(){
//        SysSoundfile build = SysSoundfile.builder().name("湖水").classification("自然").build();
//        PageRequest pageRequest = new PageRequest();
//        pageRequest.setPageNum(1);
//        pageRequest.setPageSize(10);
//
//        PageResult pageResult = sysSoundfileService.searchSoundfile(build, pageRequest);
//        System.out.println(pageResult);
    }

    @Test
    void reflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//
//        SysSoundfile build = SysSoundfile.builder().name("湖水").classification("自然").build();
//
//
//        Field[] declaredFields = build.getClass().getDeclaredFields();
//
//        HashMap<String,String> fieldMap = new HashMap<>();
//
//        for ( Field  field : declaredFields) {
//            String me = "get" + upperFirstLatter(field.getName());
//            Method method = build.getClass().getMethod(me);
//            Object invoke = method.invoke(build);
//            if(invoke!=null){
//                fieldMap.put(field.getName(),invoke.toString());
//            }
//
//        }
//
//        List<SysSoundfile> sysSoundfiles = sysSoundfileMapper.searchSoundfile(fieldMap);
//        System.out.println(sysSoundfiles);

    }


    public String upperFirstLatter(String letter){
        char[] chars = letter.toCharArray();
        if(chars[0]>='a' && chars[0]<='z'){
            chars[0] = (char) (chars[0]-32);
        }
        return new String(chars);
    }

}
