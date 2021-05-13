<template>
  <div class="register">
    <LogoName />
    <EmailInput v-if="!emailStatus" v-on:pass="checkEmail" />
    <PasswordInput v-if="emailStatus" :email="email" />
  </div>
</template>
<script>
import '@/components/css/register/style.scss';
import { LogoName } from '@/assets/index.js';
import EmailInput from '@/components/register/emailInput';
import PasswordInput from '@/components/register/passwordInput';

export default {
  name: 'Register',
  components: {
    LogoName,
    EmailInput,
    PasswordInput,
  },
  data: function() {
    return {
      isActive: false, // 다음 버튼 활성화 비활성화 결정
      emailStatus: false,
      email: '',
    };
  },
  methods: {
    checkEmail: function(email) {
      this.emailStatus = true;
      this.email = email;
    },
  },
};
</script>
