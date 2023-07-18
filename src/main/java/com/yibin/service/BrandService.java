package com.yibin.service;

import com.yibin.pojo.Brand;
import com.yibin.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();

    void add(Brand brand);

    void deleteById(int id);

    Brand selectById(int id);

    void update(Brand brand);

    void deleteByIds(int[] ids);

    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
