import scala.util.Random

object FoodChainQuestMaker {
  def main(args: Array[String]) {
    for (x <- 1 to 1000) {
      val rand = new Random

      val info = "(%d, %d, %d),".format(
        rand.nextInt(2) + 1,
        rand.nextInt(10100) + 1,
        rand.nextInt(10100) + 1
      )
      print(info)

    }
  }
}
