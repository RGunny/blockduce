import Vue from 'vue'
import VueRouter from 'vue-router'
import Now from '../views/After/Now.vue'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css' // This line here
import VueToastr from "vue-toastr"

import Home from './../views/Home.vue'
import store from '../store'

import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import Join from '../views/Join.vue'
import Vote from '../views/After/Vote.vue'
// import Wallet from '../views/After/Wallet.vue'
// import Blockduce from '../views/After/Blockduce.vue'

Vue.use(VueMaterial)
Vue.use(VueToastr, {
  defaultPosition: "toast-top-left",
  defaultTimeout: 3000,
  defaultProgressBar: false,
  defaultProgressBarValue: 0,
})
Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home,
    beforeEnter: (to, from, next) => {
      store.state.room && store.state.username ? next('/chat') : next()
    }
  },
  {
    path: '/chat',
    name: 'chat',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ './../views/Chat.vue'),
    beforeEnter: (to, from, next) => {
      !store.state.room && !store.state.username ? next('/') : next()
    }
  },
  {
    path: '/',
    name: 'Main',
    component: Main
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/after/vote',
    name: 'Vote',
    component: Vote
  },
  {
    path: '/after/now',
    name: 'Now',
    component: Now
  },
  {
    path:"/kakaologin",
    name:"Klogin",

    component:() => import("../views/kakaologin.vue")
  }
  // {
  //   path: '/after/wallet',
  //   name: 'Wallet',
  //   component: Wallet
  // },

  // {
  //   path: '/after/blockduce',
  //   name: 'Blockduce',
  //   component: Blockduce
  // },
  
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
