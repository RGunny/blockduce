import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import Vote from '../views/After/Vote.vue'
import Now from '../views/After/Now.vue'
// import Wallet from '../views/After/Wallet.vue'
// import Blockduce from '../views/After/Blockduce.vue'

Vue.use(VueRouter)

const routes = [
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
