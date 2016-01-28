object Practice11 {
  def main (args: Array[String]) {
    constPattern("文字")
    constPattern(1)
    constPattern(true)

    bindPattern("TESTTESTTEST")

    caseClassPattern(MatchTest("TEST1", 0))
    caseClassPattern(MatchTest("-----", 3))
    caseClassPattern(MatchTest("TEST3", 9))

    seqPattern(Seq(1,2))
    seqPattern(Seq(1,2,3))

    tuplePattern((1,2,3,4,5))

    guardPattern(3)
  }

  def constPattern(any: Any) = {
    any match {
      case "文字" => println("string 文字")
      case 1 => println("int 1")
      case true => print("boolean true")
      case _ => print("?")
    }
  }

  def bindPattern(s: String) = {
    s match {
      case bind_val => println("sをbind_valにバインドしました")
    }
  }

  //抽出子パターン
  def caseClassPattern(c: MatchTest) = {
    c match {
      case MatchTest("TEST1", _) => println("str:TEST1")
      case MatchTest("TEST2", _) => println("str:TEST2")
      case MatchTest(_, 3) => println("No:3")
      case MatchTest(_ @str2, _) => println("bind str2:" + str2)
      case _ => println("一個上で全部マッチするからここはこないよね")
    }
  }

  def seqPattern(s: Seq[Int]) = {
    s match {
      case Seq(1, _) => println("全2個で一個目が1")
      case Seq(_, 2 ,_) => println("全3個で2個目が2")
      case Seq(_, _ ,_ @no) => println("全3個で3個目が" + no)
      case _ => println("?")
    }
  }

  def tuplePattern(t: Any) = {
    t match {
      case (1,2) => println("タプル")
      case (1,2,3,4,5) => println("多いタプル")
      case _ => println("?")
    }
  }

  def guardPattern(a: Any) = {
    a match {
      case s: String if s == "TEST1" => println("String TEST1")
      case i: Int if i == 3 => println("Int 3")
      case _ => println("?")
    }
  }
}

case class MatchTest(srt: String, no: Int) {}
case class WrapString(s: String)
case class WrapInt(i: Int)
case class WrapBoolean(b: Boolean)

//列挙体
sealed abstract class BloodType(value: String) {}
object BloodType {
  case object A extends BloodType("A")
  case object B extends BloodType("B")
  case object O extends BloodType("O")
  case object AB extends BloodType("AB")
}

