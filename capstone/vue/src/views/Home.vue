<template>
  <div class="home">
    <!-- <div class="title">
      <h1>Home</h1>
    </div> -->
    <span class="post-container">
      <post class="each-post"
        v-for="post in listOfPosts"
        v-bind:key="post.postId"
        v-bind:post="post"
      />
    </span>
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

<style>
.home {
  
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

img{
  border-radius: 10px;
}
</style>
