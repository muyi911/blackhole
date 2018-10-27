<template>
    <div class="b_s_window" @click="jump">this is loading!</div>

</template>

<script>
export default {
  methods: {
    jump: function() {
        this.$router.push('apps');
    }
  }
};
</script>


<style>
.b_s_window {
  background: #fff;
}
</style>
