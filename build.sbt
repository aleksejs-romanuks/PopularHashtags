name := "PopularHashtags"

version := "0.1"

scalaVersion := "2.11.11"


val sparkVersion = "2.3.2"


libraryDependencies ++= Seq(
    "org.apache.bahir"  %%  "spark-streaming-twitter"   % sparkVersion,
    "org.apache.spark"  %%  "spark-streaming"           % sparkVersion
)