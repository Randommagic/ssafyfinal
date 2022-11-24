<template>
    <div class="wrapper">
        <h6>댓글 목록</h6>
        <hr />
        <comment-write></comment-write>
        <div v-for="(comment, index) in comments" :key="index">
            <b-form-group id="fieldset-horizontal" label-cols-sm="2" label-cols-lg="3" content-cols-sm
                 :description="comment.regtime" :label="comment.writer" 
                label-for="input-horizontal">
                <div>
                    <b-form-input id="input-horizontal">
                    {{comment.content}}
                </b-form-input><b-button class="delete-btn">te</b-button>
                </div>
                
            </b-form-group>
        </div>

        <!-- <ul v-for="(comment, index) in comments" :key="index">
            <li>{{ comment.writer }}</li>
            <li>{{ comment.content }}</li>
            <li>{{ comment.regtime }}</li>
        </ul> -->
    </div>
</template>

<script>
import CommentWrite from '@/components/comment/CommentWrite';
export default {
    name: "CommentList",
    data() {
        return {
            articleno: this.article.articleno,
            // comments: [],
            commentList: [

            ],
        }
    },
    components: {
        CommentWrite,
    },
    props: {
        comments: Object
    },
}
</script>

<style>
.wrapper {
    width: 100%;
    text-align: left;
}

.wrapper hr {
    width: 100%;
}

.wrapper .delete-btn {
    text-align: right;
}
</style>