<template>
  <div id="houseDetail">
    <div v-if="house" class="bv-example-row">
      <b-card>
        <h3>{{ house.aptName }}</h3>
        <b-button
          v-if="this.userInfo"
          pill
          variant="info"
          @click="bookMark(house.aptCode)"
        >
          찜하기</b-button
        >
        <hr />
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
        <b-alert show variant="info"
          >건축년도 : {{ house.buildYear }} 년
        </b-alert>
        <hr />
        <b-tabs id="houseinfo" pills size="sm">
          <b-tab title="거래정보">
            <div>
              총 {{ house.deals.length }} 개의 결과 중 필터링 된
              {{ house.filteredDeals.length }} 개의 결과들입니다.
            </div>
            <b-list-group id="houselist">
              <div v-for="(deal, index) in house.filteredDeals" :key="index">
                <b-list-group-item class="mx-3"
                  >거래날짜 :{{ deal.dealYear }}년 {{ deal.dealMonth }}월
                  {{ deal.dealDay }}일
                  <br />
                  평수:{{ deal.area }}
                  <hr />
                  층 : {{ deal.floor }}
                  <hr />
                  거래가 :
                  {{
                    (parseInt(deal.dealAmount.replace(",", "")) * 10000)
                      | price
                  }}원 </b-list-group-item
                ><br />
              </div>
            </b-list-group>
          </b-tab>
          <b-tab title="거래정보그래프"> </b-tab>
        </b-tabs>
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
const memberStore = "memberStore";
export default {
  name: "HouseDetail",
  components: {},
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    bookmark(aptCode) {
      for (var i = 0; i < this.userInfo.aptBookmark.length; i++) {
        if (aptCode === this.userInfo.aptBookmark[i].aptCode) {
          alert("이미 찜한 아파트입니다!");
          break;
        }
      }
    },
  },
  mounted() {},
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
