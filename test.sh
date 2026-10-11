#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out/test
find src -name '*.java' -print > out/sources.txt
javac --release 21 -Xlint:all -d out @out/sources.txt
find tests -name '*.java' -print > out/test-sources.txt
javac --release 21 -Xlint:all -cp out -d out/test @out/test-sources.txt
java -cp out:out/test com.airtribe.learntrack.LearnTrackFlowTest
