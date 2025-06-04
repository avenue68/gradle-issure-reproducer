package com.example

import org.gradle.api.Plugin
import org.gradle.api.Project

class ExamplePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("exampleTask") {
            val exampleContent =
                javaClass
                    .classLoader
                    .getResource("META-INF/com/example/example.txt")
                    ?.readText()

            if (exampleContent == null || !exampleContent.startsWith("**test**")) {
                error("Couldn't retrieve resource.")
            }
        }
    }
}
