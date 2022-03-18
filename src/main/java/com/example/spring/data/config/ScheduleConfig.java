package com.example.spring.data.config;

import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduleConfig {
//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() {
//        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
//    }
    @Autowired
    MemberService memberService;
//test thu schedule job chay 10s 1 lan
    @Scheduled(fixedRate = 10000)
    public void scheduleFixedRateTask() {
        memberService.scheduleUpdateAll();
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
//    public void scheduleFixedRateWithInitialDelayTask() {
//
//        long now = System.currentTimeMillis() / 1000;
//        System.out.println(
//                "Fixed rate task with one second initial delay - " + now);
//    }
}
