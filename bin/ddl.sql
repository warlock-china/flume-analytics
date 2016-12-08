drop table if exists baby_bi.visitor_analysis;
CREATE TABLE baby_bi.visitor_analysis(
source string,
ugc_type string,
ugc_id string,
uniqueid string,
birthday string,
user_id string,
ts string
)
PARTITIONED BY ( 
dt string comment '分区日期')
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n' 
STORED AS TEXTFILE;