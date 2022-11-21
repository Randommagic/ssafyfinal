import { sidoList, gugunList, houseList, dongList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    dong: null,
    houses: [],
    house: null,
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG(state) {
      state.dong = null;
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },

    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_DONG(state, dong) {
      state.dong = dong;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_DETAIL_HOUSE_BYNUMBER(state, num) {
      state.house = state.houses[num];
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, param) => {
      commit("SET_DONG", param.dong);
      commit("CLEAR_APT_LIST");
      console.log(param.minDateRange, param.maxDateRange);
      const params = {
        dong: param.dong,
        minPriceRange: param.minPriceRange,
        maxPriceRange: param.maxPriceRange,
        minDateRange: param.minDateRange,
        maxDateRange: param.maxDateRange,
      };
      houseList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    detailHouseByNumber: ({ commit }, num) => {
      commit("SET_DETAIL_HOUSE_BYNUMBER", num);
    },
  },
};

export default houseStore;
