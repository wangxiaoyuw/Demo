package com.boke.dao;

import com.boke.pojo.Record;

import java.util.List;

/**
 * Created by wangzy on 2017/7/14.
 */
public interface RecordMapper {

    List<Record> findList();


    void importFile(Record record);
}
