package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BookmarkDto;
import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;

	public MemberDto userInfo(String userid) throws SQLException;

	public void saveRefreshToken(Map<String, String> map) throws SQLException;

	public Object getRefreshToken(String userid) throws SQLException;

	public void deleteRefreshToken(Map<String, String> map) throws SQLException;

	public int registUser(MemberDto memberDto) throws SQLException;

	public int removeUser(String userid) throws SQLException;

	public List<BookmarkDto> getBookmarkApt(String userid) throws SQLException;

	public int newBookmark(String userid, String aptCode) throws SQLException;
}
