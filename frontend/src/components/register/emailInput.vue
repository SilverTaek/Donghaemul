<template>
  <div class="emailInput">
    <h1 class="informationMessage">이메일을 입력해주세요</h1>

    <v-form onsubmit="return false;">
      <v-container fluid>
        <v-row>
          <v-text-field
            v-model="email"
            :rules="emailRule"
            label="이메일"
            required
            @keyup.enter="goPasswordWithEnter()"
          ></v-text-field>
        </v-row>
      </v-container>
    </v-form>

    <v-btn
      @click="goPassword()"
      :disabled="!isActive"
      :class="{ active: isActive, goPasswordButton: 'goPasswordButton' }"
      height="63"
      >다음</v-btn
    >
  </div>
</template>
<script>
export default {
  name: 'EmailInput',
  data: function() {
    return {
      email: '',
      isActive: false,
      emailRule: [
        (v) =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
          '이메일 형식에 맞춰 작성해주세요.',
      ],
    };
  },
  methods: {
    validateEmail: function() {
      const regex = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
      if (regex.test(this.email)) {
        this.isActive = true;
      } else {
        this.isActive = false;
      }
    },
    goPassword: function() {
      this.$emit('pass', this.email); // 상위 컴포넌트로 이벤트 전달
    },
    goPasswordWithEnter: function() {
      // 이메일 조건이 충족됐을때만 이벤트 전달
      if (this.isActive) this.$emit('pass', this.email);
    },
  },
  watch: {
    email: function() {
      // email 값이 변경될 때마다 다음 버튼을 활성화하기위한 함수 호출
      this.validateEmail();
    },
  },
};
</script>
