<template>
    <!-- <b-container class="bv-example-row">
            <b-button-group fluid>
            <b-col cols="9" class="bg-light p-2 pl-5 text-left">
                <span :class="{ completed: apt.aptCOde }">{{ apt.aptCode }}</span>
            </b-col>
                <b-button variant="primary" @click="removeApt">지도</b-button>
                <b-button variant="danger" @click="removeApt">삭제</b-button>
            </b-button-group>
    </b-container> -->
    <div>
        <b-input-group prepend="아파트 명" class="mt-3">
            <b-form-input v-model="aptInfo.aptCode" readonly></b-form-input>
            <b-input-group-append>
                <b-button variant="primary" @click="removeApt">지도</b-button>
                <b-button variant="danger" @click="removeApt">삭제</b-button>
            </b-input-group-append>
        </b-input-group>
    </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "UserMyPageBookMark",
    data() {
            return {
                aptInfo: null,
            }
        },
    created() {
        this.aptInfo = this.apt;
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    props: {
        apt: Object,
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
.bookmark {
    display: contents;
    margin: 10px;
}

/* 
.bookmark>button {
    width: 100px;
    margin-left: 15px;
    font-size: 0.5rem;
} */
</style>