package com.ray.springInActionDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2017/6/4.
 */

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {

    @Bean
    public BlankDisc sgtPeppers(){
        BlankDisc cd = new BlankDisc();
        cd.setTitle("Sgt. Pepper's Lonely Hearts Club Band");
        cd.setArtist("The Beatles");
        List<String> tracks = new ArrayList<>();
        tracks.add("Sgt. Pepper's Lonely");
        tracks.add("With a Little Help");
        tracks.add("Lucy in the Sky");
        tracks.add("Getting Better");

        cd.setTracks(tracks);
        return cd;
    }

    @Bean
    public TrackCounter trackCounter(){
        return new TrackCounter();
    }


}
