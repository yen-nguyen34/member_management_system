<template>
  <nav class="flex items-center justify-between flex-wrap bg-teal-500 p-6">
    <div class="flex items-center flex-shrink-0 text-white mr-6">
      <svg class="fill-current h-8 w-8 mr-2" width="54" height="54" viewBox="0 0 54 54"
        xmlns="http://www.w3.org/2000/svg"></svg>
      <a class="font-semibold text-xl tracking-tight">{{ props.headerName ? props.headerName : 'Member Management System'
      }}</a>
    </div>
    <div>
      <button type="button" @click="logOut" class="text-white font-semibold text-xl tracking-tight items-center">
        <svg class="w-6 h-6 text-white dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
          fill="none" viewBox="0 0 14 10">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="M13 5H1m0 0 4 4M1 5l4-4" /></svg>Log Out</button>
    </div>
  </nav>
</template>

<script setup>
const props = defineProps(['headerName']);
const logOut = () => {
}
</script>