# Assignment 4: Text Analysis with DP and String Algorithms

Java implementation of LCS, Edit Distance, Naive / KMP / Rabin-Karp string matching and Kasai's LCP array, with tests and benchmarks.

## How to run

Needs JDK 17+ (tested on 21). Plots need Python 3 with matplotlib.

```bash
./run.sh
```

Or step by step:

```bash
javac -d out $(find src -name "*.java")
java -cp out textanalysis.TestRunner
java -Xms512m -cp out textanalysis.bench.Benchmark results
python3 scripts/plot.py results plots
```

## Project structure

```
src/main/java/textanalysis/
  dp/         Lcs, EditDistance
  matching/   Matcher, NaiveMatcher, KmpMatcher, RabinKarpMatcher
  suffix/     SuffixArray, Kasai
  bench/      InputGenerator, Timer, DpExperiment, MatchingExperiment, KasaiExperiment, Benchmark
src/test/java/textanalysis/   tests + small Check helper and TestRunner
scripts/plot.py               builds plots from results/*.csv
results/                      csv output of the benchmark
plots/                        png plots
```
