<template>
  <div class="home">
    <h1>Home</h1>
    <post
      v-for="post in listOfPosts"
      v-bind:key="post.postId"
      v-bind:post="post"
    />
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
    };
  },
  created() {
    apiService.displayPost().then((response) => {
      console.log(response.data);
      this.listOfPosts = response.data;
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
