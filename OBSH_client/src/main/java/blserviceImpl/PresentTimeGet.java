package blserviceImpl;

import java.sql.Timestamp;
import java.util.Date;

public class PresentTimeGet {
	public Timestamp NowTime(){
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		return now;
	}
}
