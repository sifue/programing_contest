object DynamicPrograming {
  def main(args: Array[String]) {
    val s = "abcd";
    val t = "becd";
    val dpTable : Array[Array[Int]] = Array.fill(s.size + 1)(Array.fill(t.size + 1)(0))
    val dbStrTable: Array[Array[StringBuilder]] =
      Array.fill(s.size + 1)(Array.fill(t.size + 1)(new StringBuilder))
    s.zipWithIndex.foreach { case (charS, indexS) =>
      t.zipWithIndex.foreach{ case (charT, indexT) =>
        if (charS == charT) {
          // カウントを更新
          dpTable(indexS + 1)(indexT + 1) = dpTable(indexS)(indexT) + 1

          // できた文字列を更新
          dbStrTable(indexS + 1)(indexT + 1) = dbStrTable(indexS)(indexT).append(charS)
        } else {
          // カウントを更新
          dpTable(indexS + 1)(indexT + 1) =
            Math.max(dpTable(indexS + 1)(indexT), dpTable(indexS)(indexT + 1))

          // できた文字列を更新
          dbStrTable(indexS + 1)(indexT + 1) =
            if (dpTable(indexS + 1)(indexT) >= dpTable(indexS)(indexT + 1)) {
              dbStrTable(indexS + 1)(indexT)
            } else {
              dbStrTable(indexS)(indexT + 1)
            }

        }
      }
    }
    val result = dbStrTable(s.size)(t.size)
    "%1$d(\"%2$s\")".format(result.length, result)
  }
}
