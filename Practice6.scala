object Practice6 {
    def main(args:Array[String]) = {
        val list = List[String]("A","B","C")
        for (s <- list) {
            s match {
                case "A" => println("Adesu")
                case _ => println("other")
            }
        }
        val x = if (list.size > 0) "OK"
        println(x)
        test_case()
    }

    case class Person(name:String, age:Int)
    val alice = new Person("Alice", 25)
    val bob = new Person("Bob",32)
    val charlie = new Person("Charlie",32)

    def test_case () = {
        //caseクラスマッチ
        for (person <- List(alice, bob, charlie)) {
            person match {
                case Person("Alice",25) => println("Hi Alice")
                case Person("Bob",32) => println("Hi Bob")
                case Person(name, age) => println("Who are you?")
            }
        }

        //クラスマッチ
        for (x <- List("String", 123, true)) {
            x match {
                case x:String => println(x)
                case x:Int => println("Int")
                case x:Boolean => println("Boolean")
                case _ => println("Other")
            }
        }
    }
}
