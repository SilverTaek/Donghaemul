<template>
  <div class="login">
    <!-- 로고 -->
    <div class="logo">
      <LogoName />
    </div>

    <!-- 로그인 폼 -->
    <LoginForm />

    <!-- 소셜 로그인 모달 -->
    <SocialLogin />

    <router-link to="Register" class="registerButton">회원가입</router-link>
  </div>
</template>

<script>
import '@/components/css/login/style.scss';
import LoginForm from '@/components/login/loginForm';
import SocialLogin from '@/components/login/socialLogin';
import { LogoName } from '@/assets/index.js';

export default {
  name: 'Login',
  components: {
    LoginForm,
    SocialLogin,
    LogoName,
  },
};
</script>
