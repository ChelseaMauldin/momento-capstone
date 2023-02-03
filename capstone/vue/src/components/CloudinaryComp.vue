<template>
  <div class="upload-main d-flex flex-column">
      
      <button v-on:click="upload">Upload a Picture</button><br>
      <textarea class="mt-3" v-model="post.caption"
        placeholder="Add a caption..."
          id="caption-input"
          rows="3"
        ></textarea>
        <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="addPost">Save changes</button>
  </div>
</template>

<script>
import apiService from "../services/APIService.js"
export default {
  name: 'CloudinaryComp',
  data() {
    return {
      myWidget : {},
      post: {
        photo_url: "",
        username: this.$store.state.user.username,
        likes: 0,
        caption: ""
        }
    }
  },
  methods: {
      upload() {
        this.myWidget.open();
      },
     addPost(){
        
        apiService.createPost(this.post).then(response => {
            if(response.status==200){
                this.$router.push(`/profile/${this.post.username}`)
            }
        })
     }
  },
  mounted() {
       this.myWidget = window.cloudinary.createUploadWidget(
      {
        // Insert your cloud name and presets here, 
        // see the documentation
        cloudName: 'depdfgnxo', 
        uploadPreset: 'c0pfjpmh'
      }, 
      (error, result) => { 
        if (!error && result && result.event === "success") { 
            this.post.photo_url=result.info.url
          console.log('Done! Here is the image info: ', result.info); 
          console.log("Image URL: " + result.info.url);
        }
      }
    );

  }
}
</script>

<style>

</style>
