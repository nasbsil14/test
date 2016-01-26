import java.io.{IOException, FileNotFoundException}

import scala.io.Source

object Practice10 {
  def main (args: Array[String]) {
    println("START")
//    val list = args.toList.map(s => createCommandList(s))
//    println(list)

    val fileName = "C:/Users/d-takehana/Desktop/work/test/test.csv"
//    Source.fromFile(fileName).foreach(print)
    val csv =
    try {
      Source.fromFile(fileName).getLines().toList.map { line =>
        println("debug:" + line)
        line.split(",")(1)
      }
    } catch {
      case ex: FileNotFoundException => println("ファイル無い")
      case ex: IOException => println("読めない")
    }

    println(csv)
    println("END")
  }

  def createCommandList(s: String): Command = {
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
