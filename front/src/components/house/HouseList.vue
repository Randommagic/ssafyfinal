<template>
  <div>
    <b-button v-b-toggle.sidebar-1>필터 열기</b-button>
    <b-sidebar id="sidebar-1" title="검색 필터" shadow>
      <div class="px-3 py-2">
        <p>최소 가격</p>
        <b-form-input
          id="minRange"
          v-model="filter.minRange"
          type="range"
          min="0"
          :max="filter.maxRange"
          step="1"
        ></b-form-input>
        <p>최대 가격</p>
        <b-form-input
          id="maxRange"
          v-model="filter.maxRange"
          type="range"
          :min="filter.minRange"
          max="50"
          step="1"
        ></b-form-input>
        {{ filter.minRange }} 억 ~ {{ filter.maxRange }} 억 <br />
        <hr />
        <p>시작 날짜</p>
        <b-form-datepicker
          id="minDate"
          v-model="filter.minDate"
          class="mb-2"
        ></b-form-datepicker>
        <br />

        <p>끝 날짜</p>
        <b-form-datepicker
          id="maxDate"
          v-model="filter.maxDate"
          class="mb-2"
        ></b-form-datepicker>

        {{ filter.minDate }} ~ {{ filter.maxDate }} <br />
        <hr />
      </div>
      <div class="px-3 py-2">
        <b-button class="mx-2" variant="secondary" @click="resetFilter">
          필터 초기화</b-button
        >
        <b-button class="mx-2" variant="success" @click="applyFilter">
          필터 설정</b-button
        >
      </div>
    </b-sidebar>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      filter: {
        minRange: "0",
        maxRange: "50",
        minDate: "2019-01-01",
        maxDate: "2019-12-31",
      },

      map: null,
      latitude: 37.501273,
      longitude: 127.039675,
      infos: [],
      markers: [],
      houseNum: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a55445555a66d8b5e8ad74af0600510c&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, ["detailHouseByNumber", "getHouseList"]),
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
          level: 4, // 지도의 확대 레벨
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    moveMapToMarker() {
      // 마커들의 중심값을 구한 후 그곳으로 지도의 중심점 변경
      if (this.infos.length != 0) {
        let lat = 0;
        let lng = 0;
        for (let i = 0; i < this.infos.length; i++) {
          lat += Number(this.infos[i].lat);
          lng += Number(this.infos[i].lng);
        }
        lat /= this.infos.length;
        lng /= this.infos.length;
        console.log(lat, lng);
        this.map.setCenter(new kakao.maps.LatLng(lat, lng));
      }
    },

    displayMarker() {
      this.removeMarker();
      for (let i = 0; i < this.infos.length; i++) {
        if (this.infos[i].deals.length == 0) continue;
        let marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(this.infos[i].lat, this.infos[i].lng),
          clickable: true,
          title: i + "",
        });
        marker.setMap(this.map);

        // let infowindow = new kakao.maps.InfoWindow({
        //   content: this.infos[i].aptName,
        //   //removable: true,
        // });

        kakao.maps.event.addListener(marker, "click", () => {
          var no = Number(marker.getTitle());
          this.map.panTo(
            new kakao.maps.LatLng(this.infos[no].lat, this.infos[no].lng)
          );
          this.detailHouseByNumber(no);
        });

        // kakao.maps.event.addListener(
        //   marker,
        //   "mouseover",
        //   this.makeOverListener(this.map, marker, infowindow)
        // );
        // kakao.maps.event.addListener(
        //   marker,
        //   "mouseout",
        //   this.makeOutListener(infowindow)
        // );
        this.markers.push(marker);
      }
      this.moveMapToMarker();
    },

    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    applyFilter() {
      if (!this.dong) {
        alert("동을 먼저 선택해주세요.");
        return;
      }

      const param = {
        dong: this.dong,
        minPriceRange: Number(this.filter.minRange) * 10000,
        maxPriceRange: Number(this.filter.maxRange) * 10000,
        minDateRange: this.filter.minDate,
        maxDateRange: this.filter.maxDate,
      };

      this.getHouseList(param);
    },
    resetFilter() {
      this.filter.minRange = 0;
      this.filter.maxRange = 50;
      this.filter.minDate = "2019-01-01";
      this.filter.maxDate = "2019-12-31";
      this.applyFilter();
    },
  },
  computed: {
    ...mapState(houseStore, ["houses", "dong"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  watch: {
    houses: function (val) {
      this.infos = val;
      this.displayMarker();
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 500px;
}
</style>
