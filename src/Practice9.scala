object Practice9 {
  def main (args: Array[String]) {
    println("START")

    val cls = new Cls1 with T1
    cls.print()
    cls.print2()

    val impl = new Impl1

    println("END")
  }

  class Cls1 {
    val name = "Cls1"
    def print() = {
      println(this.name)
    }
  }

  trait T1 {self: Cls1 =>
    //Clsにミックスイン前提でCls1のフィールド使う
    def print2() = {
      println(self.name)
    }
  }

  class Impl1 () {
    implicit val name = Impl1.name
  }
  object Impl1 {
    val name = "ObjImpl1"
  }
}
