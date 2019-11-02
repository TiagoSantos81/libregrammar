/*
 * Copyright 2019 Jim O'Regan <jaoregan@tcd.ie>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.languagetool.tagging.disambiguation.rules.ga;

import org.junit.Test;
import org.languagetool.tagging.ga.Utils;

import static org.junit.Assert.*;

public class UtilsTest {
  @Test
  public void testToLowerCaseIrish() {
    assertEquals("test", Utils.toLowerCaseIrish("TEST"));
    assertEquals("test", Utils.toLowerCaseIrish("Test"));
    assertEquals("t-aon", Utils.toLowerCaseIrish("tAON"));
    assertEquals("n-aon", Utils.toLowerCaseIrish("nAON"));
  }

  @Test
  public void testUnLenited() {
    assertEquals("Kate", Utils.unLenite("Khate"));
    assertEquals("can", Utils.unLenite("chan"));
    assertEquals("ba", Utils.unLenite("bha"));
    assertEquals("b", Utils.unLenite("bh"));
    assertEquals(null, Utils.unLenite("can"));
    assertEquals(null, Utils.unLenite("a"));
  }

  @Test
  public void testUnEclipseChar() {
    // properly eclipsed
    assertEquals("carr", Utils.unEclipseChar("gcarr", 'g', 'c'));
    assertEquals("Carr", Utils.unEclipseChar("gCarr", 'g', 'c'));
    // improperly eclipsed
    assertEquals("Carr", Utils.unEclipseChar("G-carr", 'g', 'c'));
    assertEquals("Carr", Utils.unEclipseChar("Gcarr", 'g', 'c'));
    assertEquals("CARR", Utils.unEclipseChar("GCARR", 'g', 'c'));
    // not eclipsed
    assertEquals(null, Utils.unEclipseChar("carr", 'g', 'c'));
  }
}
