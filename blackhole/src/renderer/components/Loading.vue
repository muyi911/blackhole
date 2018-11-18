<template>
    <div class="loading_page">
      <div class="loading_content">
        <img class="loading_page_img" src="/static/loading/loading.gif" alt="">
        <h1 class="loading_tips text-light" v-text="showTips"></h1>
      </div>
    </div>
</template>

<script>
export default {
  data: () => ({
    tipsArray: [
      "加载中 (╭￣3￣)╭♡ ",
      "不要着急 ~( ˘･з･)~ ",
      "真的快好了 (✪ω✪) "
    ],
    showTips: ""
  }),
  created: function() {
    var i = 0;
    var timeOut = 2000;
    var self = this;
    self.showTips = self.tipsArray[i++];
    // setTimeout(function() {
    //   self.showTips = self.tipsArray[i++];
    //   setTimeout(function() {
    //     self.showTips = self.tipsArray[i];
    //   }, timeOut);
    // }, timeOut);

    if (self.$store.getters.isLogin == false) {
      self.jumpTo("login");
    } else {
      //self.jumpTo("apps");
    }

    // TODO 验证用户权限
  },
  methods: {
    jumpTo: function(path) {
      this.$router.push(path);
    }
  }
};
</script>

<style>
.loading_page {
  background: #fff;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: Center;
}

.loading_page_img {
  width: 300px;
  height: 300px;
}

.loading_tips {
  font-size: 20px;
  font-weight: 100;
  text-align: center;
  margin-top: 15px;
  line-height: 32px;
  color: #c0c4cc;
}
</style>
