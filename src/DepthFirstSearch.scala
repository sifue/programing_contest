object DepthFirstSearch {
  def main(args: Array[String]) {
    val n = 20
    val a = List(1, 10, 49, 3, 8, 13, 7, 23, 60, -500, 42, 599, 45, -23, 1, 10, 49, 3 ,8, 13)
    val k = 444

    // リストの要素を最初から足すか足さないかという2択で考え、４つの場合なら
    // 足さない, 足さない, 足さない, 足さない
    // 足さない, 足さない, 足さない, 足す
    // 足さない, 足さない, 足す, 足さない
    // ...
    // のように再帰関数を使って判定していく

    /**
     * 深さ優先探索を行う再帰関数
     * @param index リストのインデックス
     * @param addedList 足されたもののリスト
     * @return 判定結果
     */
    def depthFirstSeach(index:Int, addedList:List[Int]):Boolean = {
      // インデックスが最後のnに達していたら判定する
      // addedList.foldLeft(0)(_+_) はリストの中身を全部足して集約するという意味
      if (index == n) if (addedList.foldLeft(0)(_+_) == k) {
        println("YES (%s)".format(addedList)) // 見つかったら内訳を出力して終了
        return true
      } else {
        return false
      }

      // インデックスが最後のnに達していない場合
      // 今回を足さない場合と判定して残りを再帰的に判定し、trueの時は終了
      if (depthFirstSeach(index + 1, addedList)) return true
      // 今回を足す場合と判定して残りを再帰的に判定し、trueの時は終了
      // addedList :+ a(index)はリストの末尾に要素を一つ足すという意味
      if (depthFirstSeach(index + 1, addedList :+ a(index))) return true
      // 全てのパターンを探しても見つからなかった時はfalse　
      return false
    }

    // 実行
    if (!depthFirstSeach(0, List())){
      println("NO") // 最終的に見つからなかった場合はNOを出力
    }
  }
}
