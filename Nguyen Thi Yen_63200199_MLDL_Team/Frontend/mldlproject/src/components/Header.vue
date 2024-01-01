<template>
    <nav class="flex items-center justify-between flex-wrap bg-teal-500 p-6">
 <div class="flex items-center flex-shrink-0 text-white mr-6">
   <svg class="fill-current h-8 w-8 mr-2" width="54" height="54" viewBox="0 0 54 54" xmlns="http://www.w3.org/2000/svg"></svg>
   <span class="font-semibold text-xl tracking-tight">{{props.headerName?props.headerName:'Member Management System'}}</span>
 </div>
</nav>
</template>

<script setup>
const props = defineProps(['headerName'])
</script>