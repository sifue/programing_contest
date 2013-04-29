// S1...Si+1とT1...Ti+1に対する共通部分列は、
// ・Si+1=Tj+1の時、S1...SiとT1...Tiに対する共通部分列の後ろにSi+1をつなげたもの
// ・S1...Si+1とT1...Tiに対する共通部分列
// ・S1...SiとT1...Ti+1に対する共通部分列
//と漸化式を立てることができるのでそれを以下のように動的計画法の結果を保持する
//DPデーブルとして実装する
object DynamicPrograming {
  def main(args: Array[String]) {
    val s = "kgsbyshnthednsehtrgabjmnhnkafwwrnpsuxdbrmfggsgjdrfbcpjyshxdtirzzpytngmjwmfjtduftiwufmxmduxehmtkbureziurphzjzbwwayxuwaandywbneinkiyurhbtkmsbkmmnbjiriupxchtpbsefrnwbhhtxndbdpgdhkjmrtkafxaxziajwweczbsarjuukemchsrbusjnexwwrumsferygnuhkyiadrdrrzxzusxwfcazgmejintyjesfdbdewekepezmmtfwbuynwcustjmzwjxgcbcdxxrrkfpjygidaebatjnweyhryejgzmdmjhdpziucxdtxgcmjjdsjdkmhsdkperpfchcbsszimehtzacmdjpzusnunzcnmrejkjnhuhgmdwpcdnfgdzszrjyjibfkgagmadzkfhzmwesrkgcwruaynadizrngpdimbxhtkaiezhrkgxhdtdmjkptzprsxkbtuzfkpumxenwkminrdeaeftheamxcenzasjkabypgkgrytnyszeunszkcihuuyfcfacdxaepjknekfjeigcnhngufuxbtawtuyhrbehnbhxyfjgrgwywhzsgnptcmtmfkawjtnrybmuwgydrdhbjkgbufsaaeniyywyukmkwsbttprusuejceaupbsyywpwpehsduzngmxrepwabhpdgybhxfbyywxspzznsjfpbetgkfpyweyumrjijukhxbdajsnkpdwjdtjkbtbmazbkyzxtwmiiedpabdacxjykhaeeatudfcucngxygmkzmcatsxsnghmatsbfhiudruxnswbxwzkcyeunhwkkffzscxyzriytgcwmxpjtuxcikgrrtfxidrssuxipdkpxuaymgtzzfutummxgbmkesszcgkunbsbffertgtbxfnaeifkwfkksfupfyxweaufktscyxjeagfrdnctupkwmtemypxgabprdxtfzzkhfntatsbyxm";
    val t = "righknxxdtrbebwwrsbkuhtsxfhiuepnneyyjzgwdmnynxgjjadjabaukurrzsnditncgygexneyxwnpubfhgikdkmbjttagrcmzkgxjuxexbhhyjgashcpjrjdgauestafscdtxhywpaekecyjyjhihajypisaxbahkjyxsnxrphihmdcdauyyfapnrdyuhmnkayrpfapxbzbsbxumrfszywjspzgngwiiixwagkshppdbsuzpisrhtfzehcjbtxrfpmxssexititfaiytfahwizkyeppyiywkpgjxrziwwhnbncpcrrsdkadrxbjyimegyjdwptcpwpuscnkanrhreuywwapkjdppnajuswiupeffnzjasmjtjrhuxcysgmisyfmeaspseyxgpzsrpfwsfieynbbgxfpeucsfyunhfdkhyspkjprjppmrsftxtazyagyrujrrkmainaxefbjmmhcbhztkcnizypyfmymstuscfafepipzrwpbdicmhmeizisjctxtrhetifxmmrpxzccbhkfkutzsbuxgwzwbyycezeeykbaccmcjucgmjbxrjwiyuuynhfyrufjhxencubdgsnkriszpbdjefbbrmkgxuaeputmetxuetyksparnkxizjfcfbtgswjmpaddiufgdkbxdpyergcjkahibtnpjmsmmemdttssmanwirwneggkchzrbzjgdheyppfdnacdutxraszdhsjfurnbycxjgyprasdrdzmjhmufykgwdnbzjzuxxnezrrmcbpjubsucdebgrhaajzigwgcwpgfscidiygsawskwetepakrybnprdnickbgpuijhhkinakeygrnjfhfxnytrfjummknwgthuxirhtzgmcrmzudpyryzncytahkzigpecgedbzuwyycyuxdtdejphyyxdguuumybbjpdcmgharpwpczxznjmhxrnesnbrggfnbscmgnjteygpizbfsbnrjh";

    // インデックス(0,0)を0の初期値としてDBテーブルを縦横サイズ1つ分大きいものを用意する
    val dbTable: Array[Array[StringBuilder]] =
      Array.fill(s.size + 1)(Array.fill(t.size + 1)(new StringBuilder))

    // DPテーブルを0から全巡回して答を出していく
    s.zipWithIndex.foreach { case (charS, indexS) =>
      t.zipWithIndex.foreach{ case (charT, indexT) =>
        // まず、SとTのインデックスを進めて両方共同じ文字であれば、その文字を足したもの答えとなる
        if (charS == charT) {
          dbTable(indexS + 1)(indexT + 1) = dbTable(indexS)(indexT).clone.append(charS)
        } else {
          // Sのインデックスを進めてそこの文字列がTのインデックスを進めた時の文字列より大きければ
          // そこで同じだったものがあったということで、それを両方進めた時の答とできる
          dbTable(indexS + 1)(indexT + 1) =
            if (dbTable(indexS + 1)(indexT).size >= dbTable(indexS)(indexT + 1).size) {
              dbTable(indexS + 1)(indexT).clone
            } else {
              dbTable(indexS)(indexT + 1).clone
            }
        }
      }
    }

    val result = dbTable(s.size)(t.size)
    println("%1$d(\"%2$s\")".format(result.size , result.toString))
  }
}
