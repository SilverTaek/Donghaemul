<template>
  <div class="socialLogin">
    <v-dialog v-model="dialog" width="320">
      <template v-slot:activator="{ on, attrs }">
        <v-btn class="socialLoginButton" height="63" v-bind="attrs" v-on="on">
          다른 방법으로 시작하기
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="title">
          다른 방법으로 시작하기
        </v-card-title>

        <div class="socialLoginButton">
          <Google @click="loginWithGoogle" />
          <Kakao @click="loginWithKakao" />
        </div>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="dialog = false" class="closeButton">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-alert :value="showError" type="error" class="error">
      {{ errorMessage }}
    </v-alert>
  </div>
</template>
<script>
import { Google, Kakao } from '@/assets/index.js';
import firebase from 'firebase';
import '@firebase/auth';
import '@firebase/firestore';

export default {
  name: 'SocialLogin',
  components: {
    Google,
    Kakao,
  },
  data() {
    return {
      dialog: false,
      showError: false,
      errorMessage: '', // 에러메세지
    };
  },
  methods: {
    loginWithGoogle: function() {
      const provider = new firebase.auth.GoogleAuthProvider();
      firebase.auth().languageCode = 'ko';
      firebase
        .auth()
        .signInWithPopup(provider)
        .then((result) => {
          this.id = result.user.email;
          this.$axios({
            url: '/members/social',
            method: 'POST',
            data: {
              memberEmail: result.user.email,
              memberPassword: null,
              memberName: result.user.displayName,
              memberPlatformType: 'Google',
            },
          })
            .then((response) => {
              const token = response.data.accesstoken;
              this.$store.commit('LOGIN', token);
              this.$router.push({ name: 'Main' });
            })
            .catch((error) => {
              console.error(error);

              // error alert 출력
              this.showError = true;
              this.errorMessage = '로그인에 실패했습니다.';

              setTimeout(() => {
                this.showError = false;
              }, 5000);
            });
        });
    },
    loginWithKakao: function() {
      window.Kakao.Auth.login({
        scope: 'profile, account_email',
        success: this.getInfoWithKaKao,
      });
    },
    getInfoWithKaKao: function() {
      window.Kakao.API.request({
        url: '/v2/user/me',
        success: async (res) => {
          const kakaoAccount = res.kakao_account;
          if (kakaoAccount.email === null) {
            alert(
              '카카오에 이메일을 등록하지 않아서 로그인에 실패했습니다. 다른 방법으로 로그인해주세요.'
            );
          } else {
            this.$axios({
              url: '/members/social',
              method: 'POST',
              data: {
                memberEmail: kakaoAccount.email,
                memberPassword: null,
                memberName: kakaoAccount.profile.nickname,
                memberPlatformType: 'Kakao',
              },
            })
              .then((response) => {
                const token = response.data.accesstoken;
                this.$store.commit('LOGIN', token);
                this.$router.push({ name: 'Main' });
              })
              .catch((error) => {
                console.error(error);

                // error alert 출력
                this.showError = true;
                this.errorMessage = '로그인에 실패했습니다.';

                setTimeout(() => {
                  this.showError = false;
                }, 5000);
              });
          }
        },
      });
    },
  },
};
</script>
