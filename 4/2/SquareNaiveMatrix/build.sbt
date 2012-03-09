name := "SquareNaiveMatrix"

version := "1.0-SNAPSHOT"

scalaVersion := "2.9.1"

resolvers ++= Seq("Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
	  "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo")


libraryDependencies ++= Seq("junit" % "junit" % "4.8.1" % "test",
		    "org.scalala" %% "scalala" % "1.0.0.RC3-SNAPSHOT",
		    "org.scalatest" %% "scalatest" % "1.7.1" % "test"
		    )
