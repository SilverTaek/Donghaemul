<template>
  <div class="passwordInput">
    <h1 class="informationMessage">비밀번호를 입력해주세요</h1>

    <v-form>
      <v-container fluid>
        <v-row>
          <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="alphabetRule"
            :type="showPassword ? 'text' : 'password'"
            label="비밀번호"
            counter
            required
            @click:append="showPassword = !showPassword"
          ></v-text-field>
        </v-row>
        <v-row>
          <v-text-field
            v-model="passwordConfirm"
            :append-icon="showPasswordConfirm ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="passwordConfirmationRule"
            :type="showPasswordConfirm ? 'text' : 'password'"
            label="비밀번호 확인"
            counter
            required
            @click:append="showPasswordConfirm = !showPasswordConfirm"
            @keyup.enter="register()"
          ></v-text-field>
        </v-row>
      </v-container>
    </v-form>
    <v-btn
      @click="register()"
      :class="{ active: isActive, registerButton: 'registerButton' }"
      height="63"
      >회원가입</v-btn
    >

    <v-alert :value="showError" type="error" class="error">
      {{ errorMessage }}
    </v-alert>
  </div>
</template>
<script>
import '@/components/css/register/style.scss';

export default {
  name: 'PasswordInput',
  components: {},
  data() {
    return {
      isActive: false, // 회원가입 버튼 활성화 비활성화 결정
      showPassword: false,
      showPasswordConfirm: false,
      password: '',
      passwordConfirm: '',
      alphabetRule: [
        (value) =>
          /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/.test(value) ||
          '소문자(a-z)와 대문자(A-Z), 숫자(0-9)를 모두 포함하고, 8자 이상이어야 합니다.',
      ],
      passwordConfirmationRule: [(value) => this.password === value || '비밀번호가 같지 않습니다.'],
      showError: false,
      errorMessage: '', // 에러메세지
    };
  },
  props: {
    email: String,
  },
  methods: {
    validatePassword: function() {
      const regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/;
      if (regex.test(this.password) && this.validateConfirm()) {
        this.isActive = true;
      } else this.isActive = false;
    },
    validateConfirm: function() {
      return this.password === this.passwordConfirm ? true : false;
    },
    register: function() {
      this.$axios({
        url: '/members/signup',
        method: 'POST',
        data: {
          memberEmail: this.email,
          memberPassword: this.password,
          memberName: '',
          memberPlatformType: '',
        },
      })
        .then(() => {
          this.$router.push({ name: 'Login' });
        })
        .catch((error) => {
          console.error(error);

          // error alert 출력
          this.showError = true;
          this.errorMessage = '회원가입에 실패했습니다.';

          setTimeout(() => {
            this.showError = false;
          }, 5000);
        });
    },
  },
  watch: {
    password: function() {
      // password 값이 바뀔때마다 함수 실행
      this.validatePassword();
    },
    passwordConfirm: function() {
      // password Confirm 값이 바뀔때마다 함수 실행
      this.validatePassword();
    },
  },
};
</script>
