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
public class FrenchReadabilityRule extends ReadabilityRule {
  
  boolean tooEasyTest;

  public FrenchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest) {
    this (messages, lang, userConfig, tooEasyTest, -1, false);
  }
  
  public FrenchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest, int level) {
    this (messages, lang, userConfig, tooEasyTest, level, false);
  }
  
  public FrenchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, boolean tooEasyTest, boolean defaultOn) {
    this (messages, lang, userConfig, tooEasyTest, -1, defaultOn);
  }
  
  public FrenchReadabilityRule(ResourceBundle messages, Language lang, UserConfig userConfig, 
      boolean tooEasyTest, int level, boolean defaultOn) {
    super(messages, lang, userConfig, tooEasyTest, level, defaultOn);
    super.setCategory(new Category(new CategoryId("TEXT_ANALYSIS"), "Analyse de Texte", Location.INTERNAL, false));
    this.tooEasyTest = tooEasyTest;
  }
  
  @Override
  public String getId() {
    return getId(tooEasyTest);
  }

  @Override
  public String getId(boolean tooEasyTest) {
    if(tooEasyTest) {
      return "READABILITY_RULE_SIMPLE_FR";
    } else {
      return "READABILITY_RULE_DIFFICULT_FR";
    }
  }

  @Override
  public String getDescription() {
    if(tooEasyTest) {
      return "Lisibilité: texte trop simple";
    } else {
      return "Lisibilité: texte trop complexe";
    }
  }

  private static String printMessageLevel(int level) {
    String sLevel = null;
    if (level == 0) {
      sLevel = "Très complexe";
    } else if (level == 1) {
      sLevel = "Complexe";
    } else if (level == 2) {
      sLevel = "Moyennement complexe";
    } else if (level == 3) {
      sLevel = "À moyen terme";
    } else if (level == 4) {
      sLevel = "Moyennement simple";
    } else if (level == 5) {
      sLevel = "Simple";
    } else if (level == 6) {
      sLevel = "Très simple";
    }
    if (sLevel != null) {
      return " {Niveau " + level + ": " + sLevel + "}";
    }
    return "";
  }
  
  @Override
  protected String getMessage(int level, int FRE, int ASL, int ASW) {
    String simple;
    String few;

    if(tooEasyTest) {
      simple = "facile";
      few = "peu de";
    } else {
      simple = "difficile";
      few = "beaucoup de";
    }
    return "Lisibilité {FRE: " + FRE +", ASL: " + ASL + ", ASW: " + ASW + "}: Le texte de ce paragraphe est trop " + simple + printMessageLevel(level) + ". Il y a "
        + few + " mots par phrase et " + few + " syllabes par mot.";
  }

  @Override
  public String getConfigureText() {
    return "Niveau de lisibilité 0 (très difficile) à 6 (très facile):";
  }

  @Override
          /* Equation for readability
           * FRE = Flesch-Reading-Ease
           * ASL = Average Sentence Length
           * ASW = Average Number of Syllables per Word
           * English:    FRE= 206,835 - ( 1,015 * ASL ) - ( 84,6 * ASW )
           * French:     FRE= 207     - ( 1.015 * ASL ) - ( 73.6 * ASW )
           * Garais, E. G. (2011). Web Applications Readability. Journal of Information Systems & Operations Management, 5(1), 114-120.
           */
  protected double getFleschReadingEase(double ASL, double ASW) {
    return 207 - (1.015 * ASL) - ( 73.6 * ASW );  //  French
  }
  
  private static boolean isVowel(char c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y' ||
            c == 'é' || c == 'à' || c == 'è' || c == 'ù' ||
            c == 'É' || c == 'à' || c == 'È' || c == 'Ù' ||
            c == 'â' || c == 'ê' || c == 'î' || c == 'ô' || c == 'û' ||
            c == 'Â' || c == 'Ê' || c == 'Î' || c == 'Ô' || c == 'Û' ||
            c == 'ë' || c == 'ï' || c == 'ü' ||
            c == 'Ë' || c == 'Ï' || c == 'Ü');
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
        } else if((c == 'a' && (cl == 'i' || cl == 'u' || cl == 'n')) ||
            (c == 'e' && (cl == 'u' || cl == 'r' || cl == 'z' || cl == 'm' || cl == 'n')) ||
            (c == 'i' && (cl == 's' || cl == 'n')) ||
            (c == 'o' && (cl == 'i' || cl == 'u' || cl == 'n')) ||
            (c == 'u' && (cl == 'e' || cl == 'i' || cl == 'n' || cl == 'é')) ||
            (c == 'ú' && (cl == 'a' || cl == 'e' || cl == 'o'))) {
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
