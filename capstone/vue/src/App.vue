<template>
  <div id="app">
    <div id="nav">
      <i class="fa-solid fa-house-user"
        >&nbsp;&nbsp;<router-link class="link-text" v-bind:to="{ name: 'home' }"
          >Home</router-link
        ></i
      >
      <i
        v-if="$store.state.token == ''"
        class="fa-solid fa-arrow-right-to-bracket"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{ name: 'login' }"
          >Login</router-link
        ></i
      >
      <i v-if="$store.state.token != ''" class="fa-solid fa-heart"
        >&nbsp;<router-link class="link-text" v-bind:to="{ name: 'favorites' }"
          >Favorites</router-link
        >
      </i>
      <i v-if="$store.state.token != ''" class="fa-solid fa-user"
        ><router-link
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
        class="fa-solid fa-arrow-right-to-bracket"
        >&nbsp;&nbsp;<router-link
          class="link-text"
          v-bind:to="{ name: 'logout' }"
          >Logout</router-link
        >
      </i>

      <!-- &nbsp;|&nbsp; -->
      <i
        v-if="$store.state.token != ''"
        class="fa-regular fa-square-plus"
        data-toggle="modal"
        data-target="#createModal"
        >&nbsp;&nbsp;
        <span id="create-modal-link">Create</span>
      </i>
      <div class="modal fade" id="createModal" tabindex="-1">
        <div class="modal-dialog">
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
    <div id="page">
      <div class="title">
        <h1>Momento</h1>
        <div class="search-bar">
          <input
            type="text"
            id="usernameSearch"
            v-model="filter.username"
            placeholder="Search a username"
          />
          <router-link
            v-bind:to="{
              name: 'profile',
              params: { username: filter.username },
            }"
          >
            <button>Search</button>
          </router-link>
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
  created() {
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
      })
  },
};
</script>

<style>
#app {
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "nav link";
  /* margin: -8px; */
  padding: 0;
  min-height: 100vh;
  width: 100%;
  flex-basis: 100%;
}

#nav {
  padding-top: 50px;
  grid-area: nav;
  display: flex;
  flex-direction: column;
  background: rgba(171, 71, 188, 0.2);
  text-align: center;
}
#nav i {
  margin: 5px 10px 10px 10px;
}

#nav i .link-text {
  text-decoration: none;
  color: black;
}

#page {
  grid-area: link;

  width: 100%;
}
#create-modal-link {
  font-weight: bold;
}
#create-modal-link:hover {
  cursor: pointer;
}

.title {
  background-color: rgba(255, 51, 102, 0.2);
  height: 75px;
  width: 100%;
  display: flex;
  text-align: center;
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