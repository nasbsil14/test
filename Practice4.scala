object Practice4 {
    //コンストラクタ
    println("constractor")

    //クラス定義
    class Upper {
        def upper (strings:String*) : Seq[String] = {
            strings.map((s:String) => s.toUpperCase())
        }
    }
    //インスタンス化
    val up = new Upper
    println(up.upper("A","First","Scala","Program"))

    def main(args:Array[String]):Unit = {
        println("main start")

        // def sum(i:Int = 1, ii: Int) = i + ii
        // println(sum(ii = 5))

        // def sum2(i:Int)(implicit imp:Int) = i + imp
        // implicit val imp = 2
        // println(sum2(5))

        // val t_str = "ABC"
        //文字列を受け取って、for文をまわしてコレクションを返す関数を定義
        // val x = (str:String) => {for(i <- 0 to str.length-1) yield i}
        // val y = x(t_str)

        //this.test_io
        //this.test_actor
        println(('a' to 'z').getClass)
        import akka.util.ByteString
        println(('a' to 'z').map(ByteString(_)).getClass)

        //this.observ_patten

        //ForeachSinkの置き換えver
        val x = clsTest[String]{ s => println(s)}
        x.get("A")

        val xx = "test"
        if (xx.equals("test")) println("ok") else println("ng")

        val xxx = ("A","B")
        xxx match {
            case (_, "B") => println(xx)
            //case (_ @ vx, "B") => println(vx)
            case _ => println("no")
        }

        //map:要素を変換したListを返す
        val x2 = List(1,2,3,4,5)
        val x3 = x2.map({_ * 2})
        //collect:条件に該当した要素を変換したListを返す
        val x4 = List("A",1,"B",2,"C")
        val x5 = x4.collect({case e:String => e + "TEST"})
        //reduceLeft:左から集計していく
        val x6 = List(1,2,3)
        val x7 = x6.reduceLeft({(a,b) => a + b})
        //foldLeft:左から初期値を元に集計していく
        val x8 = x6.foldLeft(0)({(a,b) => a + b})

        val x9 = new abs

        //関数を引数にとるメソッド
        val x10 = (x:Int) => {x < 1}
        funcTest(x10(1))
    }

    case class clsTest[in](f:in => Unit) {
        def get(elem:in):Unit = f(elem)
    }

    class abs() extends absClsTest {
        override def isBoo2 = isBoo
    }
    abstract case class absClsTest(){
        val x:String =""
        def isBoo = true
        def isBoo2 = false
    }

    def funcTest(flg: => Boolean):Unit = {
        println(flg)
    }

    //scala.Ioの使用例
    def test_io = {
        import scala.io._
        val src = Source.fromURL("http://www.chips-clown.net/","UTF-8")
        println("Source Out Put")
        println(src.mkString)
    }

    //actorの使用例
    import scala.actors._, Actor._
    case class Message(msg: String)
    def test_actor = {
        val consumer =
        actor{
            //無限ループで待ち構えないと単純な逐次処理になってしまう
            var done = false
            while (!done){
                receive{
                    case msg => println("Received message! -> " + msg)
                    done = (msg == "DONE")
                    //同期処理時に必要（メッセージの応答メソッドらしい）
                    reply("RECEIVED")
                }
            }
        }

        //非同期実行
        // println("1")
        // consumer ! "TEST1"
        // println("2")
        // consumer ! "TEST2"
        // println("3")
        // consumer ! "TEST3"
        // println("4")
        // consumer ! "DONE"

        //同期実行
        println("1")
        consumer !? "TEST1"
        println("2")
        consumer !? "TEST2"
        println("3")
        consumer !? "TEST3"
        println("4")
        consumer !? "DONE"

        //.concurrent.opsが無い
        // import scala.concurrent.ops._
        // spawn {
        //     val importantInfo : Array[String] = Array(
        //         "test1", "test2", "test3", "DONE")
        //     importantInfo.foreach(msg => consumer !? msg)
        // }
    }

    class Observ {
        var cnt:Int = 0;
        def set():Unit = {
            cnt = cnt + 1
        }
        def get():Int = {
            cnt
        }
    }
    class Target(obsv:Observ) {
        val obsv_ = obsv
        def run():Unit = {
            obsv_.set()
        }
    }
    def observ_patten():Unit = {
        val obsv = new Observ
        val targetA = new Target(obsv)
        val targetB = new Target(obsv)
        targetA.run
        targetB.run
        println(obsv.get)
    }
}
