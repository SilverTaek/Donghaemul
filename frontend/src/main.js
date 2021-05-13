import Vue from 'vue';
import App from './App.vue';
import router from './router';
import vuetify from './plugins/vuetify';
import axios from 'axios';
import store from './store';
import firebase from 'firebase';

const firebaseConfig = {
  apiKey: 'AIzaSyBrBWdp7XqKk5VYZxIoUghLxyuDEv7MCOU',
  authDomain: 'kingredient-edda9.firebaseapp.com',
  projectId: 'kingredient-edda9',
  storageBucket: 'kingredient-edda9.appspot.com',
  messagingSenderId: '501453057674',
  appId: '1:501453057674:web:8dd2b96f06666125fd0773',
  measurementId: 'G-ZHNRTP0XJ4',
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

window.Kakao.init('bd78c60e1e7374f5ed32a493f17b4d7a');

Vue.config.productionTip = false;

Vue.prototype.$axios = axios;
axios.defaults.baseURL = 'https://j4a301.p.ssafy.io:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount('#app');
