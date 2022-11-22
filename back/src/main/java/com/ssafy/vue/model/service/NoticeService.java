package com.april2nd.happyhouse.model.service;

import java.util.List;

import com.april2nd.happyhouse.model.NoticeDto;
import com.april2nd.util.PageNavigation;
import com.april2nd.happyhouse.model.BoardParameterDto;
import org.springframework.stereotype.Service;

public interface NoticeService {
    public boolean writeNotice(NoticeDto noticeDto) throws Exception;
    public List<NoticeDto> listNotice(BoardParameterDto boardParameterDto) throws Exception;
    public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;

    public NoticeDto getNotice(int noticeno) throws Exception;
    public void updateHitNotice(int noticeno) throws Exception;
    public boolean modifyNotice(NoticeDto NoticeDto) throws Exception;
    public boolean deleteNotice(int noticeno) throws Exception;
}
