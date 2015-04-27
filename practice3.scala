object practice3 {
    def main(args:Array[String]):Unit = {
        println("START")

        //コンソール入力値取得
        import scala.io.Source
        val args4:List[String] = Source.stdin.getLines.toList
        println(args4.length)
        // for (s <- Source.stdin.getLines) println(s)
        // val args3 = for (s <- Source.stdin.getLines) yield {
        //     println(s)
        //     s.trim().split(",")
        // }

        val args2 = Array("red green blue blue green blue")
        import scala.collection.mutable
        val data:Array[String] = args2(0).replace("\n","").split(" ")
        var result:mutable.Map[String, Int] = mutable.Map()
        var cnt = 0

        data.foreach{s =>
            cnt = result.getOrElse(s, 0) + 1
            if (result.contains(s)) {
                result(s) = cnt
            } else {
                result.put(s, cnt)
            }
        }

        import scala.collection.immutable.ListMap
        ListMap(result.toSeq.sortWith(_._2 < _._2):_*).foreach(e => println(e._1 + " " + e._2))
        result.foreach(e => println(e._1 + " " + e._2))


        import scala.collection.immutable.SortedMap
        val data2:List[String] = args2(0).replace("\n","").split(" ").toList

        //.mapで生成されたList[Map[String, Int]]をSortedMapに変換する
        val result2:SortedMap[String, Int] = SortedMap(data2.distinct.map{s =>
            (s -> data2.filter(_ == s).size)
        }:_*)

        result2.foreach{e =>
            println(e._1 + " " + e._2)
        }
    }
}