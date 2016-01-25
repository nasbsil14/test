object Practice10 {
  def main (args: Array[String]) {
    val list = args.toList.map(s => addList(s))
    println(list)
  }

  def addList(s: String): Command = {
    s match {
      case "A" =>  A
      case "B" =>  B
      case "C" =>  C
    }
  }

  class Command(name: String) {
  }
  case object A extends Command("A")
  case object B extends Command("B")
  case object C extends Command("C")

}
