<template>
  <v-app>
    <v-main>
      <router-view :key="$route.fullPath" />
    </v-main>
  </v-app>
</template>

<script>
import '@/components/css/style.scss';

export default {
  name: 'App',
};
</script>
