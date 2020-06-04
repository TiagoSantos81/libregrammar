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
package org.languagetool.rules.pt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.AbstractStyleRepeatedWordRule;
import org.languagetool.rules.Categories;
import org.languagetool.rules.Example;

/**
 * A rule checks the appearance of same words in a sentence or in two consecutive sentences. Superseeds Portuguese REPEATED_WORDS.
 * Only substantive, verbs and adjectives are checked.
 * This rule detects no grammar error but a stylistic problem
 * @author Fred Kruse
 * @since 5.0
 * Localized from German by Tiago F. Santos
 */
public class PortugueseStyleRepeatedWordRule extends AbstractStyleRepeatedWordRule {
  
  private static final String SYNONYMS_URL = "https://www.sinonimos.com.br/";

  private static final Pattern UNIDADES_DE_MEDIDA = Pattern.compile("(?:(?:[khdcmnµfYZEPTGM]|da)?(?:[gmlsJNWCVSFTHΩ]|Hz|cd|lm|mol|Pa|Wb|rad|sr|lx|Bq|Gy|Sv|kat|Np|eV)(?:⁻)?[23¹²³]?|º[CFK]|cv|k?cal|mmHg|atm|bpm|ton|kWh|GWa|MWd|MWh|mAh|min|ha)");
  private static final Pattern UNIDADES_DE_MEDIDA_EXTENSO = Pattern.compile("(?:quil[oó]|dec[ií]|cent[ií]|mil[ií]|micr[oó])?(grama|metro|litro|segundo)s?|hectar(es)?|(grau|milha|(quilo)?tonelada)s?");
  private static final Pattern DIVISAS = Pattern.compile("(?:bol[ií]vares|c(?:êntimos|oroas)|d(?:inares|ólares)|euros|f(?:rancos|lorins)|i(?:uanes|enes)|kwanzas|libras|meticais|p(?:atacas|esos)|r(?:andes|ublos|rupias)|zlótis|[€£¥$₽])");
  private static final Pattern EXPRESSOES_DE_TEMPO = Pattern.compile("(?:minuto|hora|dia|se(?:gundo|mana|mestre)|(?:bi|tri|quadri)mestre|ano|d(?:écada|ecénio)|mil[éê]nio|temp(?:o|orada)|época)s?|m(?:ês|eses)");
  private static final Pattern MEDIDAS = Pattern.compile("[\\d,. ]*['\\\"/\\|%º°′″‴]*(?:(?:[khdcmnµfYZEPTGM]|da)?(?:[gmlsJNWCVSFTHΩ]|Hz|cd|lm|mol|Pa|Wb|rad|sr|lx|Bq|Gy|Sv|kat|Np|eV)(?:⁻)?[23¹²³]?|º[CFK]|cv|k?cal|mmHg|atm|bpm|ton|kWh|GWa|MWd|MWh|mAh|min|ha){0,1}");
  private static final Pattern ANTI_1_1 = Pattern.compile("cara|dia|face|frente|passo|porta|tempos?|terra|t[éê]te"); 

  public PortugueseStyleRepeatedWordRule(ResourceBundle messages, Language lang, UserConfig userConfig) {
    super(messages, lang, userConfig);
    super.setCategory(Categories.STYLE.getCategory(messages));

  }

  @Override
  public String getId() {
    return "STYLE_REPEATED_WORD_RULE_PT";
  }

  @Override
  public String getDescription() {
    return "Palavras repetidas dentro de um parágrafo";
  }
  
  @Override
  protected String messageSameSentence() {
    return "Possível problema de estilo. Esta palavra repete-se na mesma frase.";
  }
  
  @Override
  protected String messageSentenceBefore() {
    return "Possível problema de estilo. Esta palavra já se utilizou numa frase anterior.";
  }
  
  @Override
  protected String messageSentenceAfter() {
    return "Possível problema de estilo. Esta palavra também é utilizada numa frase posterior.";
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
    return (!token.matchesPosTagRegex("(?:C|D|NP|P|SP|Z).+|R[GN].*|_PUNCT|_GN_|UNKNOWN")
        && !StringUtils.equalsAny(token.getToken(), "mais", "menos", "e", "ou", "são", "n", "x", "e", "-")
        && !StringUtils.equalsAny(getLemma(token), "ser", "estar", "ter", "haver", "ficar") // passive voice auxiliaries
        && !StringUtils.contains(getLemma(token), ' ') // multiwords
        && !UNIDADES_DE_MEDIDA.matcher(token.getToken()).matches()
        && !UNIDADES_DE_MEDIDA_EXTENSO.matcher(token.getToken()).matches()
        && !MEDIDAS.matcher(token.getToken()).matches()
        && !DIVISAS.matcher(token.getToken()).matches()
        && !EXPRESSOES_DE_TEMPO.matcher(token.getToken()).matches())
        || isUnknownWord(token);
  }

  /* 
   * Pairs of substantive are excluded like "Mano a Mano", "Hand in hand", etc.
   */
  protected boolean isTokenPair(AnalyzedTokenReadings[] tokens, int n, boolean before) {
    if (before) {
      if (
          (ANTI_1_1.matcher(tokens[n-2].getToken()).matches()
       && StringUtils.equalsAny(tokens[n-1].getToken(), "a", "à", "ao")
       && tokens[n-2].getToken().toLowerCase().equals(tokens[n].getToken().toLowerCase()))
       
       || (StringUtils.equalsAny(tokens[n-1].getToken(), "é", "são", "a", "e", "após", "por", "em", "para", "pra")
       && tokens[n-2].getToken().toLowerCase().equals(tokens[n].getToken().toLowerCase()))
       
       || (StringUtils.equalsAny(tokens[n-2].getToken(), "chupa", "chupas")
       && tokens[n-1].getToken().equals("-")
       && StringUtils.equalsAny(tokens[n].getToken(), "chupa", "chupas"))

       || (tokens[n-2].getToken().equals("p")
       && tokens[n-1].getToken().equals(".")
       && tokens[n].getToken().equals("p"))
         ) {
        return true;
      }
    } else {
      if (
          (ANTI_1_1.matcher(tokens[n].getToken()).matches()
       && StringUtils.equalsAny(tokens[n+1].getToken(), "a", "à", "ao")
       && tokens[n+2].getToken().toLowerCase().equals(tokens[n].getToken().toLowerCase()))
       
       || (StringUtils.equalsAny(tokens[n+1].getToken(), "é", "são", "a", "e", "após", "por")
       && tokens[n+2].getToken().toLowerCase().equals(tokens[n].getToken().toLowerCase()))
       
       || (StringUtils.equalsAny(tokens[n+2].getToken(), "chupa", "chupas")
       && tokens[n+1].getToken().equals("-")
       && StringUtils.equalsAny(tokens[n].getToken(), "chupa", "chupas"))
       
       || (tokens[n+3].getToken().equals("de")
       && tokens[n+2].getToken().toLowerCase().equals(tokens[n].getToken().toLowerCase())
       && StringUtils.equalsAny(tokens[n+1].getToken(), "em", "para", "pra"))
       
       || (tokens[n+2].getToken().equals("p")
       && tokens[n+1].getToken().equals(".")
       && tokens[n].getToken().equals("p"))
         ) {
        return true;
      }
    }
    return false;
  }

  private String getLemma(AnalyzedTokenReadings token) {
    if (token != null) {
      List<AnalyzedToken> readings = token.getReadings();
      for (AnalyzedToken reading : readings) {
        String lemma = reading.getLemma();
        if (lemma != null) {
          return lemma;
        }
      }
    }
    return null;
  }

  /* 
   *  set an URL to the Portuguese Thesaurus
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
