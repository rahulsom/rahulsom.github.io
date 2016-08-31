import java.nio.charset.Charset

void reportOnText(String text) {
  final encodings = [
      'ASCII', 'ISO-8859-1', 'windows-1251', 'UTF-8', 'UTF-16', 'UTF-32'
  ]
  println ''
  println text
  println text.replaceAll(/./, '=')
  encodings.each { enc ->
    def theBytes = text.getBytes(Charset.forName(enc))
    def reparse = new String(theBytes, enc)
    println "${enc.padRight(12)}: ${theBytes.encodeHex()} --> ${reparse}"
  }
}

reportOnText('Happy New Year!')
reportOnText('¡Feliz Año Nuevo!')
reportOnText('新年あけましておめでとうございます！')
reportOnText('KYPHON® Balloon Kyphoplasty')