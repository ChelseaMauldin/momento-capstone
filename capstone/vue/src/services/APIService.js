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

    createNewComment(comment) {
        return axios.post('/post/create-comment', comment)
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
    },

    displayFavoritePosts(username) {
        return axios.get(`/favorites/${username}`)
    },

    displayFavoriteIds(username) {
        return axios.get(`/favorites/${username}/ids`)
    },

    addFavoritePost(favorite) {
        return axios.post('/favorites/add', favorite)
    },

    deleteFavorite(id) {
        return axios.delete(`/favorites/${id}`)
    },

    createPost(post) {
        return axios.post('/posts/create', post)
    },
    updateProfile(profile) {
        return axios.put('/profiles/update', profile)
    },
    getAllRatings(){
        return axios.get('/ratings')
    },
    getRatingsById(id){
        return axios.get(`/ratings/${id}`)
    },
    addRating(rating) {
        return axios.post('/ratings', rating)
    },
    updateRating(rating){
        return axios.put('/ratings', rating)
    },
    getRatingByUser(id, user){
        return axios.get(`/ratings/${id}/${user}`)
    },
    getAvgRating(id){
        return axios.get(`/ratings/${id}/avg`)
    }

}