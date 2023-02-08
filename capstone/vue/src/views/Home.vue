<template>
  <div class="home d-flex">
    <div class="posts-container">
      <div class="post-container">
        <post
          class="each-post"
          v-for="post in this.$store.state.allPosts"
          v-bind:key="post.post_id"
          v-bind:post="post"
        />
      </div>
    </div>
    <div class="sort-container d-flex flex-column">
      <div class="sort-div">
      <button id="sort-btn" class="btn btn-lg btn-outline-secondary dropdown-toggle align-self-start" type="button"
        data-toggle="dropdown"
        aria-expanded="false"
      >Sort By
      </button>
      <div class="dropdown-menu">
        <a class="dropdown-item" type="button" v-on:click="orderByRating('desc')"
          >Highest Rating</a>
        <a class="dropdown-item" type="button" v-on:click="orderByRating('asc')"
          >Lowest Rating</a>
        <div role="separator" class="dropdown-divider"></div>
        <a class="dropdown-item" type="button">Newest Posts</a>
        <a class="dropdown-item" type="button">Oldest Posts</a>
      </div>
      </div>
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
      this.listOfPosts = response.data;
      this.$store.commit("SET_ALL_POSTS", response.data);
    });
    apiService
      .displayFavoritePosts(this.$store.state.user.username)
      .then((response) => {
        this.$store.commit("SET_FAVORITES", response.data);
      });
    apiService
      .displayFavoriteIds(this.$store.state.user.username)
      .then((response) => {
        this.$store.commit("SET_FAV_IDS", response.data);
      });
  },
  methods: {
    orderByRating(order) {
      apiService.displayPostsByRating(order).then((response) => {
        if (response.status == 200) {
          this.listOfPosts = response.data;
        }
      });
    },
  },
};
</script>

<style>
.sort-container{
    width:100%;
  align-items: center;
}
#sort-btn:hover {
  background-color: rgba(171, 71, 188, 0.2);
  color: rgb(173, 171, 171);
}

#sort-btn:focus {
  background-color: rgba(171, 71, 188, 0.2);
  color: rgb(173, 171, 171);
}

.posts-container {
  margin-right: auto;
  margin-left: 320px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
}

.each-post {
  margin: 10px;
  border-bottom: 1px solid rgb(223, 218, 218);
}
</style>
