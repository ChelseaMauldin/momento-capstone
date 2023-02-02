<template>
  <div class="post-container">
    <h3 v-if="isPhotoFeed" class="username">
      <router-link
        v-bind:to="{ name: 'profile', params: { username: post.username } }"
        >{{ post.username }}</router-link
      >
    </h3>
    <img :src="post.photo_url" alt="`${post.username}'s image`" />
    <div class="reactions" v-if="isPhotoFeed">
      <i
        id="likeIcon"
        v-if="!liked"
        v-on:click="addLike(post.post_id)"
        class="likeButton fa-regular fa-heart"
      ></i>
      <i
        class="likeButton fa-solid fa-heart"
        v-if="liked"
        v-on:click="removeLike(post.post_id)"
      ></i>

      <p id="likes">{{ post.likes }} likes</p>
    </div>

    <p id="caption" v-if="isPhotoFeed">
      <span class="username">{{ post.username }}</span
      >&nbsp;{{ post.caption }}
    </p>
    <div class="comments" v-if="isPhotoFeed">
      <p v-for="comment in listOfComments" v-bind:key="comment.comment_id">
        <span id="commenter">{{ comment.commenter }}</span
        >&nbsp;{{ comment.comment }}
      </p>
    </div>

    <input
      class="comment-input"
      v-if="$store.state.token != '' && isPhotoFeed"
      type="text"
      placeholder="Write a comment..."
    />
  </div>
</template>

<script>
import apiService from "../services/APIService.js";

export default {
  name: "Post",
  props: ["post", "isPhotoFeed"],
  data() {
    return {
      listOfComments: [],
      liked: false,
      isPhotoFeed: true,
    };
  },
  methods: {
    addLike(id) {
      apiService.like(id).then((response) => {
        if (response.status == 200) {
          this.liked = true;
          this.$store.commit("ADD_LIKE", this.post);
        }
      });
    },
    removeLike(id) {
      apiService.unlike(id).then((response) => {
        if (response.status == 200) {
          this.liked = false;
          this.$store.commit("REMOVE_LIKE", this.post);
        }
      });
    },
  },

  created() {
    apiService.displayCommentsByPost(this.post.post_id).then((response) => {
      this.listOfComments = response.data;
    });
  },
};
</script>

<style>
.comment-input {
  border-style: none;
}
#commenter,
.username,
#likes {
  font-weight: bold;
}

.comments,
#likes,
#caption,
.username {
  text-align: left;
}

.reactions {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.fa-heart {
  color: red;
}

.likeButton:hover {
  cursor: pointer;
}
</style>