object Practice8 {
    def main(args:Array[String]):Unit = {
        println("test5")

        val c = new ClassWithT1
        println(c.name)
        val c2 = new ClassExtendT1
        println(c2.name)

    }

    trait T1 {
        val name = "T1"
    }

    class Base

    class ClassWithT1 extends Base with T1 {
        override val name = "classwithT1"
    }
    class ClassExtendT1 extends T1 {
        override val name = "ClassExtendT1"
    }


    abstract class absC1 {
        type In
        val source:In
    }
    class C1(val source:String) extends absC1 {
        type In = String
    }
}