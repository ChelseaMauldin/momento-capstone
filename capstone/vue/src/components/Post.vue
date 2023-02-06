<template>
  <div class="post-container">
    <div class="post-header">
      <h3 v-if="this.$route.name == 'home'" class="username-post">
        <router-link
          v-bind:to="{ name: 'profile', params: { username: post.username } }"
          >{{ post.username }}</router-link
        >
      </h3>
      <i
        v-if="
          !isFavorite && $store.state.token != '' && this.$route.name == 'home'
        "
        v-on:click="addFavorite(post)"
        class="fa-regular fa-square-plus"
      ></i>
      <i
        v-if="
          (isFavorite &&
            $store.state.token != '' &&
            this.$route.name == 'home') ||
          (isFavorite && this.$route.name == 'favorites')
        "
        v-on:click="removeFavorite(post)"
        class="fa-regular fa-square-minus"
      ></i>
    </div>
    <img id="post-img" :src="post.photo_url" alt="`${post.username}'s image`" />
    <div class="reactions" v-if="this.$route.name == 'home'">
      <i
        id="likeIcon"
        v-if="!liked && $store.state.token != ''"
        v-on:click="addLike(post.post_id)"
        class="likeButton fa-regular fa-heart"
      ></i>
      <i
        class="likeButton fa-solid fa-heart"
        v-if="liked && $store.state.token != ''"
        v-on:click="removeLike(post.post_id)"
      ></i>

      <p id="likes">{{ post.likes }} likes</p>
    </div>

    <p id="caption" v-if="this.$route.name == 'home'">
      <span class="username-post">{{ post.username }}</span
      >&nbsp;{{ post.caption }}
    </p>
    <div class="comments" v-if="this.$route.name == 'home'">
      <p v-for="comm in listOfComments" v-bind:key="comm.id">
        <span id="commenter">{{ comm.commenter }}</span
        >&nbsp;{{ comm.comment }}
      </p>
    </div>
    <div class="submit-new-comment">
      <input
        class="comment-input"
        v-if="$store.state.token != '' && this.$route.name == 'home'"
        type="text"
        v-model="filter"
        v-on:keyup.enter="createComment()"
        placeholder="Write a comment..."
      />
    </div>
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
      filter: "",
      newComment: {
        post_id: this.post.post_id,
        commenter: this.$store.state.user.username,
        comment: "",
      },
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
    addFavorite(post) {
      const postToAdd = {
        username: this.$store.state.user.username,
        post_id: post.post_id,
      };
      apiService.addFavoritePost(postToAdd).then((response) => {
        if (response.status == 200) {
          alert("added!");
          this.$store.commit("ADD_FAVORITE_POST", post, postToAdd);
        }
      });
    },
    removeFavorite(post) {
      apiService.deleteFavorite(post.post_id).then((response) => {
        if (response.status == 200) {
          alert("removed!");
          this.$store.commit("REMOVE_FAVORITE_POST", post);
        }
      });
    },
    createComment() {
      if (this.filter != "") {
        this.newComment.comment = this.filter;
        apiService.createNewComment(this.newComment).then((response) => {
          if (response.status == 201) {
            console.log(this.newComment.comment);
            this.listOfComments.push(this.newComment);
            this.filter = "";
          }
        });
      }
    },
  },
  computed: {
    isFavorite() {
      return this.$store.state.favoriteIds.includes(this.post.post_id);
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
.submit-new-comment {
  justify-content: flex-start;
  display: flex;
}

.comment-input:focus {
  outline: none;
}

.comment-input {
  border-style: none;
  width: 100%;
}

#commenter,
.username-post,
#likes {
  font-weight: bold;
}

.comments,
#likes,
#caption,
.username-post {
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

#post-img {
  border-radius: 10px;
}

.post-header {
  display: flex;
  align-items: center;
}

.post-header .username-post {
  flex: 1;
}
</style>