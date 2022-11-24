package com.ssafy.vue.model.mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.CommentDto;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

public interface CommentMapper {
    public int registComment(CommentDto commentDto) throws SQLException;

    public List<BoardDto> getComment(int articleno) throws SQLException;


    public int deleteComment(int no) throws SQLException;

    public int modifyComment(CommentDto commentDto) throws SQLException;
}
