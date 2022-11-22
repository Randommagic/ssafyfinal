<template>
  <div>
    <div id="filterBox">
      <b-card bg-variant="light " border-variant="primary">
        <b-row class="mb-4 text-center">
          <b-col class="sm-3">
            <b-form-select
              v-model="sidoCode"
              size="sm"
              :options="sidos"
              @change="gugunList"
            ></b-form-select>
          </b-col>
          <b-col class="sm-3">
            <b-form-select
              v-model="gugunCode"
              size="sm"
              :options="guguns"
              @change="dongList"
            ></b-form-select>
          </b-col>
          <b-col class="sm-3">
            <b-form-select
              v-model="dongCode"
              size="sm"
              :options="dongs"
              @change="searchApt"
            ></b-form-select>
          </b-col>
        </b-row>

        <div>
          <b-tabs id="filter" pills size="sm">
            <hr />

            <b-tab title="가격" active>
              <!-- https://nightcatsama.github.io/vue-slider-component/#/  슬라이더 관련 -->
              <vue-slider
                v-model="filter.priceRange"
                :min="0"
                :max="50"
                @change="applyFilter"
                :lazy="true"
              ></vue-slider>

              {{ filter.priceRange[0] }} 억 ~
              {{ filter.priceRange[1] }} 억</b-tab
            >
            <b-tab title="날짜"
              ><label for="minDate">시작 날짜</label>
              <b-form-datepicker
                id="minDate"
                size="sm"
                v-model="filter.minDate"
                class="mb-2"
                @input="applyFilter"
              ></b-form-datepicker>

              <label for="maxDate">끝 날짜</label>
              <b-form-datepicker
                id="maxDate"
                size="sm"
                v-model="filter.maxDate"
                class="mb-2"
                @input="applyFilter"
              ></b-form-datepicker
            ></b-tab>
          </b-tabs>
          <hr />
        </div>
        <div>
          <b-button
            size="sm"
            class="mx-2"
            variant="secondary"
            @click="resetFilter"
          >
            필터 초기화</b-button
          >
        </div></b-card
      >
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      filter: {
        priceRange: [0, 50],
        minDate: "2019-01-01",
        maxDate: "2019-12-31",
      },

      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.CLEAR_DONG();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
      "CLEAR_DONG",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      const param = {
        dong: this.dongCode,
        minPriceRange: 0,
        maxPriceRange: 500000,
        minDateRange: "2019-01-01",
        maxDateRange: "2019-12-31",
      };

      if (this.dongCode) this.getHouseList(param);
    },
    applyFilter() {
      if (!this.dongCode) {
        alert("동을 먼저 선택해주세요.");
        return;
      }

      const param = {
        dong: this.dongCode,

        minPriceRange: Number(this.filter.priceRange[0]) * 10000,
        maxPriceRange: Number(this.filter.priceRange[1]) * 10000,
        minDateRange: this.filter.minDate,
        maxDateRange: this.filter.maxDate,
      };

      this.$emit("getHouseList", param);
    },

    resetFilter() {
      this.filter.priceRange = [0, 50];
      this.filter.minDate = "2019-01-01";
      this.filter.maxDate = "2019-12-31";
      this.applyFilter();
    },
  },
};
</script>

<style scoped>
#filterBox {
  position: absolute;
  top: 13%;
  /* bottom : 10px; */
  left: 1%;
  width: 23rem;
  /* height: 400px; */
  padding: 10px;
  z-index: 100;
  opacity: 95%;
  /* overflow-y: auto; */
}
</style>
