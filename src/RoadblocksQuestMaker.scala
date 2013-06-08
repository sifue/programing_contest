import scala.util.Random
import scala.collection.mutable

object RoadblocksQuestMaker {
  def main(args: Array[String]) {

    val createdSet: mutable.Set[Set[Int]] = mutable.Set()
    val max = 500
    val rand = new Random
    var count = 0

    for (from <- 1 to max) {

      val printTryCount = rand.nextInt(2) + 2
      for (count <- 1 to printTryCount ) {
        printEdge(from, rand.nextInt(5) + 1 + from)
      }

      // 既に作ったものに含まれてなかったら印字して追加
      def printEdge(from: Int, to: Int) {
        if(to <= max && from != to && !createdSet.contains(Set(to, from))){

          val info = "(%d, %d, %d),".format(
            from,
            to,
            rand.nextInt(400) + 100
          )
          print(info)
          count += 1
          createdSet += Set(to, from)
        }
      }

    }

    println("\n\ncount = " + count)
    println("max = " + max)
  }
}
