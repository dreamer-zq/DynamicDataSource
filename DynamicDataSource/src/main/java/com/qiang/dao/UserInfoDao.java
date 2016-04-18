package com.qiang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qiang.anno.DataSourceEnum;
import com.qiang.anno.DateSource;
import com.qiang.mapper.UserInfoMapper;
import com.qiang.model.UserInfo;

@Repository
public class UserInfoDao {

	@Autowired
	private SqlSession sqlSession;

	@DateSource(DataSourceEnum.MASTER)
	public int insert(UserInfo record) {
		return sqlSession.getMapper(UserInfoMapper.class).insert(record);
	}

	@DateSource(DataSourceEnum.SLAVE)
	public List<UserInfo> findAll() {
		return sqlSession.getMapper(UserInfoMapper.class).select(null);
	}
}
