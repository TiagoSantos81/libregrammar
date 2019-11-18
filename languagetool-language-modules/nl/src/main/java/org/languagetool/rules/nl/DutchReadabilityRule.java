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

package org.languagetool.rules.nl;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.Category;
import org.languagetool.rules.CategoryId;
import org.languagetool.rules.ReadabilityRule;
import org.languagetool.rules.Category.Location;

/**
 * A rule that checks the readability of Portuguese text (using the Flesch-Reading-Ease Formula)
 * If tooEasyTest == true, the rule tests if paragraph level &gt; level (readability is too easy)
 * If tooEasyTest == false, the rule tests if paragraph level &lt; level (readability is too difficult)
 * @author Fred Kruse localized by Tiago F. Santos
 * @since 4.8
 */
public class DutchReadabilityRule extends ReadabilityRule {
  
  boolean tooEasyTest;

  public DutchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest) {
    this (messages, lang, userConfig, tooEasyTest, -1, false);
  }
  
  public DutchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest, int level) {
    this (messages, lang, userConfig, tooEasyTest, level, false);
  }
  
  public DutchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest, boolean defaultOn) {
    this (messages, lang, userConfig, tooEasyTest, -1, defaultOn);
  }
  
  public DutchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, 
      boolean tooEasyTest, int level, boolean defaultOn) {
    super(messages, lang, userConfig, tooEasyTest, level, defaultOn);
    super.setCategory(new Category(new CategoryId("TEXT_ANALYSIS"), "Tekst analyse", Location.INTERNAL, false));
    this.tooEasyTest = tooEasyTest;
  }
  
  @Override
  public String getId() {
    return getId(tooEasyTest);
  }

  @Override
  public String getId(boolean tooEasyTest) {
    if(tooEasyTest) {
      return "READABILITY_RULE_SIMPLE_NL";
    } else {
      return "READABILITY_RULE_DIFFICULT_NL";
    }
  }

  @Override
  public String getDescription() {
    if(tooEasyTest) {
      return "Leesbaarheid: tekst te eenvoudig";
    } else {
      return "Leesbaarheid: tekst te moeilijk";
    }
  }

  private static String printMessageLevel(int level) {
    String sLevel = null;
    if (level == 0) {
      sLevel = "Heel complex";
    } else if (level == 1) {
      sLevel = "Complex";
    } else if (level == 2) {
      sLevel = "Matig complex";
    } else if (level == 3) {
      sLevel = "Middellange termijn";
    } else if (level == 4) {
      sLevel = "Matig simpel";
    } else if (level == 5) {
      sLevel = "Simpel";
    } else if (level == 6) {
      sLevel = "Heel simpel";
    }
    if (sLevel != null) {
      return " {Peil " + level + ": " + sLevel + "}";
    }
    return "";
  }
  
  @Override
  protected String getMessage(int level, int FRE, int ASL, int ASW) {
    String simple;
    String few;

    if(tooEasyTest) {
      simple = "gemakkelijk";
      few = "weinig";
    } else {
      simple = "moeilijk";
      few = "veel";
    }
    return "Leesbaarheid {FRE: " + FRE +", ASL: " + ASL + ", ASW: " + ASW + "}: De tekst van deze paragraaf is dat ook " + simple + printMessageLevel(level) + ". Er is "
        + few + " woorden per zin en " + few + " lettergrepen per woord.";
  }

  @Override
  public String getConfigureText() {
    return "Leesbaarheid niveau 0 (heel moeilijk) tot 6 (heel gemakkelijk):";
  }

  @Override
          /* Equation for readability
           * FRE = Flesch-Reading-Ease
           * ASL = Average Sentence Length
           * ASW = Average Number of Syllables per Word
           * English:    FRE= 206,835 - ( 1.015 * ASL ) - ( 84,6 * ASW )
           * Dutch:      FRE= 206.84  - ( 0.93  * ASL ) - ( 77.0 * ASW )
           * https://nl.wikipedia.org/wiki/Leesbaarheid
           */
  protected double getFleschReadingEase(double ASL, double ASW) {
    return 206.84 - (0.93 * ASL) - ( 77.0 * ASW );  //  Dutch
  }
  
  private static boolean isVowel(char c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y' ||
            c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' ||
            c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú' ||
            c == 'à' || c == 'è' || c == 'ì' || c == 'ò' || c == 'ù' ||
            c == 'À' || c == 'è' || c == 'ì' || c == 'Ò' || c == 'Ù' ||
            c == 'â' || c == 'ê' || c == 'î' || c == 'ô' || c == 'û' ||
            c == 'Â' || c == 'Ê' || c == 'Î' || c == 'Ô' || c == 'Û' ||
            c == 'ä' || c == 'ë' || c == 'ï' || c == 'ö' || c == 'ü' ||
            c == 'Ä' || c == 'Ë' || c == 'Ï' || c == 'ü' || c == 'Ü');
  }
  
  @Override
  protected int simpleSyllablesCount(String word) {
    if(word.length() == 0) {
      return 0;
    }
    int nSyllables = 0;
    if(isVowel(word.charAt(0))) {
      nSyllables++;
    }
    boolean lastDouble = false;
    for (int i = 1; i < word.length(); i++) {
      char c = word.charAt(i);
      if(isVowel(c)) {
        char cl = word.charAt(i - 1);
        if(lastDouble) {
          nSyllables++;
          lastDouble = false;
        } else if((c == 'a' && (cl == 'a' || cl == 'i')) ||
                  (c == 'e' && (cl == 'e' || cl == 'i' ||cl == 'u')) ||
                  (c == 'i' && (cl == 'j' || cl == 'e')) ||
                  (c == 'o' && (cl == 'e' || cl == 'i' || cl == 'o' || cl == 'u' || cl == 'w' )) ||
                  (c == 'u' && (cl == 'i' || cl == 'w'))) {
          lastDouble = true;
        } else {
          nSyllables++;
          lastDouble = false;
        }
      } else {
        lastDouble = false;
      }
    }
    return nSyllables == 0 ? 1 : nSyllables;
  }



}
