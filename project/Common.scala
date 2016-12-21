import sbt._
import sbt.Keys._
import sbtassembly.AssemblyPlugin.autoImport._
import sbtprotobuf.{ProtobufPlugin => PB}

object Common {

  val commonSettings = Seq(
    name := "crazy-cricket",
    version := "0.1",
    scalaVersion := "2.11.7",
    organization := "com.bfm"
  )

  val assemblySettings = Seq(
    assemblyMergeStrategy in assembly := {
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },
    test in assembly := {}
  )

  val protoBufSettings =
    PB.protobufSettings ++ Seq(
      version in PB.protobufConfig := Dependencies.Versions.pbVer,
      javaSource in PB.protobufConfig := sourceDirectory.value / "generated",
      PB.runProtoc in PB.protobufConfig := { args =>
        com.github.os72.protocjar.Protoc.runProtoc(s"-v${Dependencies.Versions.pbVer.replace(".", "")}" +: args.toArray)
      }
    )
}