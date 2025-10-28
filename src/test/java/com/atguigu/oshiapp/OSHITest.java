package com.atguigu.oshiapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;
import java.util.List;

public class OSHITest {

    SystemInfo si = new SystemInfo();

    @Test
    @SneakyThrows
    void testCpu(){
        HardwareAbstractionLayer hardware = si.getHardware();
        CentralProcessor processor = hardware.getProcessor();
//        2.获取cpu最大频率
        long maxFreq =processor.getMaxFreq();
        System.out.println("最大频率 = "+maxFreq);

//        3.获取cpu当前频率.返回所有逻辑cpu当前频率
        long[] currentFreq =processor.getCurrentFreq();
        System.out.println("当前频率 = "+ Arrays.toString(currentFreq));

//        4.当前频率/最大频率=当前cpu占用率

//        5.获取cpu 1s 的使用情况
        double[] cpuLoad =processor.getProcessorCpuLoad(1000);
        System.out.println("cpuLoad = "+Arrays.toString(cpuLoad));
    }




    @SneakyThrows
    @Test
    void testMemory(){
        HardwareAbstractionLayer hardware = si.getHardware();

        GlobalMemory memory = hardware.getMemory();
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(memory);

        System.out.println("string ="+string);
    }

    @SneakyThrows
    @Test
    void test01(){
//        1.创建si对象


//        2.获取操作系统对象
        OperatingSystem os = si.getOperatingSystem();


        ObjectMapper mapper =new ObjectMapper();

        List<OSProcess> processes = os.getProcesses();
        System.out.println("进程总数："+os.getProcessCount());
        for (OSProcess process : processes) {
            String json =mapper.writerWithDefaultPrettyPrinter().writeValueAsString(process);
            System.out.println(json);
        }
    }

}
