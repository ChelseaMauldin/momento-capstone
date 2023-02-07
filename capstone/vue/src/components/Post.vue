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
    <img
      id="post-img"
      :src="post.photo_url"
      alt="`${post.username}'s image`"
      data-toggle="modal"
      :data-target="`#postDetails${post.post_id}`"
    />
    <div
      class="modal fade"
      ref="postDetailsModal"
      :id="`postDetails${post.post_id}`"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" style="max-width: 70%">
        <div class="modal-content" id="details-content">
          <div class="modal-body" id="details-body">
            <post-details :post="post" />
          </div>
        </div>
      </div>
    </div>
    <div class="reactions" v-if="this.$route.name == 'home'">
      <div class="likes-ratings">
        <div class="reactions-likes">
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
        </div>
        <div class="reactions-ratings">
          <ratings v-bind:ratingsForPost="ratingsForPost"
          v-bind:rateValue="rateValue"
          v-bind:postId="post.post_id" />
        </div>
      </div>

      <p id="likes">{{ post.likes }} likes</p>
    </div>

    <p id="caption" v-if="this.$route.name == 'home'">
      <span class="username-post">{{ post.username }}</span
      >&nbsp;{{ post.caption }}
    </p>
    <div class="comments" v-if="this.$route.name == 'home'">
      <p v-for="comm in shortenedCommentsList(2)" v-bind:key="comm.id">
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
import PostDetails from "../views/PostDetails.vue";
import Ratings from "./Ratings.vue"
export default {
  name: "Post",
  props: ["post", "isPhotoFeed"],
  components: {
    PostDetails,
    Ratings,
  },
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
      ratingsForPost: [],
      rateValue: null,
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
            this.listOfComments.push(this.newComment);
            this.filter = "";
          }
        });
      }
    },
    shortenedCommentsList(commentsListSize) {
      return this.listOfComments.slice(0, commentsListSize);
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
    apiService.getRatingsById(this.post.post_id).then((response) => {
      console.log(response.data);
      this.ratingsForPost = response.data;
    });
    apiService.getRatingByUser(this.post.post_id, this.$store.state.user.username).then((response) => {
      this.rateValue = response.data;
    })
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

#details-body {
  margin: 0;
  padding: 0;
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

div.likes-ratings {
  display: flex;
  width: 100%;
  justify-content: flex-start;
}

.reactions-likes, .reactions-ratings {
  flex: 1;
  display: flex;
  justify-content: flex-start;
  align-self: center;
}

.reactions-likes {
  height: 100%;
}

.reactions-likes i.likeButton {
  padding: 5px;
  font-size: 30px;
  color: red;
}

.reactions-ratings {
  justify-content: flex-end;
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

.post-header i {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  width: 100%;
}

.post-header .username-post {
  flex: 1;
}
</style>