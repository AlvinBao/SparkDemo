create table if not exists reco.fastscore_requests_parsed(
log_id string,
user_id int,
profile_id bigint,
packages string,
device_id int,
request_id string,
request_url_length int,
request_url string,
request_url_parsed string,
request_path string,
request_params string,
timestamp bigint,
hostname string,
system_name string,
results_count int,
results_info_length bigint,
results string,
response_milli_seconds bigint
)
partitioned by (ds string);