<template>
  <div>
   
      <button v-on:click="upload">Upload a Picture</button><br>
      
  </div>
</template>

<script>
export default {
  name: 'CloudinaryComp',
  data() {
    return {
      myWidget : {}
    }
  },
  methods: {
      upload() {
        this.myWidget.open();
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
