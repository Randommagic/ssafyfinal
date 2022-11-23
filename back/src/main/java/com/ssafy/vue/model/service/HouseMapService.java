package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.DealInfoDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SearchResultDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong, String minPriceRange, String maxPriceRange, String minDateRange,
			String maxDateRange) throws Exception;

	List<HouseInfoDto> getOneApt(String aptCode, String minPriceRange, String maxPriceRange, String minDateRange,
			String maxDateRange) throws Exception;

//	List<DealInfoDto> getAptDeals(String apt, String minPriceRange, String maxPriceRange) throws Exception;

	SearchResultDto getSearchByKeyword(String keyword) throws Exception;

}
