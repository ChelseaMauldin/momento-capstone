<template>
  <div id="app">
    <div id="nav">
      <i class="fa-solid fa-house-user"
        >&nbsp;<router-link class="link-text" v-bind:to="{ name: 'home' }"
          >Home</router-link
        ></i
      >
      <i
        v-if="$store.state.token == ''"
        class="fa-solid fa-arrow-right-to-bracket"
        >&nbsp;<router-link class="link-text" v-bind:to="{ name: 'login' }"
          >Login</router-link
        ></i
      >
      <i
        v-if="$store.state.token != ''"
        class="fa-solid fa-arrow-right-to-bracket"
        ><router-link class="link-text" v-bind:to="{ name: 'logout' }"
          >Logout</router-link
        ></i
      >
      <!-- &nbsp;|&nbsp; -->
    </div>
    <div id="page">
      <div class="title">
        <h1>TE-Gram</h1>
        <div class="dropdown-search-menu">
          <input
            v-model="searchQuery"
            type="text"
            placeholder="Search for User"
          />
          <div class="options">
            <ul>
              <li>List Item</li>
              <li>List Item</li>
            </ul>
          </div>
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<style>
#app {
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "nav link";
  margin: -8px;
  padding: 0;
  min-height: 100vh;
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

.title {
  background-color: rgba(255, 51, 102, 0.2);
  height: 75px;
  width: 100%;

  text-align: center;
}

.dropdown-search-menu {
  max-width: 350px;
  position: relative;
  margin: 0 auto;
}

.selected-item {
  height: 40px;
  border: 2px solid lightgray;
  border-radius: 5px;
  padding: 5px 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: 500;
}

.dropdown-popover {
  position: absolute;
  border: 2px solid lightgray;
  top: 46px;
  left: 0;
  right: 0;
  background-color: #fff;
  max-width: 100%;
}
</style>