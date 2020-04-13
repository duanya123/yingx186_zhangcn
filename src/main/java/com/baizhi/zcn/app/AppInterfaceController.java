package com.baizhi.zcn.app;

import com.baizhi.zcn.common.CommonResult;
import com.baizhi.zcn.service.VideoService;
import com.baizhi.zcn.util.AliyunSendPhoneUtil;
import com.baizhi.zcn.vo.VideoVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8989", maxAge = 3600,methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("app")
public class AppInterfaceController {

    @Resource
    VideoService videoService;

    /*发送验证码的接口*/
    @RequestMapping("getPhoneCode")
    public CommonResult getPhoneCodes(String phone){
        //生成随机数
        String random = AliyunSendPhoneUtil.getRandom(6);
        System.out.println("存储验证码："+random);
        //发送验证码
        String message = AliyunSendPhoneUtil.sendCode(phone, random);
        System.out.println("验证码发送："+message);
        if (message.equals("发送成功")){
            return new CommonResult().success("100","发送成功",phone);
        }else{
            return new CommonResult().failed("发送失败:"+message,null);
        }
    }

    /*
    * 首页查询视频信息接口
    * */
    @CrossOrigin(origins="http://localhost:8989")
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            //查询数据
            List<VideoVo> videoVos = videoService.queryByReleaseTime();
            return new CommonResult().success(videoVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed();
        }
    }

}
