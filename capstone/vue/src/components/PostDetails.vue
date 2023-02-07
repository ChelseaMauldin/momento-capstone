<template>
  <div class="post-details-main row">
    <div class="col-md modal-image-container">
      <img id="post-details-img" :src="post.photo_url" />
    </div>
    <div class="col-md d-flex flex-column" id="picture-details">
      <div class="user-headline d-flex">
        <div id="post-details-profile-picture-div">
          <img
            id="post-details-profile-picture"
            :src="profile.profile_image"
            alt="`${profile.username}'s image`"
          />
        </div>
        <p id="post-details-caption" class="align-self-end">
          <span class="username-post"> {{ post.username }}</span
          >&nbsp;{{ post.caption }}
        </p>
      </div>
      <div id="post-details-comments" class="comments">
        <p v-for="comm in comments" v-bind:key="comm.id">
          <span id="commenter">{{ comm.commenter }}</span
          >&nbsp;{{ comm.comment }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import apiService from "../services/APIService.js";
export default {
  data() {
    return {
      profile: {},
    };
  },
  props: ["post"],
  computed: {
    comments() {
      return this.$store.state.comments;
    },
  },
  mounted() {
    apiService.displayCommentsByPost(this.post.post_id).then((response) => {
      console.log(this.post);
      this.comments = response.data;
    });
    apiService.displayProfile(this.post.username).then((response) => {
      this.profile = response.data;
    });
  },
};
</script>
    
<style>
.user-headline {
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid rgb(223, 218, 218);
}
.modal-image-container {
  margin: 0;
  padding: 0;
}
.modal-image-container img {
  width: 700px;
}
#post-details-profile-picture-div {
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  border: 3px rgb(240, 67, 119) solid;
}
#post-details-profile-picture {
  border-radius: 50%;
  object-fit: cover;
  object-position: center;
  width: 40px;
  height: 40px;
}

#post-details-container {
  margin: 0;
  padding: 0;
}
#post-details-caption {
  margin-left: 10px;
}

.post-details-main {
  margin: 0;
  padding: 0;
}

#picture-details {
  margin: 0;
  padding: 0;
}

#post-details-comments {
  margin-top: 10px;
  padding-left: 9.9%;
  padding-right: auto;
}
</style>