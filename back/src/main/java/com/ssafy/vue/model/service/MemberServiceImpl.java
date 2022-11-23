package com.ssafy.vue.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		MemberDto user = sqlSession.getMapper(MemberMapper.class).userInfo(userid);
		// 아파트 찜 목록도 같이 받아오기.
		user.setAptBookmark(sqlSession.getMapper(MemberMapper.class).getBookmarkApt(userid));
		return user;
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}
	@Override
	public boolean joinUser(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).registUser(memberDto) == 1;
	}

	@Override
	public boolean deleteUser(String userid) throws Exception {
		if(userid == "" || userid == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).removeUser(userid) == 1;
	}

	@Override
	public boolean newBookmark(String userId, String aptCode) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).newBookmark(userId, aptCode) == 1;
	}
}
