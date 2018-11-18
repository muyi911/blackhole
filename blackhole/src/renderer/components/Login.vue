<template>
    <div class="login">
        <div class="login_box" @click="changeLogin">
            <el-form ref="form" :model="form" :rules="rules">
                <el-form-item prop="loginid">
                    <el-input v-model="form.loginid" placeholder="用户名" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" v-model="form.password" placeholder="密码" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login('form')" class="login_btn">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      console.log(value);
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      form: {
        loginid: "",
        password: ""
      },
      rules: {
        loginid: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ validator: validatePass, trigger: "blur" }]
      }
    };
  },
  created: function() {},
  methods: {
    changeLogin: function() {
      console.log(this.$store.getters.isLogin);
      console.log(this.$store);
      this.$store.dispatch("changeLogin");
      console.log(this.$store.getters.isLogin);
    },
    login: function(formName) {
      var self = this;
      self.$refs[formName].validate(valid => {
        if (valid) {
          http
            .apiPost("/user/login", {
              loginid: self.form.loginid,
              password: self.form.password
            })
            .then(res => {
              http.handleResponse(
                res,
                function(data) {
                    _g.message('success', '登录成功')
                  //self.changeLogin();
                },
                function(res) {}
              );
            });
        }
      });
    }
  }
};
</script>


<style>
.login {
  background: #fafafa;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: Center;
}

.login_box {
  width: 250px;
}

.login_btn {
  width: 100%;
  background: #000;
  border-color: #000;
  font-weight: 700;
  letter-spacing: 10px;
}

.login_btn:hover,
.login_btn:active,
.login_btn:focus {
  background-color: #303133;
  border-color: #303133;
}

.login .el-form-item__content {
  margin-left: 0 !important;
}

.login .el-form-item.is-success .el-input__inner {
  border-color: #dcdfe6;
}
</style>
