<template>
  <div class="main">
      
  </div>
</template>

<script>
import apiService from '../services/APIService.js'
export default {
    data(){
        return{
            profile: {}
        }
    },
    created(){
        apiService.displayProfile(this.$route.params.username).then(response => {
            this.profile = response.data;
        })
    }
}
</script>

<style>

</style>