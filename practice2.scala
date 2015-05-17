object Practice2 {
    def main(args:Array[String]):Unit = {
        println("START")

        //宣言時にトレイトミックスイン
        val c1 = new Base with T1
        println(c1.getNum)
        println(c1.getName)

        //複数ミックスイン
        val c1m = new Base with T1 with T2
        println(c1m.getComment)

        val c1t = new Base with T1 {
            override val num = 0
            override val name = "tokmei"
        }
        println(c1t.getNum)
        println(c1t.getName)

        val c2 = new ClassExtendT1
        println(c2.getNum)
        println(c2.getName)

        val c3 = new ClassWithT1
        println(c3.getNum)
        println(c3.getName)

        println("END")
    }

    //クラス
    class Base {
        val num = 1
        def getNum():String = "num:" + num
    }
    //トレイト
    trait T1 {
        val name = "T1"
        def getName():String = "name:" + name
    }
    //クラス継承&トレイト
    class ClassWithT1 extends Base with T1 {
        override val num = 2
        override val name = "classwithT1"
    }
    //トレイト継承
    class ClassExtendT1 extends T1 {
        val num = 3
        def getNum():String = "num:" + num
        override val name = "ClassExtendT1"
    }

    trait T2 {
        val comment = "トレイト2です"
        def getComment():String = "comment:" + comment
    }

    //抽象クラス
    abstract class absC1 {
        type In
        val source:In
    }
    class C1(val source:String) extends absC1 {
        type In = String
    }
}