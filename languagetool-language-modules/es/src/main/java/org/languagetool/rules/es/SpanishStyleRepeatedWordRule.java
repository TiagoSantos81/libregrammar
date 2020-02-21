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
package org.languagetool.rules.es;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.AbstractStyleRepeatedWordRule;
import org.languagetool.rules.Categories;
import org.languagetool.rules.Example;

/**
 * A rule checks the appearance of same words in a sentence or in two consecutive sentences.
 * Only substantive, verbs and adjectives are checked.
 * This rule detects no grammar error but a stylistic problem
 * @author Fred Kruse
 * Localized from German by Tiago F. Santos
 */
public class SpanishStyleRepeatedWordRule extends AbstractStyleRepeatedWordRule {
  
  private static final String SYNONYMS_URL = "https://www.sinonimosonline.com/";
  
  public SpanishStyleRepeatedWordRule(ResourceBundle messages, Language lang, UserConfig userConfig) {
    super(messages, lang, userConfig);
    super.setCategory(Categories.STYLE.getCategory(messages));
    // addExamplePair(Example.wrong("I go to the supermarket, then I <marker>go</marker> home."), 
    //                Example.fixed("I go to the supermarket, then I drive home."));
    /* FIXME
[INFO] Results:
[INFO] 
[ERROR] Failures: 
[ERROR]   RuleTest.testJavaRules:58->testExamples:84->testIncorrectExamples:106 Did not get the expected rule match for the incorrect example sentence:
Text: I go to the supermarket, then I go home.
Rule: STYLE_REPEATED_WORD_RULE_EN
Matches: [STYLE_REPEATED_WORD_RULE_EN:2-4:Possible style problem: The word is already used in the same sentence., STYLE_REPEATED_WORD_RULE_EN:32-34:Possible style problem: The word is already used in the same sentence.] expected:<1> but was:<2>
[INFO] 
[INFO] LanguageTool stand-alone GUI ....................... FAILURE [ 43.762 s] */

  }

  @Override
  public String getId() {
    return "STYLE_REPEATED_WORD_RULE_ES";
  }

  @Override
  public String getDescription() {
    return "Palabras repetidas dentro de un párrafo";
  }
  
  @Override
  protected String messageSameSentence() {
    return "Posible problema de estilo. Esta palabra se repite en la misma frase.";
  }
  
  @Override
  protected String messageSentenceBefore() {
    return "Posible problema de estilo. Esta palabra ya se usó en una frase anterior.";
  }
  
  @Override
  protected String messageSentenceAfter() {
    return "Posible problema de estilo. Esta palabra también se usa en una frase posterior.";
  }

  /*
   * Is a unknown word (has only letters and no PosTag) 
   */
  private static boolean isUnknownWord(AnalyzedTokenReadings token) {
    return token.isPosTagUnknown() && token.getToken().length() > 2 && token.getToken().matches("^[A-Za-záéààèêôûãõçāūÄÖÜäöüß]+$");
  }

  /*
   * Only substantive, names, verbs and adjectives are checked
   */
  protected boolean isTokenToCheck(AnalyzedTokenReadings token) {
    return (token.matchesPosTagRegex("[VNAR].+|FW") 
        && !token.matchesPosTagRegex("(NP|VA|SP|D|C|P).+")
        && !StringUtils.equalsAny(token.getToken(),
           "más", "menos",
           "día", "días", "semana", "semanas", "mes", "meses", "año", "años", "hora", "horas", "minuto", "minutos", "segundo ", "segundos",
           "n", "x", "y",
           "ciento",
           "metro", "metros", "gramo", "gramos", "litro", "litro", "litros", "litros",
           "libras", "dólares", "euros", "yenes"))
        || isUnknownWord(token);
  }

  /* TODO Yet to be truly localized 
   * Pairs of substantive are excluded like "Mano a Mano", "Hand in hand", etc.
   */
  protected boolean isTokenPair(AnalyzedTokenReadings[] tokens, int n, boolean before) {
    if (before) {
      if ((tokens[n-2].hasPosTagStartingWith("N") && tokens[n-1].hasPosTagStartingWith("SP")
              && tokens[n].hasPosTagStartingWith("N"))
         // || (!tokens[n-2].getToken().equals("mano") && !tokens[n-1].getToken().equals("in") && !tokens[n].getToken().equals("hand"))
         ) {
        return true;
      }
    } else {
      if ((tokens[n].hasPosTagStartingWith("N") && tokens[n+1].hasPosTagStartingWith("P")
              && tokens[n+2].hasPosTagStartingWith("N"))
         // || (!tokens[n].getToken().equals("hand") && !tokens[n-1].getToken().equals("in") && !tokens[n + 2].getToken().equals("hand"))
         ) {
        return true;
      }
    }
    return false;
  }

  /* 
   *  set an URL to the Spanish Thesaurus
   */
  @Override
  protected URL setURL(AnalyzedTokenReadings token) throws MalformedURLException {
    if (token != null) {
      List<AnalyzedToken> readings = token.getReadings();
      List<String> lemmas = new ArrayList<>();
      for (AnalyzedToken reading : readings) {
        String lemma = reading.getLemma();
        if (lemma != null) {
          lemmas.add(lemma);
        }
      }
      if (lemmas.size() == 1) {
        return new URL(SYNONYMS_URL + lemmas.get(0));
      }
      return new URL(SYNONYMS_URL + token.getToken());
    }
    return null;
  }

}
