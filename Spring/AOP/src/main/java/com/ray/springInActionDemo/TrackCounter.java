package com.ray.springInActionDemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 2017/6/4.
 */
@Aspect
public class TrackCounter {

    private Map<Integer, Integer>  trackCounts = new HashMap<>();

    @Pointcut("execution(* com.ray.springInActionDemo.BlankDisc.play(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {}

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber) {
        int currentCount = trackCounts.getOrDefault(trackNumber, 0);
        trackCounts.put(trackNumber, currentCount + 1);
    }

}
