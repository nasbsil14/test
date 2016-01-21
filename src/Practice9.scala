object Practice9 {
  def main (args: Array[String]) {
    println("START")

    val cls = new Cls with T1
    cls.print2()

    //    //ObjReposがimplicitを解決してくれるはず・・・だめだった。ファクトリが必要な模様
    //    import ObjRepos._
    //    val facade = new Facade
    //    facade.print()
    val factory = new Factory(ObjRepos)
    factory.facade.print()

    val exect = ComponentRegistry.executer
    exect.exec

    println("END")
  }

  //******** 自分型 *********//
  class Cls {
    val name = "Cls"
    def print() = {
      println(this.name)
    }
  }
  trait T1 {self: Cls =>
    //Clsにミックスイン前提でClsのフィールド使う(=Clsに依存する実装ということになる)
    def print2() = {
      println(self.print())
    }
  }
  //***********************//

  //******** implicitでDI ********//
  //DIするもの
  trait Repo {
    def print() = println("車")
  }
  //その実装
  class RepoImpl1 () extends Repo {
    override def print() = println("乗用車")
  }
  class RepoImpl2 () extends Repo {
    override def print() = println("トラック")
  }
  //DIオブジェクト入れ物
  trait Repos {
    implicit val repo: Repo
  }
  //DI解決
  object ObjRepos extends Repos {
    lazy val repo = new RepoImpl1()
    //実装の切替イメージ（どちらか一方でないと、同一型が二つあるよでコンパイルエラー）
    //lazy val repo = new RepoImpl2()
  }

  //DIオブジェクトを使って処理するクラス
  class Facade(implicit val repo: Repo){
    def print() = repo.print()
  }

  class Factory(repos : Repos){
    // コンポーネントを列挙したクラスのフィールドをimportしてやる
    import repos._

    lazy val facade = new Facade() // 勝手に必要なコンポーネントが渡される
  }
  //************************//

  //********* CakeパターンでDI ***********//
  trait A_ServiceComp {
    val a_ServiceMethod: ServiceMethod
    trait ServiceMethod {
      def on: Unit
      def off: Unit
    }
  }
  trait B_ServiceComp {
    //trait使って名前空間切り的な
    val b_ServiceMethod: ServiceMethod
    trait ServiceMethod {
      def is: Boolean
    }
  }
  trait A_ServiceCompImpl extends A_ServiceComp {
    class A_Server extends ServiceMethod {
      def on = println("ON")
      def off = println("OFF")
    }
  }
  trait B_ServiceCompImpl extends B_ServiceComp {
    class B_Server extends ServiceMethod {
      def is = true
    }
  }

  //
  trait ServiceCompImpl {
    self: A_ServiceComp with B_ServiceComp =>
    class Executer {
      def exec = {
        //コンポーネント内の変数名を同じにすると後者（B_Serviceの変数）が優先される模様
        if (self.b_ServiceMethod.is) self.a_ServiceMethod.on
        else self.a_ServiceMethod.off
      }
    }
  }

  // モジュール内でサービスのインスタンスを生成する
  object ComponentRegistry extends
  A_ServiceCompImpl with
  B_ServiceCompImpl with
  ServiceCompImpl {

    val a_ServiceMethod = new A_Server
    val b_ServiceMethod = new B_Server
    val executer = new Executer
  }
  //*************************************//

  //********* gitで見かけた実装 ***********//
  class Impl1 () {
    //コンパニオンオブジェクトからもらう
    implicit val name = Impl1.name
    def getName(implicit name: String) = {
      name
    }
  }
  object Impl1 {
    val name = "ObjImpl1"
  }
  //*************************************//
}
