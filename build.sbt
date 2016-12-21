import Common._
import Dependencies._
import BuildTask._


lazy val root = (project in file(".")).
  settings(commonSettings:_*).
  settings(libraryDependencies ++= allDeps).
  settings(buildtaskSettings:_*).
  settings(Common.protoBufSettings:_*)