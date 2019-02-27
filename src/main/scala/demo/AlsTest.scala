package demo
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.mllib.recommendation.Rating
/**
  * //@Author: fansy 
  * //@Time: 2019/2/26 22:12
  * //@Email: fansy1990@foxmail.com
  */
object AlsTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    val data = sc.textFile("ratings.dat")
    val ratings = data.map(_.split("::") match { case Array(user, item, rate,ts) =>
      Rating(user.toInt, item.toInt, rate.toDouble)
    })

    // Build the recommendation model using ALS
    ratings.cache
    val rank = 10
    val numIterations = 10
    println(new java.util.Date()+": before model train...")
    val model = ALS.train(ratings, rank, numIterations, 0.01)
    println(new java.util.Date()+": after model train...")
    println(model.recommendProductsForUsers(10).count)
    println(model.recommendProductsForUsers(10).first)
  }
}
