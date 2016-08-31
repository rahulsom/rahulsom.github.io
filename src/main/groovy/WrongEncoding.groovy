import java.nio.charset.Charset
void testWrongEncoding(String text) {
  def theBytes = text.getBytes(Charset.forName('ISO-8859-1'))
  def reparse = new String(theBytes, 'UTF-8')
  println "${text}: ${theBytes.encodeHex()} --> ${reparse}"
}
println "Wrong Encoding"
println "Wrong Encoding".replaceAll(/./,'=')
testWrongEncoding('Happy New Year!')
testWrongEncoding('¡Feliz Año Nuevo!')
testWrongEncoding('新年あけましておめでとうございます！')
testWrongEncoding('KYPHON® Balloon Kyphoplasty')
