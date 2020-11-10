package com.mitchellbosecke.benchmark;

import com.mitchellbosecke.pebble.error.PebbleException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import pro.fessional.meepo.sack.Gene;

import java.util.Map;

public class Meepo extends BaseBenchmark {

    private Map<String, Object> context;

    private Gene template;

    @Setup
    public void setup() throws PebbleException {
        template = pro.fessional.meepo.Meepo.parse("classpath:/templates/stocks.meepo.html");
        this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws PebbleException {
        return template.merge(context);
    }

}
