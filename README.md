template-benchmark
================

JMH benchmark for popular Java template engines:

* [Meepo](https://gitee.com/trydofor/pro.fessional.meepo)
* [Freemarker](http://freemarker.org/)
* [Mustache](https://github.com/spullara/mustache.java)
* [Pebble](http://www.mitchellbosecke.com/pebble)
* [Rocker](https://github.com/fizzed/rocker)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Trimou](http://trimou.org/)
* [Velocity](http://velocity.apache.org/)

Result in Precision 5510, 2020-11-11
====================================

```
Result "com.mitchellbosecke.benchmark.Velocity.benchmark":
  18956.594 ±(99.9%) 766.578 ops/s [Average]
  (min, avg, max) = (12635.749, 18956.594, 20754.686), stdev = 1548.525
  CI (99.9%): [18190.016, 19723.172] (assumes normal distribution)
# Run complete. Total time: 01:27:47
```

| Benchmark            | Mode  | Cnt |       Score |    Error | Units |
|:---------------------|:------|:----|------------:|---------:|:------|
| Meepo.benchmark      | thrpt | 50  | 24177.507 ± |  493.546 | ops/s |
| Freemarker.benchmark | thrpt | 50  | 18152.915 ± |  928.830 | ops/s |
| Mustache.benchmark   | thrpt | 50  | 22565.064 ± |  154.915 | ops/s |
| Pebble.benchmark     | thrpt | 50  | 34311.017 ± |  248.283 | ops/s |
| Rocker.benchmark     | thrpt | 50  | 37499.123 ± | 1275.888 | ops/s |
| Thymeleaf.benchmark  | thrpt | 50  |  5406.186 ± |  177.424 | ops/s |
| Trimou.benchmark     | thrpt | 50  | 19718.903 ± |  669.759 | ops/s |
| Velocity.benchmark   | thrpt | 50  | 18956.594 ± |  766.578 | ops/s |


Running the benchmark
======================

1. Download the source code and build it (`mvn clean install`)
2. Run the entire benchmark suite with `java -jar target/benchmarks.jar`
3. (Optional) To run a single benchmark, such as Mustache, use `java -jar target/benchmarks.jar Mustache`

Generating plot
===============
1. Run benchmark while exporting results to csv with `java -jar target/benchmarks.jar -rff results.csv -rf csv`
2. Use gnuplot to generate plot with `gnuplot benchmark.plot`. This will output `results.png`.

Rules of Template Engine Configuration
======================================
It is imperative that each template engine is configured in way to reflect real-world usage as opposed to it's *optimal* configuration. Typically this means an out-of-the-box configuration.

To strive for a common set of features across template engines, the following configurations are expected:
* Disabling of HTML escaping
* Template loaded from classpath prior to actual benchmark

Interpreting the Results
========================
The benchmarks measure throughput, given in "ops/time". The time unit used is seconds.
Generally, the score represents the number of templates rendered per second; the higher the score, the better.

Example Results
===============

![Template Comparison](results.png)
