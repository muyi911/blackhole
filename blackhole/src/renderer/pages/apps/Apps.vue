<template>
    <div style="height:100%">
        <el-row>
            <el-button @click="closeApp">关闭应用</el-button>
            <el-button @click="jumpTo('frequent')">常用链接</el-button>
        </el-row>
        <router-view></router-view>
    </div>
</template>
<script>
export default {
  methods: {
    jumpTo: function(path) {
      this.$router.push(path);
    }
  }
};
</script>
