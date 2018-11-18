package pers.miaku.blackhole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.miaku.blackhole.domain.SmsReceiver;

public interface SmsReceiverDao extends JpaRepository<SmsReceiver, String> {


}
