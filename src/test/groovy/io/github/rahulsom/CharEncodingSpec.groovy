package io.github.rahulsom

import groovy.ui.SystemOutputInterceptor
import org.apache.commons.lang3.text.WordUtils
import spock.lang.Specification

import java.nio.charset.Charset

/**
 * Created by rahul on 8/30/16.
 */
class CharEncodingSpec extends Specification {
  def "runPartOne"() {
    given:
    GroovyShell shell = new GroovyShell()
    def op = new StringBuilder()
    def output = new SystemOutputInterceptor({a, b ->
      op.append(b)
      false
    })
    output.start()
    shell.evaluate(new File('src/main/groovy/CharEncoding.groovy'))
    output.stop()
    new File('build/CharEncoding.txt').newWriter().with {
      write op.toString()
      flush()
      close()
    }

    expect:
    op
  }
  def "runPartTwo"() {
    given:
    GroovyShell shell = new GroovyShell()
    def op = new StringBuilder()
    def output = new SystemOutputInterceptor({a, b ->
      op.append(b)
      false
    })
    output.start()
    shell.evaluate(new File('src/main/groovy/WrongEncoding.groovy'))
    output.stop()
    new File('build/WrongEncoding.txt').newWriter().with {
      write op.toString()
      flush()
      close()
    }

    expect:
    op
  }
  def "runPartThree"() {
    given:
    new File('build/AllCharsets.txt').newWriter().with {
      write WordUtils.wrap(Charset.availableCharsets().keySet().join(', '), 80)
      flush()
      close()
    }

    expect:
    1
  }
}
