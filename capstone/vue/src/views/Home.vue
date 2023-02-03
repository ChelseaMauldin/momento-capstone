<template>
  <div class="home">
    <!-- <div class="title">
      <h1>Home</h1>
    </div> -->
    <div class="post-container">
      <post class="each-post"
        v-for="post in listOfPosts"
        v-bind:key="post.postId"
        v-bind:post="post"
      />
    </div>
  </div>
</template>

<script>
import apiService from "../services/APIService.js";
import post from "../components/Post.vue";

export default {
  name: "home",
  components: {
    post,
  },
  data() {
    return {
      listOfPosts: [],
      userCurrent: this.$store.state.user.username,
    };
  },
  created() {
    apiService.displayPosts().then((response) => {
      console.log(response.data);
      this.listOfPosts = response.data;
    });
    apiService.displayFavoritePosts(this.userCurrent).then((response) => {
      this.$store.commit("SET_FAVORITES", response.data);
    });
    apiService.displayFavoriteIds(this.userCurrent).then((response) => {
      this.$store.commit("SET_FAV_IDS", response.data);
    });
  },
  // methods: {
  //   displayPost() {
  //     apiService.displayPost().then((response) => {
  //       console.log(response.data);
  //       this.listOfPosts = response.data;
  //     });
  //   },
  // },
};
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
}

.post-container {
  display: flex;
  flex-direction: column;
  width: 80vh;
  text-align: center;
}

.each-post {
margin: 10px;
border-bottom: 1px solid black;
}


</style>
