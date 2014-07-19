REGISTER /usr/local/hbase/lib/protobuf-java-2.4.0a.jar
REGISTER /usr/local/hbase/hbase-0.94.13-security.jar
REGISTER /usr/local/hbase/lib/guava-11.0.2.jar
REGISTER /usr/local/hbase/lib/zookeeper-3.4.5.jar

set hbase.zookeeper.quorum 'master,slave' 

raw_data = LOAD 'hbase://population'
       USING org.apache.pig.backend.hadoop.hbase.HBaseStorage(
       'general_info:state general_info:median_household_income')
       AS (state:chararray, median_household_income:float);

grp = GROUP raw_data BY state;

ttl_data = FOREACH grp GENERATE group,AVG(raw_data.median_household_income);


STORE ttl_data INTO 'testing' USING PigStorage();
