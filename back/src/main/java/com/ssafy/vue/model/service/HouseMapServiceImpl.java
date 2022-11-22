package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.DealInfoDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SearchResultDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong, String minPriceRange, String maxPriceRange, String minDateRange,
			String maxDateRange) throws Exception {
		List<HouseInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
		for (int i = 0; i < list.size(); i++) {
			List<DealInfoDto> filteredDealList = sqlSession.getMapper(HouseMapMapper.class).getAptFilteredDeals(
					list.get(i).getAptCode(), minPriceRange, maxPriceRange, minDateRange, maxDateRange);
			List<DealInfoDto> dealList = sqlSession.getMapper(HouseMapMapper.class)
					.getAptDeals(list.get(i).getAptCode());
			list.get(i).setDeals(dealList);
			list.get(i).setFilteredDeals(filteredDealList);
		}
		return list;
	}

	@Override
	public SearchResultDto getSearchByKeyword(String keyword) throws Exception {

		SearchResultDto result = new SearchResultDto();
		result.setAptResult(sqlSession.getMapper(HouseMapMapper.class).searchByAptName(keyword));
		result.setDongResult(sqlSession.getMapper(HouseMapMapper.class).searchByDongName(keyword));

		return result;
	}

//	@Override
//	public List<DealInfoDto> getAptDeals(String apt, String minPriceRange, String maxPriceRange) throws Exception {
//		return sqlSession.getMapper(HouseMapMapper.class).getAptDeals(apt, minPriceRange, maxPriceRange);
//	}

}
