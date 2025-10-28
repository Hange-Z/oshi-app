<template>
  <a-space wrap>
    <!-- 16个 叫 cpu-[1~16] -->
    <div
      :id="`cpu-${i}`"
      style="width: 180px; height: 180px; border: 1px solid black"
      v-for="i in 16"
    ></div>
  </a-space>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted, ref } from "vue";
import {getCpuLoadApi} from "@/api/cpuloadApi"

//保存所有初始化的图表
const chartDom = ref([]);

onMounted(() => {
  //页面加载出来，有div dom元素才可以
  // drawCpuLoad();
  //初始化图表只需要进行一次
  initChart();
  //以后每次画图
  getCpuData();
});

const initChart = ()=>{
    for(let i=1;i<=16;i++){
        var dom = document.getElementById("cpu-"+i);
        //得到一个chart对象
        var myChart = echarts.init(dom);
        chartDom.value.push(myChart)
    }
}

// 16核 cpu 的所有数据。
// 每一核还是一个数组，这个数组中保存的是每秒的数据
const cpuAllData = ref([])


const getCpuData = async ()=>{
    
    //1、拿到服务器真正的响应； 给服务器发送请求获取
    let resp = await getCpuLoadApi();
    //2、返回的是16核cpu。每个核心当前的负载值
    let data = resp.data;
    for(let i =0;i<16;i++){
        //把当前请求到的这个cpu的使用率放进自己的数组中。第一次要初始化数组
        if(!cpuAllData.value[i]){
            cpuAllData.value[i] = [];
        }


        if(cpuAllData.value[i].length > 60){
            //把最老的一个数据删除，放入最新获取的这个数据
            let arr = cpuAllData.value[i].slice(-60); //移除了最前一个元素的数组
            arr.push(data[i])
            cpuAllData.value[i] = arr;
        }else{
            //每次给里面放之前最数组的长度进行一个判断
            cpuAllData.value[i].push(data[i])
        }


        //这里会 OOM；这个数组最多放 60个？ 超过 60个删除最老的

        drawCpuLoad(i+1,cpuAllData.value[i])
    }

    await getCpuData();
   
}

//1、每个图显示CPU名
//2、每个图xy轴不显示
//3、显示为面积图
const drawCpuLoad = (cpuIndex,cpuData) => {
  //得到一个chart对象
  var myChart = chartDom.value[cpuIndex-1]
  var option;

//   textStyle. fontSize

  option = {
    title: {text: 'CPU'+cpuIndex, textStyle: {fontSize: 14}},
    grid: {left: "0", right: "0", bottom: "5", top: "0"},
    xAxis: {
      show: false,
      type: "category"
    },
    yAxis: {
      show: false,
      type: "value",
      min: 0,
      max: 1
    },
    series: [
      {
        data: cpuData,
        type: "line",
        symbol: "none",
        smooth: true,
        areaStyle: {}
      },
    ],
  };

  myChart.setOption(option);
};
</script>

<style scoped></style>
