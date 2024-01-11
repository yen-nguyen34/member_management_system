<template>
  <HeaderView :headerName="headerName"></HeaderView>
  <router-view></router-view>
</template>

<script setup>
import { useRoute } from 'vue-router';
import HeaderView from './components/Header.vue';
import { onMounted, ref, watch } from 'vue'
import { initFlowbite } from 'flowbite'

const route = useRoute()
const  headerName = ref('')


watch(() => route.path, (curPath, prevPath) => {
  if (route.params == '/members') {
    headerName.value = 'Member Management System'
  } else {
    headerName.value = 'Member Management System'
  }
})

</script>
