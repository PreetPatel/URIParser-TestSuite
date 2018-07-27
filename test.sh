#!/bin/bash

JUNIT_CLASSPATH="./lib/hamcrest-core-1.3.jar:./lib/junit-4.12.jar"

printf "Compiling test suite..."

if ! out="$(javac -cp ./lib/good.jar:./lib/junit-4.12.jar -d bin/ 2>&1 ./src/uriparser/TestURIParser.java)"; then
    tput setaf 1
    echo "error"
    tput sgr0
    echo "$out" >&2 
    exit 1
fi

tput setaf 2
echo "ok"
tput sgr0

good_count=0
good_passed=0
exit_code=0


for jar in lib/bad*.jar; do
    printf "Testing $jar..."
    java -cp "$JUNIT_CLASSPATH":"$jar":bin/ org.junit.runner.JUnitCore uriparser.TestURIParser;
done 

bad_count=0
bad_passed=0

for jar in lib/bad*.jar; do
    bad_count=$((bad_count + 1))
    printf "Testing $jar..."
    if out="$(java -cp "$JUNIT_CLASSPATH":"$jar":bin/ org.junit.runner.JUnitCore uriparser.TestURIParser;)"; then
        tput setaf 1
        echo "error: all tests passed"
        tput sgr0
        exit_code=1
    else
        bad_passed=$((bad_passed + 1))
        tput setaf 2
        echo "ok"
        tput sgr0
    fi
done 
echo "-------------------------------------Running good code-----------------------------------"
for jar in lib/good*.jar; do
    good_count=$((good_count + 1))
    printf "Testing $jar..."
    if ! out="$(java -cp "$JUNIT_CLASSPATH":"$jar":bin/ org.junit.runner.JUnitCore uriparser.TestURIParser)"; then
        tput setaf 1
        echo "error: a test failed, see below"
        tput sgr0
        echo "$out" >&2
        exit_code=1
    else
        good_passed=$((good_passed + 1))
        tput setaf 2
        echo "ok"
        tput sgr0
    fi
done
tput bold
echo "[$good_passed/$good_count] good jars passed all tests"
echo "[$bad_passed/$bad_count] bad jars failed at least one test"
tput sgr0


exit $exit_code
