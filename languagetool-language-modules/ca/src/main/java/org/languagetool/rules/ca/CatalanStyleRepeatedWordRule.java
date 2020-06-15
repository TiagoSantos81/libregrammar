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
package org.languagetool.rules.ca;

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
 * @since 5.0
 * Localized from Spanish by Tiago F. Santos
 */
public class CatalanStyleRepeatedWordRule extends AbstractStyleRepeatedWordRule {
  
  private static final String SYNONYMS_URL = "https://www.softcatala.org/diccionari-de-sinonims/paraula/";
  
  public CatalanStyleRepeatedWordRule(ResourceBundle messages, Language lang, UserConfig userConfig) {
    super(messages, lang, userConfig);
    super.setCategory(Categories.STYLE.getCategory(messages));
  }

  @Override
  public String getId() {
    return "STYLE_REPEATED_WORD_RULE_CA";
  }

  @Override
  public String getDescription() {
    return "Paraules repetides dins d'un paràgraf";
  }
  
  @Override
  protected String messageSameSentence() {
    return "Possible problema d'estil. Aquesta paraula es repeteix en la mateixa frase.";
  }
  
  @Override
  protected String messageSentenceBefore() {
    return "Possible problema d'estil. Aquesta paraula ja es va usar en una frase anterior.";
  }
  
  @Override
  protected String messageSentenceAfter() {
    return "Possible problema d'estil. Aquesta paraula també s'usa en una frase posterior.";
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
    return (token.matchesPosTagRegex("[VNAR].+") 
        && !token.matchesPosTagRegex("(NP|VA|SP|D|C|P).+")
        && !StringUtils.equalsAny(token.getToken(),
           "més", "menys",
           "dia", "dies", "setmana", "setmanes", "mes", "mesos", "any", "anys", "hora", "hores", "minut", "minuts", "segon ", "segons",
           "n", "x", "i",
           "cent",
           "metre", "metres", "gram", "grams", "litre", "litre", "litres", "litres",
           "lliures", "dòlars", "euros", "iens"))
        || isUnknownWord(token);
  }

  /* 
   * Pairs of substantive are excluded like "Mano a Mano", "Hand in hand", etc.
   */
  protected boolean isTokenPair(AnalyzedTokenReadings[] tokens, int n, boolean before) {
    if (before) {
      if ((tokens[n-2].hasPosTagStartingWith("N") && tokens[n-1].hasPosTagStartingWith("SP")
              && tokens[n].hasPosTagStartingWith("N"))
         ) {
        return true;
      }
    } else {
      if ((tokens[n].hasPosTagStartingWith("N") && tokens[n+1].hasPosTagStartingWith("SP")
              && tokens[n+2].hasPosTagStartingWith("N"))
         ) {
        return true;
      }
    }
    return false;
  }

  /* 
   *  set an URL to the Catalan Thesaurus
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
