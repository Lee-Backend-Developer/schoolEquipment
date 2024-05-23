package com.equipment.school_equipment.scheduler;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class TodayEquipmentScheduled {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E");

	@Scheduled(fixedDelay = DelayTime.SECOND, zone = "Asia/Seoul")
	public void reportCurrentTime() {
		String month = dateFormat.format(new Date()) + "요일";
		String week = DayOfWeekEnum.getName(month).name();
		log.info("The time is now {}", week);
	}
}