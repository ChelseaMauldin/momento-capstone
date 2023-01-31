<template>
  <div class="post-container">
    <h3>{{ post.username }}</h3>
    <img :src="post.photo_url" alt="`${post.username}'s image`" />
    <p>{{ post.likes }}</p>
    <p>{{ post.caption }}</p>
    <p>{{ post.comments }}</p>
    <input class="comment-input" v-if="$store.state.token != ''" type="text" placeholder="Write a comment..." >
  </div>
</template>

<script>
export default {
  name: "Post",
  props: ["post"],
};
</script>

<style>
.comment-input {
  border-style: none;
}

</style>