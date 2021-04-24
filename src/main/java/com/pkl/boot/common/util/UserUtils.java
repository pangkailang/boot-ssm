package com.pkl.boot.common.util;

import com.pkl.boot.entity.TableCondition;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    public static List<TableCondition> getTableCondition(String name){
        TableCondition tableCondition = new TableCondition();
        List<TableCondition> list =new ArrayList<TableCondition>();
        list.add(tableCondition);
        return list;
    };
}
