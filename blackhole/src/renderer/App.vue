<template>
  <div id="app">
    <button @click="autoUpdate()">获取更新</button>
    <router-view></router-view>
    <command />
  </div>
</template>

<script>
import command from "@/components/command/command";

export default {
  name: "blackhole",
  components: {
    command
  },
  created() {
    const _this = this;
    _this.$electron.ipcRenderer.send("checkForUpdate");
    _this.$electron.ipcRenderer.on("message", (event, text) => {
      _this.tips = text;
      alert(text)
    });
    _this.$electron.ipcRenderer.on("downloadProgress", (event, progressObj) => {
      _this.downloadPercent = progressObj.percent || 0;
    });
    _this.$electron.ipcRenderer.on("isUpdateNow", () => {
      _this.$electron.ipcRenderer.send("updateNow");
    });
  },
  methods: {
    autoUpdate() {
      ipcRenderer.send('checkForUpdate');
    }
  }
};
</script>
