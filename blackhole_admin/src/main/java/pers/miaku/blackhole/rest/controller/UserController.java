package pers.miaku.blackhole.rest.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.miaku.blackhole.domain.BlackHoleUser;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.UserForm;
import pers.miaku.blackhole.rest.service.UserService;
import pers.miaku.blackhole.rest.vo.ResultVO;
import pers.miaku.blackhole.rest.vo.SmsConfigItemVO;
import pers.miaku.blackhole.rest.vo.UserInfoVO;
import pers.miaku.blackhole.util.LoginUtil;
import pers.miaku.blackhole.util.ResultVOUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 获取短信配置信息
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/login")
	public ResultVO<SmsConfigItemVO> getConfig(@Valid @RequestBody UserForm userForm, BindingResult bindingResult,
			HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			log.debug("【用户登录】参数不正确，receiverForm={}", userForm);
			throw new ApiException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
		}
		BlackHoleUser user = userService.checkAuth(userForm);
		LoginUtil.setLoginInfo(response, redisTemplate, user);
		return ResultVOUtil.success();
	}

	@PostMapping("/info")
	public ResultVO<UserInfoVO> getUserInfo() {
		System.out.println("用户信息");
		return ResultVOUtil.success();
	}
}
