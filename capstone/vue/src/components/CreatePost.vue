<template>
  <div class="create-main d-flex flex-column" >
      <!-- <form class="container " > -->
    
     
        <Cloudinary-Comp msg="Welcome to Your Vue.js App"/>
        
        <textarea class="mt-5"
        placeholder="Add a caption..."
          id="caption-input"
          rows="3"
        ></textarea>
     
      

      <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
    <!-- </form> -->
    
  </div>
</template>

<script>
import CloudinaryComp from '../components/CloudinaryComp.vue'
export default {
    components: {
        CloudinaryComp
    }
}
</script>

<style>

</style>