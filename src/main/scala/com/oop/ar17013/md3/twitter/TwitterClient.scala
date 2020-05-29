package com.oop.ar17013.md3.twitter

import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.Status

object TwitterClient extends App {

    System.setProperty("twitter4j.oauth.consumerKey", properties.getProperty("consumer_key"))
    System.setProperty("twitter4j.oauth.consumerSecret", properties.getProperty("consumer_secret"))
    System.setProperty("twitter4j.oauth.accessToken", properties.getProperty("access_token"))
    System.setProperty("twitter4j.oauth.accessTokenSecret", properties.getProperty("accessToken_secret"))

    val ssc = new StreamingContext("local[*]", "PopularHashtags", Seconds(1))

    ssc.sparkContext.setLogLevel("ERROR")

    val tweets : ReceiverInputDStream[Status] = TwitterUtils.createStream(ssc, None)

    val statuses = tweets.map(_.getText)

    val tweetWords = statuses.flatMap(_.split(" "))

    val hashtags = tweetWords.filter(_.startsWith("#")).map((_, 1))

    val hashtagCounts = hashtags.reduceByKeyAndWindow( (x: Int, y: Int) => x + y, Seconds(300), Seconds(1))

    val hashtagsWithCountsSorted = hashtagCounts.transform(rdd => {
        rdd.sortBy(_._2, false)
    })

    hashtagsWithCountsSorted.print(20)

    ssc.checkpoint("checkpoint")
    ssc.start()
    ssc.awaitTermination()


}
