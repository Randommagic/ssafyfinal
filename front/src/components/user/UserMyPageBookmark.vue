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
        <h3> 북마크 리스트 </h3>
        <b-input-group v-for="(apt, index) in userInfo.aptBookmark" :key="index" prepend="아파트 명" class="mt-3">
            <b-form-input v-model="apt.aptCode" readonly></b-form-input>
            <b-input-group-append>
                <router-link :to="{ name: 'house', query: { aptCode: apt.aptCode } }">
                    <b-button variant="primary">지도</b-button>
                </router-link>
                <b-button variant="danger" @click="removeBookmark(apt.aptCode)">삭제</b-button>
            </b-input-group-append>
        </b-input-group>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
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
    methods: {
        ...mapActions(memberStore, ["deleteBookmark"]),
        removeBookmark(aptCode) {
            var really = confirm("삭제하시겠습니까?");
            if (!really) return;
            var param = {
                aptCode: aptCode,
                userId: this.userInfo.userid,
            };
            this.deleteBookmark(param);
        },
    },
    watch: {
        userInfo: function (val) {
            this.userInfo = val;
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