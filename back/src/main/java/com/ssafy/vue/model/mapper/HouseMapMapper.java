package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.DealInfoDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
	HouseInfoDto getOneApt(String aptCode) throws SQLException;
	
	List<DealInfoDto> getAptFilteredDeals(String apt , String minPriceRange, String maxPriceRange,String minDateRange, String maxDateRange) throws SQLException;
	List<DealInfoDto> getAptDeals(String apt) throws SQLException;
	List<Map<String,String>> searchByDongName(String keyword) throws SQLException;
	List<Map<String,String>> searchByAptName(String keyword) throws SQLException;
	
	
}
