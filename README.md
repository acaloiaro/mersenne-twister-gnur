mersenne-twister-gnur
=====================

A Mersenne-Twister implementation modeled after and validated with GNU R's RNG.c.

This is the PRNG in [Renjin](https://github.com/bedatadriven/renjin), a JVM-based interpreter for R.

# Examples
## Getting Ten Random Doubles with GNU R
```
set.seed(4357);
runif(10);
output: 0.4478791 0.8817665 0.8387621 0.6334931 0.3799481 0.8501577 0.8934669 0.5206094 0.5182715 0.6667043
```

## Same Results Using mersenne-twister-gnur
```
RNG.setSeed(4357);
double[] results = RNG.runif(10);
System.out.println(Arrays.toString(results));
output:  [0.44787912303581834, 0.8817665143869817, 0.8387620681896806, 0.6334931093733758, 0.37994811101816595, 0.8501577137503773, 0.8934669292066246, 0.5206093506421894, 0.5182715305127203, 0.6667043350171298]
```
