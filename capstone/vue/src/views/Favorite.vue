<template>
  <div main-favorites>
      <h3>
          Favorites
      </h3>
      <post 
      v-for="eachPost in this.$store.state.favoritePosts"
      v-bind:key="eachPost.post_id"
      v-bind:post="eachPost"
       />
  </div>
</template>

<script>
import Post from '../components/Post.vue';
//import apiService from '../services/APIService'

export default {
    name: 'favorite',
    components: {
        Post,
    },
    data() {
        return {
            listOfIds: [],
        }
    },
    created() {
    
    }

}
</script>

<style>

</style>