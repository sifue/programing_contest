object DepthFirstSearch {
  def main(args: Array[String]) {
    val n = 4
    val a = List(1, 2, 4, 7)
    val k = 13

    def search(i:Int, sum:Int):Boolean = {
      println("%1$s, %2$s".format(i, sum))
      if (n == i) return sum == k
      if (search(i + 1, sum + a(i))) true
      if (search(i + 1, sum)) true
      false
    }
    println(search(0, 0))
  }
}
