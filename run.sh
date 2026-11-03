#!/bin/sh
set -e
rm -rf out
javac -d out $(find src -name "*.java")
java -cp out textanalysis.TestRunner
java -Xms512m -cp out textanalysis.bench.Benchmark results
python3 scripts/plot.py results plots
