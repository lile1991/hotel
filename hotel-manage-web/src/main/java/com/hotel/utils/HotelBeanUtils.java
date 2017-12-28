package com.hotel.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HotelBeanUtils {
    /**
     * 转换对象列表
     * @param source 源对象数组
     * @param targetClass 目标类型
     * @param <S> 源类型
     * @param <T> 目标类型
     * @return 目标对象列表
     */
    public static <S, T> List<T> convert(List<S> source, Class<T> targetClass) {
        if(source == null) {
            return null;
        }
        if(source.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return source.stream().map(s -> convert(s, targetClass)).collect(Collectors.toList());
    }

    /**
     * 转换单个对象
     * @param source 源对象
     * @param targetClass 目标类型
     * @param <S> 源类型
     * @param <T> 目标类型
     * @return 目标对象
     */
    public static <S, T> T convert(S source, Class<T> targetClass) {
        if(source == null) {
            return null;
        }
        T target = newInstance(targetClass);

        return copyProperties(source, target);
    }

    /**
     * 复制属性， 采用Spring BeanUtils
     * @param source 源对象
     * @param target 目标对象
     * @param <S> 源类型
     * @param <T> 目标类型
     * @return 目标对象
     */
    public static <S, T> T copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 根据Class信息创建实例
     * @param type Class类型信息
     * @param <T> 类型
     * @return 实例
     * @throws RuntimeException 构造器调用失败
     */
    public static <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <S, T> Page<T> convert(Page<S> page, Class<T> type) {
        return new PageImpl<>(HotelBeanUtils.convert(page.getContent(), type), new PageRequest(page.getNumber() + 1, page.getSize()), page.getTotalPages());
    }
}
