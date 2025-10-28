package com.atguigu.oshiapp.common;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data//对接前端的R，不用管泛型，对接后端的需要。因为前端依赖json这种弱类型格式，后端依赖java的强类型特性。
public class R {

    private Integer code;//状态
    private String msg;//提示文字
    private Object data;//数据

    public R (Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R (Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R ok (){
        return new R(200,"ok");
    }

    public static R ok(Object data){
        return new R(200,"ok",data);
    }

    public static R error (){
        return new R(500,"error");
    }

    public static R error (String msg){
        return new R(500,msg);
    }

    public static R error (Object data){
        return new R(500,"error",data);
    }

    public static R error (int code,String msg){
        return new R(code,msg);
    }
}
