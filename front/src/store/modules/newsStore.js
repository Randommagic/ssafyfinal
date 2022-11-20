import { newsList } from "@/api/news.js";
const newsStore = {
    namespaced: true,
    state: {
        newsList: [],
    },
    getters: {},
    mutations: {
        SET_NEWS_LIST(state, titles) {
            console.log(titles);
            titles.forEach((title) => {
                console.log(title);
                state.titles.push(title);
            });
        }
    },
    actions: {
        getNews: ({ commit }, query) => {
            const params = { "query": query, };
            console.log(params);
            newsList(
                params,
                ({ data }) => {
                    commit("SET_NEWS_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        }
    },
};

export default newsStore;
