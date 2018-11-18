package pers.miaku.blackhole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.miaku.blackhole.domain.SmsConfig;

public interface SmsConfigDao extends JpaRepository<SmsConfig, String> {
}
