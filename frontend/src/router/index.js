import Vue from 'vue';
import VueRouter from 'vue-router';
import Main from '../views/Main';
import Splash from '../views/Splash';
import Login from '../views/Login';
import Register from '../views/Register';
import Detail from '../views/Detail';

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(() => {
    return window.location.reload();
  });
};

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/splash',
    name: 'Splash',
    component: Splash,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/:id',
    name: 'Detail',
    component: Detail,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
