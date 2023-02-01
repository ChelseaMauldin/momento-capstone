import axios from 'axios';

export default {

    displayPost() {
        return axios.get('/posts')
    },

    displayCommentsByPost(post_id){
        return axios.get(`/post/${post_id}/comments`)
    }



}