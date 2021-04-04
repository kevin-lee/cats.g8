import scala.collection.JavaConverters._
import java.lang.management.ManagementFactory

ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.12.13"
ThisBuild / organization := "io.kevinlee"
ThisBuild / organizationName := "Kevin's Code"
ThisBuild / developers := List(
  Developer(
    props.GitHubUsername,
    "Kevin Lee",
    "kevin.code@kevinlee.io",
    url(s"https://github.com/${props.GitHubUsername}"),
  )
)
ThisBuild / homepage := url(s"https://github.com/${props.GitHubUsername}/${props.RepoName}").some
ThisBuild / scmInfo :=
  ScmInfo(
    url(s"https://github.com/${props.GitHubUsername}/${props.RepoName}"),
    s"https://github.com/${props.GitHubUsername}/${props.RepoName}.git",
  ).some

scriptedBufferLog := false

scriptedLaunchOpts ++= ManagementFactory.getRuntimeMXBean.getInputArguments.asScala.toList.filter(
  a => List("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
)

lazy val root = (project in file("."))
  .settings(
    name := props.ProjectName,
    scriptedBufferLog := false,
    scriptedLaunchOpts ++= ManagementFactory.getRuntimeMXBean.getInputArguments.asScala.toList.filter(
      a => List("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
    )
  )
  .settings(noPublish)

lazy val props =
  new {
    val GitHubUsername = "Kevin-Lee"
    val ProjectName    = "cats-template"
    val RepoName       = "cats.g8"
  }

lazy val noPublish: SettingsDefinition = List(
  publish := {},
  publishM2 := {},
  publishLocal := {},
  publishArtifact := false,
  sbt.Keys.`package` / skip := true,
  packagedArtifacts / skip := true,
  publish / skip := true,
)
