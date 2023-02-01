<template>
  <div class="post-container">
    <h3 class="username">{{ post.username }}</h3>
    <img :src="post.photo_url" alt="`${post.username}'s image`" />
    <p id="likes">{{ post.likes }} likes</p>
    <p id="caption"><span class="username">{{post.username}}</span>&nbsp;{{ post.caption }}</p>
    <div class="comments">
      <p v-for="comment in listOfComments" v-bind:key="comment.comment_id"><span id="commenter">{{comment.commenter}}</span>&nbsp;{{comment.comment}}</p>  
    </div>

    <input class="comment-input" v-if="$store.state.token != ''" type="text" placeholder="Write a comment..." >
  </div>
</template>

<script>
import apiService from "../services/APIService.js";

export default {
  name: "Post",
  props: ["post"],
  data(){
    return {
      listOfComments: []
    }
  },

  created(){
    apiService.displayCommentsByPost(this.post.post_id).then(response => {
      this.listOfComments = response.data;
    })
  }

};
</script>

<style>
.comment-input {
  border-style: none;
}
#commenter, .username, #likes{
  font-weight: bold;
}

.comments, #likes, #caption, .username{
  text-align:left;
}

</style>