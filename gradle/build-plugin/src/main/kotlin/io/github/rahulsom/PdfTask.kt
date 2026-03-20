package io.github.rahulsom

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.jsoup.Jsoup
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.FileOutputStream

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

        val document = Jsoup.parse(html, "UTF-8").also {
            it.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml)
            it.setBaseUri(html.parentFile.toURI().toString())
        }
        val renderer = ITextRenderer()
        renderer.sharedContext.also {
            it.isPrint = true
            it.isInteractive = false
        }
        val baseUri = html.parentFile.toURI().toString()
        println("Base URI: $baseUri")
        renderer.setDocumentFromString(document.html(), baseUri)
        renderer.layout()
        FileOutputStream(pdf).use {
            renderer.createPDF(it)
        }
    }
}
