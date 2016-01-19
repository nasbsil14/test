object Practice7 {
    trait T1 {
        println("T1 x =" + x)
        val x = 1
        println("T1 x =" + x)
        def t1Method() = {
            println("t1Method")
        }
    }

    trait T2 {
        println("T2 y =" + y)
        val y = 2
        println("T2 y =" + y)
        def t2Method() = {
            println("t2Method")
        }
    }

    class Base1 {
        //println("T2 w =" + w)
        val w = 3
        println("base1 w =" + w)
    }

    class Cls1 extends Base1 with T1 with T2 {
        //println("cls1 z =" + z)
        val z = 4
        println("cls1 z =" + z)
        println("cls1 w +" + w)
        override def t1Method() = {
            println("override")
        }
    }

    trait trTest1{def print:Unit}
    trait trTest2{val msg:String; def print = println(msg)}

    class compCls(msg:String) {def Print = println(msg)}
    object compCls{def apply(msg:String) = new compCls(msg)}

    case class csClass (name:String, age:Int)

    def main(args: Array[String]): Unit = {
        val test = new Cls1()
        test.t1Method()
        test.t2Method()

        //無名クラスでのトレイト使用
        val trTest = new {val msg = "trait test"} with trTest1{def print = println(msg)}
        trTest.print
        //事前初期化を使用したサンプル
        val trTest2 = new {val msg = "override"} with trTest2
        trTest2.print

        //コンパニオンオブジェクトはファクトリー的役割で使える（静的でない共通関数的な）
        // newが必要なくなるのがメリット？
        compCls("comp object").Print
        //println(csClass("case class").name)
        //Listのapplyメソッド
        val listTest = List("apple","orange","suika")
        println(listTest(1))

        //ケースクラスはコンパニオンオブジェクト自動生成だから、newが不要
        val csTest = csClass("bob",20)
        //ケースクラス自動実装のapply利用
        println(csTest.name)
        csTest match {
            //unapply（抽出）の使用。条件に当てはまるとname変数に該当データを格納する。
            case csClass(name,20) => println(name)
            case _ =>
        }
    }
}