package com.example

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification
import spock.lang.TempDir

import java.nio.file.Files
import java.nio.file.Path

class ExamplePluginSpec extends Specification {

    @TempDir
    Path testDir

    def "Build fails"() {
        given:
        def buildScript = testDir.resolve("build.gradle.kts")
        Files.createFile(buildScript)
        Files.writeString(
            buildScript,
            """
            plugins {
                id("com.example.example-plugin")
            }
            """
        )

        when:
        def firstResult = GradleRunner.create()
            .withProjectDir(testDir.toFile())
            .withArguments("exampleTask")
            .withPluginClasspath()
            .withDebug(true)
            .forwardOutput()
            .build()

        then:
        firstResult.task(":exampleTask").outcome == TaskOutcome.UP_TO_DATE
    }
}
