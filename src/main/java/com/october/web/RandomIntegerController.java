package com.october.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@RestController
public class RandomIntegerController {
    Logger logger = LoggerFactory.getLogger(RandomIntegerController.class);
    @GetMapping(value = "/randomInteger", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getRandomTempature() {
        Random r = new Random();
        int high = 50;
        return Flux.fromStream(Stream.generate(() -> r.nextInt(high))
                .map(s -> String.valueOf(s)))
                .map(s -> Integer.valueOf(s))
                .delayElements(Duration.ofSeconds(1));
    }
}