<template>
  <div class="main-profile">
    <div class="bio-header">
      <div id="profile-pic-div" class="profile-picture-div">
        <img
          id="profile-pic"
          class="profile-picture"
          :src="profile.profile_image"
          :alt="`${profile.name}'s profile image`"
        />
      </div>
      <div class="name-and-bio">
        <div class="username-and-edit">
          <h2 class="profile-username">{{ this.$route.params.username }}</h2>
          <router-link
            v-if="
              this.$store.state.user.username == this.$route.params.username
            "
            v-bind:to="{
              name: 'edit-profile',
              params: { username: this.$route.params.username },
            }"
            ><button type="button" id="editBtn" class="btn">
              Edit Profile
            </button>
          </router-link>
        </div>

        <h2>{{ profile.name }}</h2>
        <p>Bio: {{ profile.profile_bio }}</p>
      </div>
    </div>
    <div class="profile-posts">
      <post
        class="post-images"
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
#profile-pic-div {
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  width: 185px;
  height: 185px;
  border: 3px rgb(240, 67, 119) solid;
}
#profile-pic {
  border-radius: 50%;
  object-fit: cover;
  object-position: center;

  width: 175px;
  height: 175px;
}
.username-and-edit {
  display: flex;
}
#editBtn {
  margin-left: 30px;
  background-image: linear-gradient(to right, #e2336b, #fcac46);
  width: 150px;

  color: white;
}

.post-images {
  width: 600px;
}

.main-profile {
  display: flex;
  flex-direction: column;

  width: 90%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 40px;

  padding: 0;
  font-family: "Mukta", sans-serif;
  font-weight: bold;
}

.bio-header {
  display: flex;

  align-self: center;
}

.name-and-bio {
  display: flex;
  flex-direction: column;
  align-items: flex-start;

  margin-left: 70px;
  width: 200px;
}

.profile-posts {
  display: flex;
  flex-wrap: wrap;
  margin-top: 50px;
  justify-content: left;
}
</style>