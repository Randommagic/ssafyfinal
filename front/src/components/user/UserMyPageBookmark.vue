<template>
    <b-container class="bv-example-row">
    <div class="bookmark">
      <b-col cols="9" class="bg-light p-2 pl-5 text-left">
        <span :class="{ completed: apt.aptCOde }" >{{ apt.aptCode }}</span>
      </b-col>
      <b-button-group>
      <b-button variant="danger" @click="removeApt">지도</b-button>
      <b-button variant="danger"  @click="removeApt">삭제</b-button>
    </b-button-group>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "UserMyPageBookMark",
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    props: {
        apt : Object,
    },
    methods: {
        removeApt() {
            // memberStore에 deleteApt 구현.
            // this.deleteApt(this.apt);
            alert("deleteAPT 만들어 !");
        },
    }
}
</script>

<style>
.bookmark{
    display:flex;
    margin: 10px;
}
.bookmark > button {
    width:100px;
    margin-left:15px;
    font-size: 0.5rem;
}

</style>