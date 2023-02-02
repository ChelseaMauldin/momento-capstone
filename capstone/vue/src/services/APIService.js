import axios from 'axios';

export default {

    displayPosts() {
        return axios.get('/posts')
    },

    displayPost(post_id) {
        return axios.get(`/posts/${post_id}`)
    },

    displayCommentsByPost(post_id) {
        return axios.get(`/post/${post_id}/comments`)
    },

    like(post_id) {
        return axios.put(`/post/${post_id}/like`)
    },

    unlike(post_id) {
        return axios.put(`/post/${post_id}/unlike`)
    },

    displayProfile(username) {
        return axios.get(`/profiles/${username}`)
    },

    displayPostsByUsername(username) {
        return axios.get(`profiles/${username}/posts`)
    }

}