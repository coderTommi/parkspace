package com.parkspace.db.rmdb.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.User;

@MapperScan
public interface UserDao {
	//此处的方法名必须和mapper中的映射文件中的id同名
	//回去映射文件中通过com.hua.saf.dao.UserDao.getUser,即this.getClass().getName()+".getUser"
	public User getUser(int id);
}