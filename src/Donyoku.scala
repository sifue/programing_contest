import scala.collection.mutable

object Donyoku {
  case class Work (start:Int, terminal:Int)
  def main(args: Array[String]) {
    val listStart = List(19, 34, 5, 39, 6, 17, 43, 13, 33, 6, 25, 10, 3, 32, 30, 25, 23, 20, 38, 31, 39, 10, 34, 47, 49, 37, 19, 28, 20, 17, 22, 20, 40, 0, 20, 6, 49, 46, 0, 23, 41, 13, 0, 14, 49, 41, 36, 46, 3, 28, 27, 29, 22, 37, 47, 16, 31, 14, 30, 27, 35, 49, 0, 44, 40, 49, 41, 1, 6, 39, 37, 4, 39, 10, 31, 3, 37, 18, 47, 13, 47, 33, 4, 0, 34, 36, 42, 17, 1, 38, 30, 35, 8, 5, 1, 4, 7, 17, 26, 35)
    val listTerminal = List(34, 67, 39, 62, 32, 49, 50, 13, 43, 23, 25, 16, 48, 51, 37, 68, 46, 42, 51, 49, 76, 14, 79, 48, 87, 73, 54, 34, 65, 23, 44, 51, 49, 3, 41, 7, 86, 72, 25, 30, 63, 13, 39, 27, 88, 89, 57, 46, 31, 58, 61, 75, 34, 85, 93, 49, 54, 61, 56, 72, 54, 51, 6, 46, 50, 59, 73, 33, 31, 86, 61, 37, 81, 16, 64, 39, 85, 42, 57, 40, 62, 69, 28, 30, 38, 57, 66, 47, 36, 38, 35, 45, 30, 46, 45, 37, 16, 26, 42, 43)
    val works = listStart.zip(listTerminal).map(t => Work(t._1, t._2))
    // 終了時間が終わるのが早い順でソート
    val sortedWork = works.sortWith((w1, w2) => w1.terminal < w2.terminal)
    val selectedQueue:mutable.Queue[Work] = mutable.Queue()
    var nexTerminal = 0
    sortedWork.foreach(w => {
      if(w.start > nexTerminal) {
        nexTerminal = w.terminal
        selectedQueue += w
      }
    })
    println(selectedQueue.size)
    println(selectedQueue)
  }
}
