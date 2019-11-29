/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenetiCore;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Computery
 */
public class SimpleAlarm extends Alarm {
    public LocalDate alarmDate;
    public boolean repeat; //set if the alarm is repeating. if set to TRUE, week_repeat will be used to tell when the alarm will trigger. If not, it will be dictated by alarmDate.
    public boolean[] week_repeat = new boolean[7];
    public LocalTime time; //time for the alarm to be triggered.
    
    public SimpleAlarm()
    {
        super();
        time = LocalTime.now();
        repeat = false;
        java.util.Arrays.fill(week_repeat, false);
        LocalDate dateBuffer = LocalDate.now();
        alarmDate = dateBuffer.plusDays(1);
    }
    
    public SimpleAlarm(String mp3_filename, int mp3_volume, LocalTime time, boolean repeat, boolean week_repeat[], LocalDate alarmDate ){
        super(mp3_filename, mp3_volume);
        this.time = time;
        this.repeat = repeat;
        this.week_repeat = week_repeat;
        this.alarmDate = alarmDate;
    }
    
}
