package com.cm.zhuoyue.common.utils.baseUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author 陈萌
 * @date 2021/6/11 11:00
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {


    public static Map<String, Object> toMap(Object source) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(source);
            map = mapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static <T> List<Map<String, Object>> toMaps(Collection<T> sources) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object o : sources) {
            if (o == null) {
                continue;
            }
            list.add(toMap(o));
        }
        return list;
    }

    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * 将列表转换成另一个类型的列表，忽略类型不一致的
     *
     * @param sources 源数组
     * @param t       目标类型
     * @param <S>     class
     * @param <T>     class
     * @return 目标数组
     */
    public static <S, T> List<T> copyPropertiesToList(List<S> sources, Class<T> t) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> ts = new ArrayList<>(sources.size());
        for (S s : sources) {
            if (s == null) {
                continue;
            }
            try {
                T t1 = t.newInstance();
                copyProperties(s, t1);
                ts.add(t1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return ts;
    }
}

