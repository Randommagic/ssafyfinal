<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col>
        <h3>{{ house.aptName }}</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.aptCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.dongCode }} </b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <!-- <b-alert show variant="danger">거래금액 : {{ (parseInt(house.거래금액.replace(",", "")) * 10000) | price }}원</b-alert> -->
      </b-col>
    </b-row>
    <b-list-group
      ><div v-for="(deal, index) in house.deals" :key="index">
        <b-list-group-item
          >거래날짜 :{{ deal.dealYear }}년 {{ deal.dealMonth }}월
          {{ deal.dealDay }}일
          <hr />
          층 : {{ deal.floor }}
          <hr />
          거래가 :
          {{
            (parseInt(deal.dealAmount.replace(",", "")) * 10000) | price
          }}원 </b-list-group-item
        ><br /></div
    ></b-list-group>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show>선택된 주택이 없습니다.</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
