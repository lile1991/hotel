package com.hotel.room.enums;

import com.hotel.enums.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnumListConstant {

    private static final Map<Class<? extends EnumInterface>, List<EnumEntry>> cache = new ConcurrentHashMap<>(64);
    static {
        cache.put(CheckStateEnum.class, toList(CheckStateEnum.values()));
        cache.put(RoomStateEnum.class, toList(RoomStateEnum.values()));
    }

    private static List<EnumEntry> toList(EnumInterface[] values) {
        List<EnumEntry> list = new ArrayList<>();
        for(EnumInterface value: values) {
            list.add(new EnumEntry(value.name(), value.getDesc()));
        }
        return list;
    }

    public static List<EnumEntry> getEnumEntryList(Class<? extends EnumInterface> key) {
        return cache.get(key);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnumEntry implements Serializable {
        private String name;
        private String desc;
    }
}
