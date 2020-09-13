/* LanguageTool, a natural language style checker 
 * Copyright (C) 2009 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.language;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.*;
import org.languagetool.rules.Rule;
import org.languagetool.rules.UppercaseSentenceStartRule;
import org.languagetool.rules.ca.*;

public class ValencianCatalan extends Catalan {

  @Override
  public String getName() {
    return "Catalan (Valencian)";
  }

  @Override
  public String[] getCountries() {
    return new String[]{"ES"};
  }

  @Override
  public String getVariant() {
    return "valencia";
  }
  
  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
    return Arrays.asList(
            new CommaWhitespaceRule(messages, 
            		Example.wrong("A parer seu<marker> ,</marker> no era veritat."),
            		Example.fixed("A parer seu<marker>,</marker> no era veritat.")),
            new DoublePunctuationRule(messages),
            new CatalanUnpairedBracketsRule(messages, this),
            new UppercaseSentenceStartRule(messages, this,
            		Example.wrong("Preus de venda al públic. <marker>han</marker> pujat molt."),
            		Example.fixed("Preus de venda al públic. <marker>Han</marker> pujat molt.")),
            new MultipleWhitespaceRule(messages, this),
            new SentenceWhitespaceRule(messages),
            new WhiteSpaceBeforeParagraphEnd(messages, this),
            new WhiteSpaceAtBeginOfParagraph(messages),
            new EmptyLineRule(messages, this),
            new LongSentenceRule(messages, userConfig, -1, true),
            new LongParagraphRule(messages, this, userConfig),
            new PunctuationMarkAtParagraphEnd(messages, this),
            new PunctuationMarkAtParagraphEnd2(messages, this),
            // specific to Catalan:
            new CatalanWordRepeatRule(messages, this),
            new MorfologikCatalanSpellerRule(messages, this, userConfig, altLanguages),
            new CatalanUnpairedQuestionMarksRule(messages, this),
            new CatalanUnpairedExclamationMarksRule(messages, this),
            new AccentuationCheckRule(messages),
            new CatalanWrongWordInContextRule(messages),
            new CatalanWrongWordInContextDiacriticsRule(messages),
            new SimpleReplaceVerbsRule(messages, this),
            new SimpleReplaceBalearicRule(messages),
            new SimpleReplaceRule(messages),
            new ReplaceOperationNamesRule(messages, this),
            // Valencian DNV
            new SimpleReplaceDNVRule(messages, this),
            new SimpleReplaceDNVColloquialRule(messages, this),
            new SimpleReplaceDNVSecondaryRule(messages, this),
            new SimpleReplaceDiacriticsIEC(messages),
            new SimpleReplaceDiacriticsTraditional(messages),
            new SimpleReplaceAnglicism(messages),
            new PronomFebleDuplicateRule(messages),
            new CatalanStyleRepeatedWordRule(messages, this, userConfig)
    );
  }

  @Override
  public List<String> getDefaultEnabledRulesForVariant() {
    List<String> rules = Arrays.asList("EXIGEIX_VERBS_VALENCIANS", "EXIGEIX_ACCENTUACIO_VALENCIANA",
        "EXIGEIX_POSSESSIUS_U", "EXIGEIX_VERBS_EIX", "EXIGEIX_VERBS_ISC", "PER_PER_A_INFINITIU", "FINS_EL_AVL");
    return Collections.unmodifiableList(rules);
  }

  @Override
  public List<String> getDefaultDisabledRulesForVariant() {
    List<String> rules = Arrays.asList("EXIGEIX_VERBS_CENTRAL", "EXIGEIX_ACCENTUACIO_GENERAL", "EXIGEIX_POSSESSIUS_V",
        "EVITA_PRONOMS_VALENCIANS", "EVITA_DEMOSTRATIUS_EIXE", "VOCABULARI_VALENCIA", "EXIGEIX_US", "FINS_EL_GENERAL");
    return Collections.unmodifiableList(rules);
  }
  
  @Override
  protected int getPriorityForId(String id) {
    switch (id) {
      case "CA_SIMPLE_REPLACE_BALEARIC": return 100;
      case "INCORRECT_EXPRESSIONS": return 50;
      case "MOTS_NO_SEPARATS": return 40;
      case "CONCORDANCES_CASOS_PARTICULARS": return 30;
      case "CONFUSIONS_ACCENT": return 20;
      case "DIACRITICS": return 20;
      case "ACCENTUATION_CHECK": return 10;
      case "HAVER_SENSE_HAC": return 10;
      case "CONCORDANCES_DET_NOM": return 5;
      case "REGIONAL_VERBS": return -10;
      case "FALTA_COMA_FRASE_CONDICIONAL": return -20;
      case "SUBSTANTIUS_JUNTS": return -25;
      case "MUNDAR": return -50;
      case "MORFOLOGIK_RULE_CA_ES": return -100;
      case "FALTA_ELEMENT_ENTRE_VERBS": return -200;
      case "NOMBRES_ROMANS": return -400;
      case "UPPERCASE_SENTENCE_START": return -500;
      case "STYLE_REPEATED_WORD_RULE_CA": return -1000;  // style rules do not take priority
    }
    return super.getPriorityForId(id);
  }  
}
