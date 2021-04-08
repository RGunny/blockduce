import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import accountStore from '@/store/modules/accountStore'

import { STATUS_OPTIONS, url, STORE_ACTIONS } from '../utils/config'
import router from '@/router'
import axios from 'axios'
import cookies from 'vue-cookies'
import SERVER from '@/api/api'
import Swal from 'sweetalert2'


export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    myaccount: null,
    users: null,
    notis: null,
    room: undefined,
    username: undefined,
    status: STATUS_OPTIONS.available,
    rooms: []
  },
  getters: {
    config: state => ({ headers: { jwt : state.authToken}}),
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_MY_ACCOUNT(state, user) {
      state.myaccount = user
    },
    SET_USERS(state, users) {
      state.users = users
    },
    joinRoom(state, { room, username }) {
      state.room = room
      state.username = username
    },
    changeRoom(state, room) {
      state.room = room
    },
    setRooms(state, rooms) {
      state.rooms = rooms
    },
    leaveChat(state) {
      state.room = undefined
      state.username = undefined
    },
    changeStatus(state) {
      let nextStatus
      if (state.status === STATUS_OPTIONS.available) nextStatus = STATUS_OPTIONS.absent
      if (state.status === STATUS_OPTIONS.absent) nextStatus = STATUS_OPTIONS.unavailable
      if (state.status === STATUS_OPTIONS.unavailable) nextStatus = STATUS_OPTIONS.available

      state.status = nextStatus
    }
  },
  actions: {
    fetchGenres({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.genres)
        .then(res => {
          commit('SET_GENRES', res.data)
        })
        .catch(err => {
          console.log(err.response.data)
        })
    },
    findMyAccount({ rootGetters, commit}) {
      axios.post(SERVER.URL + SERVER.ROUTES.myaccount, null, rootGetters.config)
        .then(res => {
            commit('SET_MY_ACCOUNT', res.data)
        })
        .catch(err => console.log(err.response.data))
    },
    fetchBooks({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.books)
        .then(res => {
          commit('SET_BOOKS', res.data)
        })
        .catch(err => console.log(err.response.data))
    },
    fetchUsers({ commit }) {
      axios.get(SERVER.URL + '/users/')
        .then(res => {
          commit('SET_USERS', res.data)
        })
        .catch(err => console.log(err.response.data))
    },
    createNoti({ getters }, notiData) {
      axios.post(SERVER.URL + SERVER.ROUTES.noti, notiData, getters.config)
        .catch(err => console.log(err.response.data))
    },
    deleteNoti({ getters }, notiId) {
      axios.delete(SERVER.URL + SERVER.ROUTES.noti + '/' + notiId, getters.config)
        .catch(err => console.log(err.response.data))
    },
    logout({ commit }) {
          commit('SET_TOKEN', null)
          cookies.remove('auth-token')
          // commit('SET_INIT')
          const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            onOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
           })
           Toast.fire({
            icon: 'success',
            title: '로그아웃되었습니다.'
          })
          router.push({ name: 'Login' })
    },
    joinRoom({ commit }, data) {
      return new Promise(function (resolve, reject) {
        try {
          const { body } = Vue.http.post(`${url}/auth/login`, data)
          console.log('body', body)
          const { room } = Vue.http.post(`${url}/auth/room`,data)
          console.log('data', data)
          console.log('room', room)
          if (body.code === 400 || body.code === 401 || body.code === 500) {
            reject({ message: body.message })
          }
          commit(STORE_ACTIONS.joinRoom, data)
          resolve(console.log('success'))
          
        } catch (error) {
          reject(error)
        }
      })
    },
    changeRoom({ commit }, room) {
      commit(STORE_ACTIONS.changeRoom, room)
    },
    setRooms({ commit }) {
      return new Promise(function (resolve, reject) {
        try {
          // const rooms = Vue.http.get(`http://${url}/rooms`)
          const rooms = [{
              id: 1,
              name: 'GENERAL'
            }, {
              id: 2,
              name: 'SPORTS'
            },{
              id: 3,
              name: 'GAMES'
            },
          ]
          commit(STORE_ACTIONS.setRooms, rooms)
          resolve(rooms)
        } catch (error) {
          reject(error)
        }
      })
    },
    leaveChat({ commit }, username) {
      return new Promise(function (resolve, reject) {
        try {
          const { body : { code } } = Vue.http.post(`${url}/auth/logout`, { username })
          if (code !== 200) reject()
          commit(STORE_ACTIONS.leaveChat)
          resolve()
        } catch (error) {
          reject(error)
        }
      })
    },
    changeStatus({ commit }) {
      commit(STORE_ACTIONS.changeStatus)
    }
  },

  modules: {
    accountStore: accountStore,
  }
})
