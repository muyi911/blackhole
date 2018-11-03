<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: "blackhole",
  created() {
    const _this = this;
    _this.$electron.ipcRenderer.send("checkForUpdate");
    _this.$electron.ipcRenderer.on("message", (event, text) => {
      _this.tips = text;
    });
    _this.$electron.ipcRenderer.on("downloadProgress", (event, progressObj) => {
      _this.downloadPercent = progressObj.percent || 0;
    });
    _this.$electron.ipcRenderer.on("isUpdateNow", () => {
      _this.$electron.ipcRenderer.send("updateNow");
    });
  },
  methods: {}
};
</script>
