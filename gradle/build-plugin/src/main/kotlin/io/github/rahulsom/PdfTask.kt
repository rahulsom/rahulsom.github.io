package io.github.rahulsom

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.print.PrintOptions
import java.io.FileOutputStream
import java.util.Base64

abstract class PdfTask : DefaultTask() {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val htmlFile: RegularFileProperty

    @get:OutputFile
    abstract val pdfFile: RegularFileProperty

    @TaskAction
    fun generatePdf() {
        val html = htmlFile.get().asFile
        val pdf = pdfFile.get().asFile

        val options = ChromeOptions()
        options.addArguments("--headless=new")

        val driver = ChromeDriver(options)

        try {
            val baseUri = html.toURI().toString()
            println("Base URI: $baseUri")
            driver.get(baseUri)

            val printOptions = PrintOptions()
            printOptions.background = true
            val pdfContent = driver.print(printOptions)

            val pdfBytes = Base64.getDecoder().decode(pdfContent.content)
            FileOutputStream(pdf).use {
                it.write(pdfBytes)
            }
        } finally {
            driver.quit()
        }
    }
}
