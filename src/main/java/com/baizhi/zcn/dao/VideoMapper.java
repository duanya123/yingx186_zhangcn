package com.baizhi.zcn.dao;

import com.baizhi.zcn.entity.Video;
import com.baizhi.zcn.po.VideoPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video> {

    List<VideoPo>  queryByReleaseTime();

}