package com.baizhi.zcn.controller;

import com.baizhi.zcn.entity.City;
import com.baizhi.zcn.entity.Mondel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;
import sun.security.jgss.HttpCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("echarts")
public class EchartsController {

    @RequestMapping("queryUserNum")
    public HashMap<String, Object> queryUserNum(){

        //select concat(month(create_date),'月'),count(id) from yx_user where sex='女'
        //group by month(create_date)

        HashMap<String, Object> map = new HashMap<>();

        //根据月份 性别 查询数量
        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
        map.put("boys", Arrays.asList(100,200,500,700,200,600));
        map.put("girls", Arrays.asList(150,234,572,892,457,140));

        return map;
    }

    //http  由页面发起  短链接
    //tcp/ip 长连接
    /*
    *[
  {
    "title": "小男孩",
    "citys": [
      {"name": "北京","value": "301"},
      {"name": "天津","value": "202"},
      {"name": "上海","value": "302"},
      {"name": "重庆","value": "304"},
      {"name": "河北","value": "503"},
      {"name": "安徽","value":"603"},
      {"name": "香港","value": "503"},
      {"name": "澳门","value": "307"}
    ]
  },{
    "title": "小姑娘",
    "citys": [
      {"name": "北京","value": "310"},
      {"name": "天津","value": "220"},
      {"name": "湖北","value": "320"},
      {"name": "河南","value": "340"},
      {"name": "山东","value": "530"},
      {"name": "山西","value":"630"},
      {"name": "黑龙江","value": "530"},
      {"name": "吉林","value": "370"}
    ]
  }
]
    * */
    @RequestMapping("queryUserMap")
    public ArrayList<Object> queryUserMap(){

        //select city name,count(id) value from yx_user where sex='女' GROUP BY city

        ArrayList<Object> list = new ArrayList<>();

        //根据性别分组查询  where=男  group by="cityName"
        ArrayList<City> boysCities = new ArrayList<>();
        boysCities.add(new City("北京","500"));
        boysCities.add(new City("河南","800"));
        boysCities.add(new City("湖南","300"));
        boysCities.add(new City("湖北","600"));
        boysCities.add(new City("山东","100"));

        Mondel boyMondel = new Mondel("小男孩", boysCities);

        ArrayList<City> girlsCities = new ArrayList<>();
        girlsCities.add(new City("黑龙江","400"));
        girlsCities.add(new City("吉林","800"));
        girlsCities.add(new City("山西","700"));
        girlsCities.add(new City("重庆","600"));
        girlsCities.add(new City("海南","500"));

        Mondel girlMondel = new Mondel("小姑娘", girlsCities);

        list.add(boyMondel);
        list.add(girlMondel);

        return list;
    }

}
