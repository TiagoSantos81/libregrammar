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
package org.languagetool.rules.fr;

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
public class FrenchStyleRepeatedWordRule extends AbstractStyleRepeatedWordRule {
  
  private static final String SYNONYMS_URL = "https://www.synonymes.com/synonyme.php?mot=";
  
  public FrenchStyleRepeatedWordRule(ResourceBundle messages, Language lang, UserConfig userConfig) {
    super(messages, lang, userConfig);
    super.setCategory(Categories.STYLE.getCategory(messages));
  }

  @Override
  public String getId() {
    return "STYLE_REPEATED_WORD_RULE_FR";
  }

  @Override
  public String getDescription() {
    return "Mots répétés dedans d'un paragraphe";
  }
  
  @Override
  protected String messageSameSentence() {
    return "Possible problème de style. Ce mot se répète dans la même phrase.";
  }
  
  @Override
  protected String messageSentenceBefore() {
    return "Possible problème de style. Ce mot s'a déjà usé dans une phrase antérieure.";
  }
  
  @Override
  protected String messageSentenceAfter() {
    return "Possible problème de style. Ce mot aussi s'use dans une phrase postérieure.";
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
    return (token.matchesPosTagRegex("[VNJA] .+") 
        && !token.matchesPosTagRegex("(Z|V etre|D|C|P) .+")
        && !StringUtils.equalsAny(token.getToken(),
           "plus", "moins",
           "jour", "jours", "semaine", "semaines", "mois", "mois", "an", "ans", "heure", "heures", "minute", "minutes", "seconde ", "secondes",
           "n", "x", "et",
           "cent",
           "mètre", "mètres", "gramme", "grammes", "litre", "litre", "litres", "litres",
           "livres", "dollars", "euros", "yenes"))
        || isUnknownWord(token);
  }

  /* TODO Yet to be truly localized 
   * Pairs of substantive are excluded like "Hand in hand", etc.
   */
  protected boolean isTokenPair(AnalyzedTokenReadings[] tokens, int n, boolean before) {
    if (before) {
      if ((tokens[n-2].hasPosTagStartingWith("N") && tokens[n-1].hasPosTagStartingWith("P")
              && tokens[n].hasPosTagStartingWith("N"))
         ) {
        return true;
      }
    } else {
      if ((tokens[n].hasPosTagStartingWith("N") && tokens[n+1].hasPosTagStartingWith("P")
              && tokens[n+2].hasPosTagStartingWith("N"))
         ) {
        return true;
      }
    }
    return false;
  }

  /* 
   *  set an URL to the French Thesaurus
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
