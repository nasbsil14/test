object Practice9 {
  def main (args: Array[String]) {
    println("START")

    val cls = new Cls1 with T1
    cls.print2()

    val factory = new Factory(ObjRepos)
    factory.facade.print()
    //ObjReposがimplicitを解決してくれるはず・・・
    import ObjRepos._
    val facade2 = new Facade2
    facade2.print()

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
      println(self.print())
    }
  }

  //DIするもの
  trait Repo {
    def print() = println("骨")
  }
  class RepoImpl () extends Repo {
    override def print() = println("肉")
  }
  //DI入れ物
  trait Repos {
    implicit val repo: Repo
  }
  //DI解決
  object ObjRepos extends Repos {
    lazy val repo = new RepoImpl()
  }
  //DIオブジェクトを使って処理するクラス
  class Facade()(implicit repo: Repo){
    def print() = repo.print()
  }
  class Facade2(implicit repo: Repo){
    def print() = repo.print()
  }

  class Factory(repos : Repos){
    // コンポーネントを列挙したクラスのフィールドをimportしてやる
    import repos._

    lazy val facade = new Facade() // 勝手に必要なコンポーネントが渡される
  }

  class Impl1 () {
    //コンパニオンオブジェクトからもらう
    implicit val name = Impl1.name
  }
  object Impl1 {
    val name = "ObjImpl1"
  }
}
