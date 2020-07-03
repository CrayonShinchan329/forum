package com.lxinet.jeesns.core.utils;

import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/12/26.
 */
public class ErrorUtil {
    private static Map<Integer,String> errors = new HashMap<>();
    static {
        errors.put(-1000,"系统异常");
        errors.put(-1001,"没有权限");
        errors.put(-1002,"社团不存在");
        errors.put(-1003,"先关注社团才能发布活动");
        errors.put(-1004,"活动不存在");
        errors.put(-1005,"会员不存在");
        errors.put(-1006,"社团已关闭发布活动功能");
        errors.put(-1007,"问答不存在");
        errors.put(-1008,"请先登录");
        errors.put(-1009,"社团信息不存在");
        errors.put(-1010,"相册不存在");
        errors.put(-1011,"图片不存在");
        errors.put(-1012,"无权查看此相册");
    }

    public static String error(Model model, Integer errorcode, String ftlPath){
        model.addAttribute("msg",errors.get(errorcode));
        return ftlPath;
    }
}
