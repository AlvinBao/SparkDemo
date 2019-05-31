#!/usr/bin/env bash

spark=$FIREWORK_HOME/bin/spark-submit
cur_dir=`dirname $0`
jar=$cur_dir/target/request-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar
execution_date="2019-05-28"
$spark --spark-version 2.1.2-Scala2.11 \
       --master yarn \
       --deploy-mode cluster \
       --queue spark \
       --num-executors 5 \
       --conf spark.driver.cores=4 \
       --conf spark.driver.memory=4g \
       --conf spark.executor.cores=4 \
       --conf spark.executor.memory=16g \
       --conf spark.execution.date=$execution_date \
       --class com.hulu.reco.analyzer.main.RequestParseMain\
       $jar

