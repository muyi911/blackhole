package pers.miaku.blackhole.util;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.StringRedisTemplate;

import pers.miaku.blackhole.constant.CookieConstant;
import pers.miaku.blackhole.constant.RedisConstant;
import pers.miaku.blackhole.domain.BlackHoleUser;

public class LoginUtil {
	/**
	 * 登录后记录用户信息
	 * 
	 * @param response
	 * @param user
	 */
	public static void setLoginInfo(HttpServletResponse response, StringRedisTemplate redisTemplate,
			BlackHoleUser user) {
		String userId = user.getId();

		// 1、生成token
		String token = UUID.randomUUID().toString(); // TODO 这里以后需要改下规则的

		// 2、设置redis
		Integer expire = RedisConstant.EXPIRE;
		redisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX + "_" + token, userId, expire, TimeUnit.SECONDS);

		// 3、设置到cookie
		CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
		CookieUtil.set(response, CookieConstant.USREID, userId, CookieConstant.EXPIRE);
	}
}
