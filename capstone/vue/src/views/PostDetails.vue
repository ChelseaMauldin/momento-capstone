<template>
  <div class="post-details-main-view">
      <post-details :post="post" :listOfComments="listOfComments"/>
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