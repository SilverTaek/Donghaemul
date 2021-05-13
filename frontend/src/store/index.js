import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import jwtDecode from 'jwt-decode';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    splash: true, // 첫 방문시에만 splash 보기
    userId: '',
    error: false,
  },
  mutations: {
    LOGIN: (state, token) => {
      const member = jwtDecode(token).member;
      if (member.memberId === null) {
        // 로그인 실패
        state.error = true;
      } else {
        // 로그인 성공
        state.userId = member.memberId;
        state.error = false;
      }
    },
    LOGOUT: (state) => {
      state.userId = '';
    },
    READSPLASH: (state) => {
      state.splash = false;
    },
  },
  plugins: [createPersistedState()],
});
