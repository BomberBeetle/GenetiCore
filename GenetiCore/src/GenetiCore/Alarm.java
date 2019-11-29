/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenetiCore;
/**
 *
 * @author Computery
 */
public class Alarm {
    
    public int fitness; //value that describes how effective a given alarm is
    public String mp3_filename; //file name for the alarm sound.
    public int mp3_volume; //volume for the alarm sound.
    
    
    public Alarm(){
        fitness=0;
        mp3_filename = "MP3_NOTSET";
        mp3_volume = 100;
    }
    
    public Alarm(String mp3_filename, int mp3_volume){
        
        this.mp3_filename = mp3_filename;
        this.mp3_volume = mp3_volume;
        
    }
}
