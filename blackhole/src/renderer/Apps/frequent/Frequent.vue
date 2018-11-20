<template>
    <div style="height:100%">
        <a @click="open">百度</a>
        <iframe v-if="src!=''" :src="src" frameborder="0" height="100%" width="100%" scrolling="no"></iframe>
    </div>
</template>


<script>
export default {
  data: () => ({
    src: ""
  }),
  methods: {
    open: function() {
      this.src = "http://www.baidu.com";
        


    }
  }
};
</script>

<style>
</style>
