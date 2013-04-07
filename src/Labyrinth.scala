import scala.collection.mutable

case class Step(row:Int, column:Int, char:Char)

object Labyrinth {
  private val n = 50 // 行
  private val m = 50 // 列
  private val labString =
    """.S.#.##.####.##....#..#..#........................
      |.#.#............##.#.###.#.###.##################.
      |##.#.#############.#..#..#...#.#.....#......#.#.#.
      |...#...#........##.#....####.#.##.#..#.###......#.
      |.#.#.#....#.######.#.#.......#.#..#........###..#.
      |.######.###.#...##.#.#.#.##..#.#.#########.#.##.#.
      |..........#.#.#.##.#.#######.#.#.#...#...#.#.#..#.
      |.#.########.#.#.##.#....##.#.#.#.#.#.#...#.#.#..#.
      |.#.##.#.#.#.#.#.#..#.##......#.#.#.#...#.#.#.#..#.
      |.#....#.#.#.#.#.#.##.#########.#.#######.#.#.#.##.
      |#######.#...#.#.#......#.....#.#.........#.#.#..#.
      |..#...#.#.#.#.#.#..#####.#.#.#.###########.#.#..#.
      |......#.#.#...#.#......#..#..#.....#.......#.#..#.
      |#####.#.#.#####.#####..#.....#..##...#.#...#.##.#.
      |..#...#.#.#.###.#.########.#########.#.#...#.#..#.
      |..#.#...#.#.......#..#.................###.#.#..#.
      |....#.....#.#####.#.##.#################...#....#.
      |.##########.#.#.#.#..#..#..#.........#.#..#######.
      |.#..#.....#.....#.#.###.#.....#####....#..#.......
      |...##.#.#....##.#.#..#..#.##########.#.##.#.######
      |.#..#.#.#.#######.##.#.##.#..........#.##.#...####
      |.##.#####.#.......##....#.#.########.#.##.#.#.#...
      |#####.....#############.###.#.....##.#.##.#.#.#.#.
      |.......#..............#...#.#####.##.#.##.#.#.#.#.
      |####.####.............###.#.#####.##.#.##.#.#.#.#.
      |...#.#..................#.#.......##.#.##.#.#.#.#.
      |.#.#....###..###........#.##########.#.##.#.#.#.#.
      |.#.#.#.#.....#..........#............#.##.#.#.#.#.
      |.#.#...###...#.###......#.############.##.#.###.#.
      |.#.#.#....#..#..#.......#.#.#############.#..##.#.
      |.#.#......#..#..#.......#.#.#.............##.##.#.
      |.#.#.#.###...####.......#.#.#.##############.##.#.
      |.#.#....................#.#.#.#...........##.##.#.
      |.#.#.#......###..###....#.#.#.#.############.##.#.
      |.#.#.......#.....#......#.#.#.#.############.##.#.
      |.#.#.#.....###...#.###..#.#.#.#.#.........##.##.#.
      |.#.#..........#..#..#...#.#.#.#.#.#######.##.##.#.
      |.#.#.#........#..#..#...#.#.#.#.#.#.#G#.#.##.##.#.
      |.#.#.#.....###...####...#.#.#.#.#.#.#.#.#.##.##.#.
      |.#.#.#..................#.#.#.#.#.#.#.#.#.##.##.#.
      |.#.#.#...###..###.......#.#.#.#.#.#.......##.##.#.
      |.#.#.#..#.....#.....###.#.#.#.#.#.##########.##.#.
      |.#.#.#..###...#.###.#.#.#.#.#.#.#............##.#.
      |.#.#.#.....#..#..#..#.#.#.#.#.#.###############.#.
      |.#.#.#.....#..#..#..#.#.#.#.#.#.###############.#.
      |.#.#.#..###...####..#.#.#.#.#.#.................#.
      |.#.#.#..............#.#.#.#.#.###################.
      |.#..................#...#.#.#.....................
      |.########################.#.#####################.
      |..........................#.......................""".stripMargin
  private val labArray = labString.split("\n").map(s => s.toArray)
  private val start = Step(0 ,1, 'S');
  /**
   * 各地点の最小距離を格納したMap、この距離が設定されている場所はもう探索しない
   */
  private val minDistanceMap : mutable.Map[Step, Option[Int]] = mutable.Map()

  def main(args: Array[String]) {
    // 初回探索と次回探索の候補のキュー作成
    val queue :mutable.Queue[Step] = mutable.Queue()
    val nextQueue :mutable.Queue[Step] = mutable.Queue()

    // スタート位置の設定
    var distance = 0
    queue += start
    minDistanceMap.put(start, Some(distance));
    distance += 1

    /**
     * 取得した候補を次のキューに追加しつゴールに達した場合はtrueを返す
     * @param next
     * @return ゴールに達したかどうか
     */
    def addToNextQueue(next:Option[Step]) = {
        next match {
        case Some(Step(_, _, 'G')) =>
          true
        case Some(Step(_, _, _)) =>
          nextQueue += next.get
          false
        case None => false
      }
    }

    // キューにある候補を上下左右に巡回
    while (!queue.isEmpty){
      val from = queue.dequeue()

      val isGoal =
        addToNextQueue(up(from, distance)) ||
        addToNextQueue(down(from, distance)) ||
        addToNextQueue(left(from, distance)) ||
        addToNextQueue(right(from, distance))

      if(isGoal) {
        println("GOAL! distance:" + distance)
        queue.clear()
        nextQueue.clear()
      }

      if(queue.isEmpty && !isGoal) {
        // 今の候補を探索し終えたら、距離を増やして次候補と入れ替える
        distance = distance + 1
        println("distance: " + distance)
        println(nextQueue.toList)
        queue ++= nextQueue
        nextQueue.clear()
      }
    }
  }

  def getNextAndUpdateMap(next:Step, distance:Int):Option[Step] = {
    minDistanceMap.get(next) match {
      case None =>
        if (next.char == '.' || next.char == 'G') {
          minDistanceMap.put(next, Some(distance))
          return Some(next)
        } else return None
      case _ => return None
    }
  }

  def up(from :Step, distance:Int):Option[Step] = {
    if(from.row <= 0) return None;
    val ch = labArray(from.row - 1)(from.column)
    val next = Step(from.row - 1, from.column, ch)
    return getNextAndUpdateMap(next, distance)
  }

  def down(from :Step, distance:Int):Option[Step] = {
    if(from.row >= n - 1) return None;
    val ch = labArray(from.row + 1)(from.column)
    val next = Step(from.row + 1, from.column, ch)
    return getNextAndUpdateMap(next, distance)
  }

  def left(from :Step, distance:Int):Option[Step] = {
    if(from.column <= 0) return None;
    val ch = labArray(from.row)(from.column - 1)
    val next = Step(from.row, from.column - 1, ch)
    return getNextAndUpdateMap(next, distance)
  }

  def right(from :Step, distance:Int):Option[Step] = {
    if(from.column >= m - 1) return None;
    val ch = labArray(from.row)(from.column + 1)
    val next = Step(from.row, from.column + 1, ch)
    return getNextAndUpdateMap(next, distance)
  }
}
