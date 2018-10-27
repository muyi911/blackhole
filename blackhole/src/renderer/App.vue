<template>
  <div id="app">
    <router-view></router-view>
    <command />
  </div>
</template>

<script>
  import command from '@/components/command/command';

  export default {
    name: 'blackhole',
    components: {
      command
    }
  }
</script>

<style>
  /* CSS */
</style>
