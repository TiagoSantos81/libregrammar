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
public class EnglishSimpleGrammarRule extends AbstractSimpleReplaceRule2 {

  public static final String EN_SIMPLE_GRAMMAR_REPLACE = "EN_SIMPLE_GRAMMAR_REPLACE";

  private static final String FILE_NAME = "/en/common_grammar_mistakes.txt";
  private static final Locale EN_LOCALE = new Locale("en");  // locale used on case-conversion

  @Override
  public final String getFileName() {
    return FILE_NAME;
  }

  public EnglishSimpleGrammarRule(ResourceBundle messages) throws IOException {
    super(messages, new English());
    super.setCategory(Categories.GRAMMAR.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Grammar);
    addExamplePair(Example.wrong("<marker>their had</marker>"),
                   Example.fixed("<marker>there had</marker>"));
  }

  @Override
  public final String getId() {
    return EN_SIMPLE_GRAMMAR_REPLACE;
  }

  @Override
  public String getDescription() {
    return "'1. Common grammar mistakes'";
  }

  @Override
  public String getShort() {
    return "Possible grammar mistake";
  }

  @Override
  public String getSuggestion() {
    return "'$match' is a common grammar mistake, but this is a generic rule. Please, verify and consider using $suggestions";
  }

  @Override
  public String getSuggestionsSeparator() {
    return " or ";
  }

  @Override
  public URL getUrl() {
    return Tools.getUrl("https://en.wikipedia.org/wiki/Wikipedia:Lists_of_common_misspellings/Grammar_and_miscellaneous");
  }

  @Override
  public Locale getLocale() {
    return EN_LOCALE;
  }

}
