package pers.miaku.blackhole.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.miaku.blackhole.dao.BlackHoleUserDao;
import pers.miaku.blackhole.domain.BlackHoleUser;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.UserForm;
import pers.miaku.blackhole.util.MD5Utils;

/**
 * 用户相关服务类
 * 
 * @author caozn
 *
 */
@Service
public class UserService {
	@Autowired
	private BlackHoleUserDao blackHoleUserDao;

	/**
	 * 用户登录验证
	 * 
	 * @param userForm
	 */
	public BlackHoleUser checkAuth(UserForm userForm) {
		String loginId = userForm.getLoginId();
		String password = userForm.getPassword();
		BlackHoleUser user = blackHoleUserDao.findOneByLoginId(loginId);
		if (user == null) {
			throw new ApiException(ResultEnum.LOGINID_OR_PASSWORD_ERROR);
		} else {
			if (!user.getPassword().equals(MD5Utils.MD5Salt(password))) {
				throw new ApiException(ResultEnum.LOGINID_OR_PASSWORD_ERROR);
			}
		}
		return user;
	}
}
