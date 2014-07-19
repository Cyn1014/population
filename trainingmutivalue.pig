REGISTER /usr/local/hbase/lib/protobuf-java-2.4.0a.jar
REGISTER /usr/local/hbase/hbase-0.94.13-security.jar
REGISTER /usr/local/hbase/lib/guava-11.0.2.jar
REGISTER /usr/local/hbase/lib/zookeeper-3.4.5.jar

set hbase.zookeeper.quorum 'master,slave' 

raw_data = LOAD 'hbase://population'
       USING org.apache.pig.backend.hadoop.hbase.HBaseStorage(
       'general_info:state general_info:median_house_value general_info:median_household_income general_info:total general_info:educated_population')
       AS (state:chararray ,median_house_value:float, median_household_income:float, total_population:float, educated_population:float);

modified_data = FOREACH raw_data GENERATE state, median_house_value, median_household_income, total_population, educated_population;

STORE modified_data INTO 'training1' USING PigStorage();
