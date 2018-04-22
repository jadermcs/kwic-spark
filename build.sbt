organization := "br.unb.cic"
scalaVersion := "2.11.10"
name := "kwic"
version := "0.0.1"

sparkVersion := "2.3.0"
sparkComponents := Seq()

libraryDependencies ++= Seq(
  "org.backuity.clist" %% "clist-core"   % "3.3.0",
  "org.backuity.clist" %% "clist-macros" % "3.3.0" % "provided")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",

  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test")
