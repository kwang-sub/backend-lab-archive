package kr.co.billiejoe.place.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.billiejoe.place.model.service.PlaceService;

@Component
public class PlaceScheduler {
	
	@Autowired
	private PlaceService service;
	
	@Scheduled(cron = "0 0 * * * *" )
	public void updateReservation() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH", Locale.KOREA);
	    String today = sdf.format(date);
	    String time = sdf2.format(date);
	    Map<String, String> map = new HashMap<String, String>();
	    
	    map.put("today", today);
	    map.put("time", time);
	    int result = service.updateReservation(map);
	    
	}
}
