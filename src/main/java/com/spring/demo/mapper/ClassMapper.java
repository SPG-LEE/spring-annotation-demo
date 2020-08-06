package com.spring.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface ClassMapper {
    @Update("update classes set mark = mark + #{count} where id = #{id}")
    public  int addMark(@Param("id") long id, @Param("count") long count);
}
