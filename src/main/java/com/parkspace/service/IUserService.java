package com.parkspace.service;

import com.parkspace.db.rmdb.entity.BaseUser;

public interface IUserService {
	public BaseUser getUser(int id);
}
