package com.perm.mapper;

import java.util.List;

import com.perm.model.Area;

/**
 * Created by Administrator on 2018/10/1.
 */
public interface AreaMapper {

    List<Area> findTest();

    List<Area> query(Area area);

    void add(Area area);

    void del(Integer id);

    Area get(Integer id);

    void update(Area area);
}
