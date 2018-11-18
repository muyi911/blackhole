package pers.miaku.blackhole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.miaku.blackhole.domain.BlackHoleUser;

public interface BlackHoleUserDao extends JpaRepository<BlackHoleUser, String> {
	BlackHoleUser findOneByLoginId(String loginId);
}
