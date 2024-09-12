package com.equipment.school_equipment.scheduler;

import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.service.TodayRentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class TodayEquipmentScheduled {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E");
	private final TodayRentalService todayRentalService;

	// 주말을 제외하고 1분마다 실행 (월요일~금요일)
	@Scheduled(cron = "0 */1 * * * MON-FRI", zone = "Asia/Seoul")
	public void reportCurrentTime() {
		String currentWeekday = dateFormat.format(new Date()) + "요일";
		DayOfWeekEnum weekday = DayOfWeekEnum.getName(currentWeekday);
		log.info("The time is now {}", weekday);
		todayRentalService.createRentalFromToday(weekday);
	}
}