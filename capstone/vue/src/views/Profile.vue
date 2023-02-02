<template>
  <div class="main">
    <profile v-bind:profile="profile" v-bind:listOfPosts="listOfPosts" />
  </div>
</template>

<script>
import Profile from "../components/Profile.vue";
import apiService from "../services/APIService.js";
export default {
  components: {
    Profile,
  },
  data() {
    return {
      profile: {},
      listOfPosts: [],
    };
  },
  created() {
    apiService.displayProfile(this.$route.params.username).then((response) => {
      this.profile = response.data;
    });
    apiService
      .displayPostsByUsername(this.$route.params.username)
      .then((response) => {
        this.listOfPosts = response.data;
      });
  },
};
</script>

<style>
</style>