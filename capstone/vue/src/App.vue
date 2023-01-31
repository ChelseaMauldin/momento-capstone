<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>
      <!-- &nbsp;|&nbsp; -->
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <div id="page">
      <div class="title">
        <h1>TE-Gram</h1>
      </div>
      <router-view/>
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
  grid-area: nav;
  display: flex;
  flex-direction: column;
  background: rgba(171, 71, 188, 0.2);
}

#page {
  grid-area: link;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title {
  background-color: rgba(255, 51, 102, 0.20);
  width: 100%;

  text-align: center;
}
.h1 {
  height: 100%;
}
</style>