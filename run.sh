#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out
find src -name '*.java' -print > out/sources.txt
javac --release 21 -d out @out/sources.txt
exec java -cp out com.airtribe.learntrack.Main
