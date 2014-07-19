REGISTER /usr/local/hbase/lib/protobuf-java-2.4.0a.jar
REGISTER /usr/local/hbase/hbase-0.94.13-security.jar
REGISTER /usr/local/hbase/lib/guava-11.0.2.jar
REGISTER /usr/local/hbase/lib/zookeeper-3.4.5.jar

set hbase.zookeeper.quorum 'master,slave' 

raw_data = LOAD 'hbase://population'
       USING org.apache.pig.backend.hadoop.hbase.HBaseStorage(
       'general_info:state general_info:avg_house_size general_info:median_house_value general_info:median_household_income general_info:total_population general_info:work_population general_info:educated_population')
       AS (state:chararray, avg_house_size:float,median_house_value:float, median_household_income:float, total_population:float, work_population:float, educated_population:float);

modified_data = FOREACH raw_data GENERATE state, avg_house_size, median_house_value, median_household_income, total_population, work_population/total_population as work, educated_population/total_population as education;

grp = GROUP modified_data BY state;

ttl_data = FOREACH grp GENERATE group,AVG(modified_data.avg_house_size), AVG(modified_data.median_house_value), AVG(modified_data.median_household_income), CEIL(SUM(modified_data.total_population)/6), AVG(modified_data.work), AVG(modified_data.education);


STORE ttl_data INTO 'summary' USING PigStorage(',');




