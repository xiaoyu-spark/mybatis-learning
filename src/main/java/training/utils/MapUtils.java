package training.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MapUtils {
    public static void addMap(Map<String,HashSet<String>> collect, String key, String value){
        if(collect==null)return;
        if(key==null||value==null)return;
        if(collect.containsKey(key)){
            collect.get(key).add(value);
        }else {
            HashSet<String> newList = new HashSet<>();
            newList.add(value);
            collect.put(key,newList);
        }
    }

    public static void addMap(Map<String, HashSet<String>> collect, String key, HashSet<String> lists){
        if(collect==null)return;
        if(key==null||lists==null)return;
        for(String value:lists){
            addMap(collect,key,value);
        }
    }
}
