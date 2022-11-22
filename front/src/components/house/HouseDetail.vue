<template>
  <div id="houseDetail">
    <div v-if="house" class="bv-example-row">
      <b-card>
        <h3>{{ house.aptName }}</h3>
        <hr />
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
        <b-alert show variant="info"
          >건축년도 : {{ house.buildYear }} 년
        </b-alert>
        <hr />
        <b-list-group id="houselist">
          <div v-for="(deal, index) in house.deals" :key="index">
            <b-list-group-item class="mx-3"
              >거래날짜 :{{ deal.dealYear }}년 {{ deal.dealMonth }}월
              {{ deal.dealDay }}일
              <hr />
              층 : {{ deal.floor }}
              <hr />
              거래가 :
              {{
                (parseInt(deal.dealAmount.replace(",", "")) * 10000) | price
              }}원 </b-list-group-item
            ><br />
          </div>
        </b-list-group>
      </b-card>
    </div>
    <div v-else class="bv-example-row mt-3">
      <b-card>
        <b-row>
          <b-col>
            <b-alert show>선택된 주택이 없습니다.</b-alert>
          </b-col>
        </b-row>
      </b-card>
    </div>
  </div>
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

<style scoped>
#houseDetail {
  position: absolute;
  top: 11%;
  /* bottom : 10px; */
  right: 1%;
  width: 30rem;

  padding: 10px;
  z-index: 100;
  overflow-y: auto;

  /* height: 100vh; */
}

#houselist {
  max-height: 300px;
  overflow: scroll;
}
</style>
