package com.atguigu.oshiapp.controller;


import com.atguigu.oshiapp.common.R;
import com.atguigu.oshiapp.service.CpuLoadMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin//前后端跨域请求
@RestController//标记这是一个接口返回器，所有返回值自动转化为json格式给前端
@RequestMapping("/metrics")//统一管理接口前缀
public class CpuLoadRestController {


    @Autowired
    CpuLoadMetricsService  cpuLoadMetricsService;

    @GetMapping("/cpuload")
    public R getCpuLoad() {

        double[] cpuLoad = cpuLoadMetricsService.getCpuLoad();

        return R.ok (cpuLoad);
    }
}
