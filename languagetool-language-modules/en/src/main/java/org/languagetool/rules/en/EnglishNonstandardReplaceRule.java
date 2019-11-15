/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.en;

import org.languagetool.language.English;
import org.languagetool.rules.AbstractSimpleReplaceRule2;
import org.languagetool.rules.Example;
import org.languagetool.rules.ITSIssueType;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.languagetool.tools.Tools;
import java.net.URL;
/**
 * A rule that matches words or phrases which should not be used and suggests
 * correct ones instead.
 *
 * @author Marcin Miłkowski
 */
public class EnglishNonstandardReplaceRule extends AbstractSimpleReplaceRule2 {

  public static final String ENGLISH_NONSTANDARD_REPLACE_RULE = "EN_NONSTANDARD_SIMPLE_REPLACE";

  private static final String FILE_NAME = "/en/nonstandard.txt";
  private static final Locale EN_LOCALE = new Locale("en"); // locale used on case-conversion

  @Override
  public final String getFileName() {
    return FILE_NAME;
  }

  public EnglishNonstandardReplaceRule(ResourceBundle messages) throws IOException {
    super(messages, new English());
    setLocQualityIssueType(ITSIssueType.LocaleViolation);
    addExamplePair(Example.wrong("<marker>If I was you</marker> I would be embarassed."),
                   Example.fixed("<marker>If I were you</marker> I would be embarassed."));
  }

  @Override
  public final String getId() {
    return ENGLISH_NONSTANDARD_REPLACE_RULE;
  }

  @Override
  public String getDescription() {
    return "Nonstandard words and phrases in English";
  }

  @Override
  public String getShort() {
    return "Nonstandard word or phrase";
  }
  
  @Override
  public String getSuggestion() {
    return "Nonstandard English may use '$match' but the standard form of this phrase is '$suggestions'.";
  }

  @Override
  public String getSuggestionsSeparator() {
    return " or ";
  }

  @Override
  public boolean isCaseSensitive() {
    return false;
  }

  @Override
  public URL getUrl() {
    return Tools.getUrl("https://www.thoughtco.com/what-is-nonstandard-english-1691438");
  }

  @Override
  public Locale getLocale() {
    return EN_LOCALE;
  }

}
