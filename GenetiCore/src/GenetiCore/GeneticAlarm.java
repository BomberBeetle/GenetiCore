/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenetiCore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Computery
 */



public class GeneticAlarm {
    private final int populationSize;
    public boolean repeat;
    public boolean[] week_repeat = new boolean[7];
    public LocalDate alarmDate;
    public LocalTime time;   
    public int individualIndex = 0;
    private final int mutationChance = 15;
    String[] soundList;
    Alarm[] population;
    
    class FitnessComparator implements Comparator<Alarm>{
    @Override
    public int compare(Alarm a, Alarm b){
        return a.fitness - b.fitness;
    }
}
    
    public GeneticAlarm(String soundList[]){
        populationSize = 5;
        repeat = false;
        population = new Alarm[populationSize];
        java.util.Arrays.fill(population, new Alarm());
        mutateAllIndividuals(soundList);
    }
    
    public void evaluateCurrentIndividual(int fitness){
        population[individualIndex].fitness = fitness;
        individualIndex++;
        if(individualIndex >= populationSize){
            individualIndex = 0;
            reproduce(soundList);
        }
    }
    
    private void reproduce(String[] soundList){
        
        /*
        
        SELECTION
        
        */
        Alarm parent1;
        Alarm parent2;
        //get total weight
        int totalFitness = 0;
        for(Alarm a : population){
            totalFitness += a.fitness;
        }
        
        //select parent 1
        int randomIndex = -1;
        double random = Math.random();
        for (int i = 0; i < population.length; ++i)
            {
            random -= population[i].fitness ;
            if(random <= 0.0d);
                {
                randomIndex = i;
                break;
                }
            }   
        
        //re-calculate total weight without parent 1
        totalFitness = 0;
        for(int i = 0; i < population.length; ++i){
            if(i != randomIndex){
            totalFitness += population[i].fitness;
            }
        }
        parent1 = population[randomIndex];
        
        //select parent 2
        int randomIndex2 = -1;
        random = Math.random();
        for (int i = 0; i < population.length; ++i)
            {
            if (i != randomIndex){
            random -= population[i].fitness ;
                if(random <= 0.0d);
                {
                    randomIndex2 = i;
                    break;
                }
                }   
            }
        parent2 = population[randomIndex2];
       
        /*
        
        CROSSOVER
        
        */
        Alarm child1 = parent1;
        Alarm child2 = parent2;
        
        
        if(Math.random() > 0.5){
            String buffer = child1.mp3_filename;
            child1.mp3_filename = child2.mp3_filename;
            child2.mp3_filename = buffer;
        }
        
        if(Math.random() > 0.5){
            int buffer;
            buffer = child1.mp3_volume;
            child1.mp3_volume = child2.mp3_volume;
            child2.mp3_volume = buffer;
        }
        
        /*
        
        MUTATION
        
        */
        
        child1 = mutateIndividual(child1, soundList);
        child2 = mutateIndividual(child2, soundList);
        
        /*
        
        REPLACEMENT
        
        */
        Alarm[] populationBuffer = population;
        Arrays.sort(populationBuffer, new FitnessComparator());
        populationBuffer[0] = child1;
        populationBuffer[1] = child2;
        ArrayList<Alarm> pBufferList = new ArrayList<>();
        pBufferList.addAll(Arrays.asList(populationBuffer));
        Collections.shuffle(pBufferList);
        population = (Alarm[])pBufferList.toArray();
    }
    private Alarm mutateIndividual(Alarm toMutate, String[] soundList)
    {
       Random r = new Random();
       if(r.nextInt(100) <= mutationChance){
        toMutate.mp3_filename = soundList[r.nextInt(soundList.length)];
       }
       
      toMutate.mp3_volume += r.nextInt(20) - 10;
       
       if(toMutate.mp3_volume > 100){
          toMutate.mp3_volume = 100;
       }
       
       else if(toMutate.mp3_volume < 20){
           toMutate.mp3_volume = 20;
       }
       return toMutate;
    }
    
    private void mutateAllIndividuals(String[] soundList){
        for(int i = 0; i < populationSize; i++){
            population[i] = mutateIndividual(population[i], soundList);
        }
    }
}



