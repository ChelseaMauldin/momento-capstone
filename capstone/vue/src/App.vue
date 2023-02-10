<template>
  <div id="app">
    <div id="nav">
      <i class="nav-items fa-solid fa-house-user"
        >&nbsp;&nbsp;<router-link class="link-text" v-bind:to="{ name: 'home' }"
          >Home</router-link
        ></i
      >
      <i
        v-if="$store.state.token == ''"
        class="nav-items fa-solid fa-arrow-right-to-bracket"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{ name: 'login' }"
          >Login</router-link
        ></i
      >
      <i v-if="$store.state.token != ''" class="nav-items fa-solid fa-heart"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{ name: 'favorites' }"
          >Favorites</router-link
        >
      </i>
      <i v-if="$store.state.token != ''" class="nav-items fa-solid fa-user"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{
            name: 'profile',
            params: { username: this.$store.state.user.username },
          }"
          >Profile</router-link
        >
      </i>
      <i
        v-if="$store.state.token != ''"
        class="nav-items fa-solid fa-arrow-right-to-bracket"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{ name: 'logout' }"
          >Logout</router-link
        >
      </i>

      <!-- &nbsp;|&nbsp; -->
      <i
        v-if="$store.state.token != ''"
        class="nav-items fa-regular fa-square-plus"
        data-toggle="modal"
        data-target="#createModal"
        >&nbsp;
        <span id="create-modal-link">Create</span>
      </i>
      <div class="modal fade" id="createModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Create a Post</h5>
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <create-post />
            </div>
            <div class="modal-footer"></div>
          </div>
        </div>
      </div>
    </div>
    <div id="page d-flex flex-column">
      <div class="title">
        <h1 class="momento-title">Momento</h1>
        <div class="search-bar">
          <input
            type="text"
            id="usernameSearch"
            v-model="filter.username"
            placeholder="Search a username"
            v-on:keyup.enter="getSearchResult"
          />
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>
<script>
import createPost from "./views/CreatePost.vue";
import apiService from "./services/APIService";
export default {
  data() {
    return {
      profile: {},
      filter: {
        username: "",
      },
    };
  },
  methods: {
    getSearchResult(){
      apiService.displayProfile(this.filter.username).then(response =>{
        if(response.status==200){
          this.$store.commit("SET_PROFILE", response.data)
        }
      })

      apiService
      .displayPostsByUsername(this.filter.username)
      .then((response) => {
        this.$store.commit("SET_ALL_POSTS", response.data)
      });
      this.$router.push(`/profile/${this.filter.username}`);
      
    }
  },
  components: {
    createPost,
  },
  // methods: { -- leaving this here for now, it's not being used yet
  //   search: {
  //     searchForUser() {
  //       const username = this.filter.username;
  //       this.$router.push({ name: "profile", params: { username } });
  //     },
  //   },
  // },
  mounted() {
    apiService
      .displayFavoritePosts(this.$store.state.user.username)
      .then((response) => {
        this.$store.commit("SET_FAVORITES", response.data);
      });
    apiService
      .displayFavoriteIds(this.$store.state.user.username)
      .then((response) => {
        this.$store.commit("SET_FAV_IDS", response.data);
      });
    apiService.displayAllComments().then((response) => {
      this.$store.commit("SET_COMMENTS", response.data);
    });
  },
};
</script>

<style>
.nav-items.fa-solid.fa-house-user {
  padding-top: 70px;
}

.nav-items {
  padding-bottom: 20px;
  font-size: 30px;
  border-bottom: solid rgba(255, 255, 255, 0.7) 1px;
  margin-bottom: 70px;
  margin-right: 4%;
  margin-left: 4%;
}

.momento-title {
  margin-left: 450px;
  font-family: 'Caveat', cursive;
  font-size: 65px;
  /* font-weight: bold; */
}

#app {
  /* background: rgb(238, 174, 202); */
  /* background: linear-gradient(
    90deg,
    rgba(247, 216, 230, 1)
 0%,
    rgba(255, 51, 102, 0.2) 100%
  ); */

  background: rgba(253, 244, 248, 1);

  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "nav link";
  /* margin: -8px; */
  padding: 0;
  min-height: 100vh;
  width: 100%;
  flex-basis: 100%;
  font-family: Arial, Helvetica, sans-serif;
  background: rgb(238,174,202);
  background: linear-gradient(90deg, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);
}

#nav {
  padding-top: 100px;
  grid-area: nav;
  display: flex;
  flex-direction: column;
  /* background: rgba(171, 71, 188, 0.2); */
  text-align: center;
  border: lavenderblush 1px solid;
}
/* #nav i {
  margin: 5px 10px 10px 10px;
} */

#nav i .link-text {
  text-decoration: none;
  font-family: "Mukta", sans-serif;
  font-size: 30px;
  color: black;
  padding-bottom: 100px;
}

#page {
  grid-area: link;
  width: 100%;
}
#create-modal-link {
  font-weight: bold;
  font-family: "Mukta", sans-serif;
  font-size: 30px;
  color: black;
}
#create-modal-link:hover {
  cursor: pointer;
}

.title {
/*  background-color: rgba(255, 51, 102, 0.2);*/
  height: 75px;
  width: 100%;
  display: flex;
  text-align: center;
  border-right: white 1px solid;
  border-top: white 1px solid;
  border-bottom: white 1px solid;
}

h1,
.search-bar {
  flex: 1;
}

.search-bar {
  text-align: right;
  width: 100%;
  margin-top: 5px;
}

.fa-solid.fa-heart {
  color: black;
}
</style>