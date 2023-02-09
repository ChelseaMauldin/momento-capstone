<template>
  <div class="main-favorites">
    <h3 class="favorite-title">Favorites</h3>
    <div class="favorite-posts-container">
      <post
        class="favorite-post"
        v-for="eachPost in this.$store.state.favoritePosts"
        v-bind:key="eachPost.post_id"
        v-bind:post="eachPost"
      />
    </div>
  </div>
</template>

<script>
import Post from "../components/Post.vue";
//import apiService from '../services/APIService'

export default {
  name: "favorite",
  components: {
    Post,
  },
  data() {
    return {
      listOfIds: [],
    };
  },
  created() {},
};
</script>

<style>
h3.favorite-title {
  text-align: center;
  font-size: 3rem;
  font-weight: bolder;
  color: rgb(188, 141, 221);
  color: linear-gradient(
    -90deg,
    rgba(188, 141, 221, 1) 25%,
    rgba(138, 193, 255, 1) 50%,
    rgba(241, 134, 134, 1) 100%
  );
  margin: 10px;
}
div.main-favorites {
  display: flex;
  flex-direction: column;
  align-items: center;
}

div.favorite-posts-container {
  align-self: center;
  display: flex;
  flex-basis: 100%;
  flex-wrap: wrap;
  width: 150vh;
}

.favorite-post {
  width: 25%;
  margin: 10px;
  height: fit-content;
}
</style>