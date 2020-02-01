// Databricks notebook source

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import twitter4j.conf.ConfigurationBuilder
import twitter4j.auth.OAuthAuthorization
import twitter4j.Status
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming._

// COMMAND ----------

// MAGIC %md ###create streaming session

// COMMAND ----------

val ssc = new StreamingContext(sc, Seconds(10)) 

// values of Twitter API.
val consumerKey = "xxxxxxxxxxxxxxxxxxx" // Your consumerKey 
val consumerSecret = "xxxxxxxxxxxxxxxxxxx" // your API secret
val accessToken ="xxxxxxxxxxxxxxxxxxx" // your access token
val accessTokenSecret = "xxxxxxxxxxxxxxxxxxx" // your token secret

//Connection to Twitter API
val cb = new ConfigurationBuilder


// COMMAND ----------

cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret)

val auth = new OAuthAuthorization(cb.build)
val tweets = TwitterUtils.createStream(ssc, Some(auth)) 



// COMMAND ----------

val englishTweets = tweets.filter(_.getLang =="en")
   // Now extract the text of each status update into RDD's using map()


// COMMAND ----------

val statuses = englishTweets.map(status => status.getText()).filter(tag => tag.contains("#deltalake") || tag.contains("spark") || tag.contains("#databricks") ||tag.contains("@databricks") || tag.contains("@ApacheSpark") || tag.contains("@jaceklaskowski") || tag.contains(" #koalas") || tag.contains("#ApacheSpark") ||tag.contains("@matei_zaharia") || tag.contains("scala") ||tag.contains("#bigdata"))

statuses.print()


  

// COMMAND ----------

  ssc.start()
  ssc.awaitTermination()

// COMMAND ----------

ssc.stop()

// COMMAND ----------


