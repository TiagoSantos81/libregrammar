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
import org.languagetool.rules.Categories;
import org.languagetool.rules.Example;
import org.languagetool.rules.ITSIssueType;
import org.languagetool.tools.Tools;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import java.net.URL;

/**
 * A rule that matches words which are complex and suggests easier to understand alternatives. 
 *
 * @author Tiago F. Santos 
 * @since 4.8
 */
public class EnglishEggcornsRule extends AbstractSimpleReplaceRule2 {

  public static final String EN_EGGCORNS_REPLACE = "EN_EGGCORNS_REPLACE";

  private static final String FILE_NAME = "/en/eggcorns.txt";
  private static final Locale EN_LOCALE = new Locale("en");  // locale used on case-conversion

  @Override
  public final String getFileName() {
    return FILE_NAME;
  }

  public EnglishEggcornsRule(ResourceBundle messages) throws IOException {
    super(messages, new English());
    // setDefaultOff(); 
    super.setCategory(Categories.CONFUSED_WORDS.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Misspelling);
    addExamplePair(Example.wrong("<marker>statue of limitations</marker>"),
                   Example.fixed("<marker>statute of limitations</marker>"));
  }

  @Override
  public final String getId() {
    return EN_EGGCORNS_REPLACE;
  }

  @Override
  public String getDescription() {
    return "Eggcorns";
  }

  @Override
  public String getShort() {
    return "Eggcorn";
  }

  @Override
  public String getSuggestion() {
    return "'$match' probably is a misheard expression. The correct phrase is $suggestions";
  }

  @Override
  public String getSuggestionsSeparator() {
    return " or ";
  }

  @Override
  public URL getUrl() {
    return Tools.getUrl("https://en.wikipedia.org/wiki/Eggcorn");
  }

  @Override
  public Locale getLocale() {
    return EN_LOCALE;
  }

}
