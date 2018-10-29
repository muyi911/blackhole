<template>
    <div id="command_window" @focusout="focusout">
        <div v-show="showCommand" class="transition-box">
            <div class="command_window_input">
                <input type="text" v-model="command" id="command_window_line" @keyup.enter="run" placeholder="Enter Command">
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data: () => ({
    showCommand: false,
    command: ''
  }),
  created: function() {
    var self = this;

    if (self.$route.name == "commandwindow") {
      self.showCommand = true;
    }

    document.onkeydown = function(e) {
      // 开启快捷键
      if ("80" == e.keyCode && e.ctrlKey && e.shiftKey) {
        self.showCommand = true;
      }
      
      // 退出快捷键
      if ("27" == e.keyCode) {
        self.showCommand = false;
      }
    };
  },
  methods: {
    transitionComplete: function() {
      if (this.showCommand) {
        document.getElementById("command_line").focus();
      }

      if (!this.showCommand) {
        this.command = "";
      }
    },
    focusout: function() {
      this.showCommand = false;
    },
    run: function() {
      var self = this;
      var isCommand = true;
      var patt = /open */;
      if (patt.test(self.command)) {
        var routeName = self.command.substring(5, self.command.length);
        self.$router.push(routeName);
      } else {
        isCommand = false;
      }

      if (isCommand) {
        self.showCommand = false;
      }
    }
  }
};
</script>

<style>
#command_window {
}

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
  opacity: 0.8;
}
</style>

