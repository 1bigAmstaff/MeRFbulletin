package com.example.merfbulletin

import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.InputStream

fun readDocxFile(inputStream: InputStream): String {
    val document = XWPFDocument(inputStream)
    val paragraphs = document.paragraphs
    val stringBuilder = StringBuilder()
    for (paragraph in paragraphs) {
        stringBuilder.append(paragraph.text).append("\n")
    }
    return stringBuilder.toString()
}