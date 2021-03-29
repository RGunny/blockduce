import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueSocketIO from 'vue-socket.io';
import io from 'socket.io-client';
import VueResource from 'vue-resource';
import './styles/app.scss';
import { url } from './utils/config';
import adapter from 'webrtc-adapter';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import Vuesax from 'vuesax';

import 'vuesax/dist/vuesax.css';

console.log(
  `Browser ${adapter.browserDetails.browser} - version ${adapter.browserDetails.version}`
);
Vue.use(Vuesax);

Vue.use(BootstrapVue);

Vue.config.productionTip = false;

// Socket config
Vue.use(
  new VueSocketIO({
    debug: true,
    connection: io(`${url}/video-chat`, { autoConnect: false }),
    vuex: {
      store,
      actionPrefix: 'SOCKET_',
      mutationPrefix: 'SOCKET_',
    },
  })
);

// Vue resource for http
Vue.use(VueResource);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
