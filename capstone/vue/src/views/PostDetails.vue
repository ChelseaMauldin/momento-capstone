<template>
  <div class="post-details-main-view">
<<<<<<< HEAD
      <post-details :post="post" :listOfComments="comments"/>
=======
      <post-details :post="post" :listOfComments="listOfComments"/>
>>>>>>> main
  </div>
</template>

<script>
import PostDetails from '../components/PostDetails.vue'
export default {
    components: {
        PostDetails
    },
    props: ["post", "listOfComments"]
}
</script>

<style>

</style>