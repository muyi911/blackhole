<template>
  <div id="command_window" @focusout="focusout">
    <div class="command_window_input">
      <input type="text" v-model="command" id="command_window_line" @keyup.enter="run" placeholder="Enter Command">
    </div>
  </div>
</template>

<script>
let win = require("electron").remote.getCurrentWindow();
export default {
  data: () => ({
    command: ""
  }),
  created: function() {
    var self = this;

    document.onkeydown = function(e) {
      // 退出快捷键
      if ("27" == e.keyCode) {
        self.hide();
      }
    };

    self.$electron.ipcRenderer.on("showCommandWindow", () => {
      self.show();
    });

    self.$electron.ipcRenderer.on("hideCommandWindow", () => {
      self.command = "";
    });
  },
  mounted: function() {
    // 实现窗口穿透点击
    let el = document.getElementById("command_window");
    el.addEventListener("mouseleave", () => {
      win.setIgnoreMouseEvents(true, { forward: true });
    });
    el.addEventListener("mouseenter", () => {
      win.setIgnoreMouseEvents(false);
    });
  },
  methods: {
    focusout: function() {},
    show: function() {
      this.$nextTick(function() {
        document.getElementById("command_window_line").focus();
      });
    },
    hide: function() {
      this.command = "";
      this.$electron.ipcRenderer.send("hideCommandWindow");
    },
    run: function() {
      var self = this;
      var patt = /open */;
      if (patt.test(self.command)) {
        var routeName = self.command.substring(5, self.command.length);
        self.$router.push(routeName);
      }
    }
  }
};
</script>

<style>
#command_window .command_window_input {
  width: 600px;
  margin: 0 auto;
}

#command_window .command_window_input input {
  border: 0;
  padding: 5px 20px;
  width: 100%;
  caret-color: rgb(226, 223, 223);
  color: #fff;
  font-size: 32px;
  outline: none;
}

.command_window_input input {
  background: #000;
  height: 60px;
  line-height: 60px;
  border-radius: 6px;
  /* opacity: 0.8; */
}
</style>

