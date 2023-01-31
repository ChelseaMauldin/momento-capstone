import axios from 'axios';

export default {

    displayPost() {
        return axios.get('/posts')
    }



}