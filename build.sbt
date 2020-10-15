lazy val root = (project in file("."))
  .settings(
    organization := "io.github.cdelmas",
    name := "tsec-http4s-case-1",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.1",
    testFrameworks += new TestFramework("munit.Framework"),
    mappings in (Compile, packageDoc) := Seq(),
    libraryDependencies ++= Seq(
      "org.http4s"            %% "http4s-blaze-server"  % Http4sVersion,
      "org.http4s"            %% "http4s-blaze-client"  % Http4sVersion,
      "org.http4s"            %% "http4s-circe"         % Http4sVersion,
      "org.http4s"            %% "http4s-dsl"           % Http4sVersion,
      "io.circe"              %% "circe-generic"        % CirceVersion,
      "io.circe"              %% "circe-refined"        % CirceVersion,
      "ch.qos.logback"        % "logback-classic"       % LogbackVersion,
      "co.fs2"                %% "fs2-core"             % Fs2Version,
      "com.sksamuel.pulsar4s" %% "pulsar4s-core"        % Pulsar4sVersion,
      "com.sksamuel.pulsar4s" %% "pulsar4s-circe"       % Pulsar4sVersion,
      "com.sksamuel.pulsar4s" %% "pulsar4s-cats-effect" % Pulsar4sVersion,
      "eu.timepit"            %% "refined"              % RefinedVersion,
      "eu.timepit"            %% "refined-cats"         % RefinedVersion,
      "org.typelevel"         %% "cats-core"            % CatsVersion,
      "org.typelevel"         %% "cats-effect"          % CatsEffectVersion,
      "io.chrisdavenport"     %% "log4cats-slf4j"       % Log4CatsVersion,
      "is.cir"                %% "ciris"                % CirisVersion,
      "is.cir"                %% "ciris-refined"        % CirisVersion,
      "org.tpolecat"          %% "doobie-core"          % DoobieVersion,
      "org.tpolecat"          %% "doobie-hikari"        % DoobieVersion,
      "org.tpolecat"          %% "doobie-postgres"      % DoobieVersion,
      "org.tpolecat"          %% "doobie-refined"       % DoobieVersion,
      "org.flywaydb"          % "flyway-core"           % FlywayVersion,
      "io.chrisdavenport"     %% "random"               % RandomVersion,
      "io.estatico"           %% "newtype"              % NewtypeVersion,
      "com.stripe"            % "stripe-java"           % StripeVersion,
      "io.github.jmcardon"    %% "tsec-http4s"          % TSecVersion,
      "org.scalameta"         %% "munit"                % MUnitVersion % Test
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.11.0" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
  )
  .enablePlugins(JavaAppPackaging)

val Http4sVersion     = "0.21.3"
val CirceVersion      = "0.13.0"
val LogbackVersion    = "1.2.3"
val Fs2Version        = "2.3.0"
val Pulsar4sVersion   = "2.4.6"
val RefinedVersion    = "0.9.13"
val CatsVersion       = "2.1.1"
val CatsEffectVersion = "2.1.4"
val Log4CatsVersion   = "1.0.1"
val CirisVersion      = "1.0.4"
val DoobieVersion     = "0.9.0"
val FlywayVersion     = "6.3.3"
val RandomVersion     = "0.0.2"
val NewtypeVersion    = "0.4.3"
val StripeVersion     = "19.27.0"
val TSecVersion       = "0.2.1"
val MUnitVersion      = "0.7.10"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ymacro-annotations",
  "-Xfatal-warnings"
)
