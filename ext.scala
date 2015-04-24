object extScala {
    def main(args:Array[String]):Unit = {
        println("START")

        val list1 = "ABCDEFG".toList

        for (s <- list1){println(s)}
        //list1.foreach{s => println(s)}
        list1.foreach{println(_)}

        val list2:List[String] = List("AB","C","DE","FG","H")
        list2.foreach(s => println(s))

        //collectは条件に該当した要素のみ処理（caseの使い方が特殊）
        val list3 = list2.collect {
            case s if s.length > 1 => s
        }
        //mapは全要素対象
        // val list3 = list2.map{s =>
        //     s.length match {
        //         case 2 => s
        //         //case _ =>
        //     }
        // }
        list3.foreach(s => println(s))

        //セット値のオブジェクトが明らかにわかる場合は型推論にまかせた方が見やすいソースになる
        // たとえば、newしてるとか。メソッド呼び出しの戻り値を受けるような書き方の場合は、わかりにくいので明示したほうがいいかも

        val al = Array(1,2,3)
        println(al.filter(_ % 2 != 0).length)

        val l1 = List(1,2)
        val l2 = l1.++(List(3,4))
        val l3 = l1.::(List(5,6))
        println(l3)

        val l4 = List(1,2,3,4,5,6)

        //入れ子ループ（for(x <- l1){for(y <- l2){}}）
        for (x <- l2; y <- l1){
            println(x)
            println(y)
        }

        val lNames = List("山田","田中","斉藤")
        val mNames = List("一郎","二郎","三郎")
        // for (l <- lNames; m <- mNames) {
        //     println(l + m)
        // }
        //lNames.foreach(l => mNames.foreach(m => println(l + m)))

        val lmNames:List[String] = for(l <- lNames; m <- mNames) yield (l + m)
        lmNames.foreach(println(_))
        //↓変換したverらしいが、一文字ずつの処理になる(配列の各要素が1桁なら上と同じ動きになると思う)・・・
        val lmNames2:List[Char] = lNames.flatMap(l => mNames.flatMap(m => l + m))
        lmNames2.foreach(println(_))

        for(l <- lNames; m <- mNames; if l.indexOf("田") >= 0){println(l + m)}

        //qSort()

        println("END")
    }

    def qSort():Unit = {
        //文字列検索アルゴリズム
        val listKeyword:List[Char] = "ABCDEFGHIJK".toList
        val listTarget:List[Char] = "FGH".toList

        import scala.util.control.Breaks
        val b = new Breaks

        for(i <- 1 to listKeyword.length - listTarget.length;
            j <- 1 to listTarget.length;
            if listKeyword((i-1) + (j-1)) == listTarget(j-1)){
                if (j == listTarget.length) {
                            //検索対象文字全部が一致
                            println(i)
                        }
        }
        // for(i <- 1 to listKeyword.length - listTarget.length) {
        //     //キーワード文字数分ループ
        //     for (j <- 1 to listTarget.length) {
        //         //検索対象文字数分ループ
        //         if (listKeyword((i-1) + (j-1)) == listTarget(j-1)) {
        //             //文字一致
        //             if (j == listTarget.length) {
        //                 //検索対象文字全部が一致
        //                 println(i)
        //             }
        //         } else {
        //             //文字が一致しなかったので、キーワードの次の文字へ
        //             //b.break
        //         }
        //     }
        // }
    }
}