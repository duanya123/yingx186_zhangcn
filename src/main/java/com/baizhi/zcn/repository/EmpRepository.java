package com.baizhi.zcn.repository;

import com.baizhi.zcn.entity.Emp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//泛型   <操作对象类型,序列化主键的类型>
public interface EmpRepository extends ElasticsearchRepository<Emp,String> {

    List<Emp> findByName(String name);
}
