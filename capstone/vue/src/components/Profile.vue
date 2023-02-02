<template>
  <div class="main">
    <div class="picture-and-username">
      <img
        class="profile-picture"
        :src="profile.profile_image"
        alt="`${profile.username}'s image`"
      />
      <h2 class="profile-username">
        {{ this.$route.params.username }}
      </h2>
    </div>
    <div class="name-and-bio">
      <h2>{{ profile.name }}</h2>
      <p>Bio: {{ profile.profile_bio }}</p>
    </div>
    <div class="profile-posts">
      <post
        v-for="post in listOfPosts"
        v-bind:key="post.post_id"
        v-bind:post="post"
      />
    </div>
  </div>
</template>

<script>
import Post from "./Post.vue";

export default {
  props: ["profile", "listOfPosts"],
  components: {
    Post,
  },
};
</script>

<style>
.profile-picture {
  border-radius: 100%;
  width: 100px;
  height: 100px;
}

.main {
  display: flex;
  flex-direction: column;
}

.picture-and-username {
  display: flex;
  justify-content: center;
}

.name-and-bio {
  display: flex;
}
</style>