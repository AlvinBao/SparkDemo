#!/usr/bin/env bash

date="2019-05-28"
# config
working_dir=`echo ~`
index="fastscore-request-$date"
url="https://search-fastscore-elk-3kzdgbwljq5uuidkfpneezmqie.us-west-2.es.amazonaws.com/$index"
dir=$working_dir/fastscore-requests/$index
data_file="data.json"
table="reco.fastscore_requests_raw"
rm -rf $dir
mkdir -p $dir

# dump from es to local disk
sudo docker run --rm -i -v $dir:/tmp taskrabbit/elasticsearch-dump \
--input=$url \
--output=/tmp/$data_file \
--type=data

# upload from local disk to hive
$FIREWORK_HOME/bin/hive -e "LOAD DATA LOCAL INPATH '$dir/$data_file' OVERWRITE INTO TABLE $table PARTITION(ds='$date')"

# remove data in local disk because the data is huge
rm -f $dir/$data_file
