package com.yibin.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yibin.pojo.Brand;
import com.yibin.pojo.PageBean;
import com.yibin.service.BrandService;
import com.yibin.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = req.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.add(brand);
        resp.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        int id = JSON.parseObject(params, int.class);
        brandService.deleteById(id);
        resp.getWriter().write("success");
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        int id = JSON.parseObject(params, int.class);
        Brand brand = brandService.selectById(id);
        String jsonString = JSON.toJSONString(brand);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = req.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.update(brand);
        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        brandService.deleteByIds(ids);
        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        InputStream inputStream = req.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String params = br.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        PageBean<Brand> pageBean =
                brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
