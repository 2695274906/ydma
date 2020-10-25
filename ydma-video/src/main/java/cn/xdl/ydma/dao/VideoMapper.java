package cn.xdl.ydma.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import cn.xdl.ydma.entity.Video;
//JpaRepository的两个参数，第一个参数表示实体类，第二个参数表示实体类的id的类型

public interface VideoMapper extends JpaRepository<Video, Integer> {
	//1.通过id查找Video信息 jpa的扩展操作
	//@Query("from video where id = :nn") 
	public Video findVideoById(int id);
	
	
}
