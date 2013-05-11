import scala.collection.mutable

object Expedition {

  // ガソリンスタンドとゴールを同一視する
  trait Spot {def position():Int; def gas:Int}
  case class GasStand(position:Int, gas:Int) extends Spot
  case class Goal(position:Int, gas:Int) extends Spot

  object GasOrder extends Ordering[Spot] {
    def compare(x: Spot, y: Spot): Int = x.gas.compare(y.gas)
  }

  def main(args: Array[String]) {
    val positionList = List(1, 76, 138, 155, 243, 260, 335, 435, 498, 536, 564, 594, 602, 636, 695, 744, 750, 838, 869, 966, 1053, 1072, 1108, 1184, 1225, 1271, 1342, 1376, 1391, 1432, 1487, 1541, 1635, 1697, 1755, 1794, 1799, 1812, 1822, 1836, 1860, 1951, 1964, 1997, 2015, 2056, 2057, 2123, 2132, 2187, 2213, 2284, 2373, 2412, 2451, 2530, 2610, 2657, 2734, 2760, 2856, 2912, 3003, 3050, 3070, 3098, 3148, 3223, 3302, 3400, 3432, 3510, 3586, 3604, 3607, 3622, 3713, 3754, 3807, 3848, 3920, 4012, 4085, 4091, 4159, 4227, 4260, 4354, 4360, 4388, 4406, 4426, 4441, 4535, 4623, 4687, 4760, 4824, 4854, 4880)
    val gasList = List(70, 31, 45, 45, 48, 61, 87, 52, 89, 26, 2, 51, 24, 91, 2, 11, 46, 82, 78, 26, 85, 81, 100, 64, 70, 19, 71, 8, 52, 87, 36, 73, 38, 63, 55, 87, 52, 91, 25, 58, 10, 47, 9, 21, 81, 27, 56, 58, 70, 74, 42, 85, 58, 85, 99, 79, 4, 85, 68, 71, 11, 60, 40, 53, 49, 4, 37, 73, 24, 28, 95, 60, 67, 81, 31, 9, 39, 81, 91, 74, 39, 42, 81, 73, 100, 37, 16, 53, 98, 17, 52, 29, 75, 20, 67, 62, 26, 11, 29, 71)
    val spotList:List[Spot] =
      (positionList
        .zip(gasList)
        .map(t => new GasStand(t._1, t._2))) ::: List(new Goal(5000, 0))

    var tank = 100
    var currentPosition = 0
    var isReachableFinally = true
    // 燃料が多い順に並ぶプライオリティーキューを用意
    val priorityQueue = new mutable.PriorityQueue[Spot]()(GasOrder)
    val answers = new mutable.MutableList[Spot]

    // 一つ一つのガソリンスタンドを進めて状態を遷移させる関数goNextSpotをforeachで回す
    // 次のスポットの距離まで辿りつけない場合は、
    // 今辿りつけた補給可能ガソリンスタンドで最も量が多い順に補給していく。
    // 可能な補給を全て行ったとしても辿り着けなかった場合は最終的に辿りつけなかったとする。
    def goNextSpot(nextSpot:Spot):Unit = {
      if(!isReachableFinally) return
      val nextDistance = nextSpot.position - currentPosition

      // 次に辿り着けない場合は、燃料が多い順に補給
      def isReachableNow = {tank - nextDistance >= 0}
      def isRefillableNow = {!priorityQueue.isEmpty}
      while(!isReachableNow && isRefillableNow){
        val usedGasStand = priorityQueue.dequeue()
        tank += usedGasStand.gas
        answers += usedGasStand
      }

      if(isReachableNow) {
        // 次に辿りつけたら、補給可能なプライオリティーキューに追加
        tank -= nextDistance
        currentPosition = nextSpot.position
        priorityQueue.enqueue(nextSpot)
      } else {
        // 可能な限り補給してもダメな場合は辿りつけなかったとして終了
        isReachableFinally = false
      }
    }
    spotList.foreach(goNextSpot)

    // 解答出力
    if(isReachableFinally){
      println(answers.size)
    } else {
      println(-1)
    }
    println(answers)
  }
}
