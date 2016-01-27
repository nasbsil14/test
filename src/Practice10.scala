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
        createCommandList(line.split(",")(1))
      }
    } catch {
      case ex: FileNotFoundException => println("ファイル無い")
      case ex: IOException => println("読めない")
    }

    println(csv)
    println("END")
  }

  def createCommandList(s: String): Option[Command] = {
    println(s)
    s.toUpperCase match {
      case "A" =>  Some(A)
      case "B" =>  Some(B)
      case "C" =>  Some(C)
      case _ => None
    }
  }

  class Command(name: String) {
  }
  case object A extends Command("A")
  case object B extends Command("B")
  case object C extends Command("C")

}
