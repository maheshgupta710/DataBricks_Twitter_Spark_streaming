
name := "Twitter_spark_analysis"

version := "0.1"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-sql_2.11" % "2.4.3",
  "org.apache.spark" % "spark-core_2.11" % "2.4.3",
  "org.apache.spark" % "spark-streaming_2.11" % "2.4.3",
  "org.apache.bahir" % "spark-streaming-twitter" % "2.4.3" 
)

