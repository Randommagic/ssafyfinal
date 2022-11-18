<template>
  <div>
    <div id="map" style="height:500px;"></div>
  </div>
</template>

<script>

import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {

  },
  data() {
    return {
      map: null,
      latitude: 37.501273,
      longitude: 127.039675,
      infos: [],
      markers: [],
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
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
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
      if (this.infos.length != 0) {
        console.log(this.infos[0].lat, this.infos[0].lng);
        this.map.setCenter(new kakao.maps.LatLng(this.infos[0].lat, this.infos[0].lng));
      }
    },


    displayMarker() {

      this.removeMarker();
      for (var i = 0; i < this.infos.length; i++) {
        console.log(this.infos[i].lat, this.infos[i].lng)
        var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(this.infos[i].lat, this.infos[i].lng), // 마커를 표시할 위치
        });
        marker.setMap(this.map);
        this.markers.push(marker);
      }
      this.moveMapToMarker();
    },

  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  watch: {
    houses: function (val) {
      this.infos = val;
      this.displayMarker();
    }
  }
};
</script>

<style>

</style>
