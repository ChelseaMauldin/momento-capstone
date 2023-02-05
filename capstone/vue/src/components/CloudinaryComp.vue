<template>
  <div class="upload-main d-flex flex-column">
    <button v-if="!urlPosted" v-on:click="upload">Upload a Picture</button
    ><br />
    <p v-if="urlPosted">Upload Complete!</p>
    <textarea
      v-if="this.$route.name != 'edit-profile'"
      class="mt-3"
      v-model="post.caption"
      placeholder="Add a caption..."
      id="caption-input"
      rows="3"
    ></textarea>
    <button
      v-if="this.$route.name != 'edit-profile'"
      type="button"
      class="btn btn-primary"
      data-dismiss="modal"
      v-on:click="addPost"
    >
      Save changes
    </button>
  </div>
</template>

<script>
import apiService from "../services/APIService.js";
export default {
  name: "CloudinaryComp",
  data() {
    return {
      myWidget: {},
      post: {
        photo_url: "",
        username: this.$store.state.user.username,
        likes: 0,
        caption: "",
      },
      urlPosted: false,
    };
  },
  methods: {
    upload() {
      this.myWidget.open();
    },
    addPost() {
      if (this.post.photo_url !== "") {
        apiService.createPost(this.post).then((response) => {
          if (response.status == 200) {
            this.post.caption = "";
            this.post.photo_url = "";
            this.$router.push(`/profile/${this.post.username}`);
            this.urlPosted = false;
          }
        });
      }
    },
  },
  mounted() {
    this.myWidget = window.cloudinary.createUploadWidget(
      {
        // Insert your cloud name and presets here,
        // see the documentation
        cloudName: "depdfgnxo",
        uploadPreset: "c0pfjpmh",
      },
      (error, result) => {
        if (!error && result && result.event === "success") {
          if (this.$route.name != "edit-profile") {
            this.post.photo_url = result.info.url;
          } else {
            this.$store.commit("SET_PROFILE_IMAGE_URL", result.info.url);
          }

          this.urlPosted = true;
        }
      }
    );
  },
};
</script>

<style>
</style>
