package com.april2nd.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.april2nd.happyhouse.model.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import com.april2nd.happyhouse.model.BoardParameterDto;

@Mapper
public interface NoticeMapper {

    public int writeNotice(NoticeDto noticeDto) throws SQLException;
    public List<NoticeDto> listNotice(BoardParameterDto boardParameterDto) throws SQLException;
    public int getNoticeTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
    public NoticeDto getNotice(int noticeno) throws SQLException;
    public void updateHitNotice(int noticeno) throws SQLException;
    public int modifyNotice(NoticeDto noticeDto) throws SQLException;
    public int deleteNotice(int noticeno) throws SQLException;

}