import Vue from "vue";
import axios from "axios";
axios.defaults.baseURL = process.env.API_URL;
axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";
Vue.prototype.$axios = axios;
