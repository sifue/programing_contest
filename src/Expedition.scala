import scala.collection.mutable

object Expedition {

  trait Spot {def position():Int; def gas:Int}
  case class GasStand(position:Int, gas:Int) extends Spot
  case class Goal(position:Int, gas:Int) extends Spot

  object GasOrder extends Ordering[Spot] {
    def compare(x: Spot, y: Spot): Int = x.gas.compare(y.gas)
  }

  def main(args: Array[String]) {
    val positionList = List(10, 14, 20, 21)
    val gasList = List(10, 5, 2, 4)
    val spotList:List[Spot] =
      (positionList
        .zip(gasList)
        .map(t => new GasStand(t._1, t._2))) ::: List(new Goal(25, 0))

    // 燃料が多い順に並ぶプライオリティーキューを用意
    val priorityQueue = new mutable.PriorityQueue[Spot]()(GasOrder)

    val answers = new mutable.MutableList[Spot]
    var tank = 10
    var currentPosition = 0
    var isReachable = true
    spotList.foreach(s => {
        if(!isReachable) return
        val nextDistance = s.position - currentPosition

        def isReachableNow = {tank - nextDistance >= 0}
        def isRefillableNow = {!priorityQueue.isEmpty}
        while(!isReachableNow && isRefillableNow){
          val usedGasStand = priorityQueue.dequeue()
          tank += usedGasStand.gas
          answers += usedGasStand
        }

        if(isReachableNow) {
          tank -= nextDistance
          currentPosition = s.position
          priorityQueue.enqueue(s)
        } else {
          // 可能な限り補給してもダメな場合は辿りつけなかったとして終了
          isReachable = false
        }
      }
    )
    println(answers)
  }
}
