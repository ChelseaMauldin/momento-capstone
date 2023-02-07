import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Dropdown from 'vue-simple-search-dropdown';

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(Dropdown)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
