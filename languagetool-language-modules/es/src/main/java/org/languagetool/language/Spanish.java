/* LanguageTool, a natural language style checker 
 * Copyright (C) 2007 Daniel Naber (http://www.danielnaber.de)
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.languagetool.*;
import org.languagetool.languagemodel.LanguageModel;
import org.languagetool.rules.*;
import org.languagetool.rules.es.*;
import org.languagetool.synthesis.Synthesizer;
import org.languagetool.synthesis.es.SpanishSynthesizer;
import org.languagetool.tagging.Tagger;
import org.languagetool.tagging.disambiguation.Disambiguator;
import org.languagetool.tagging.disambiguation.es.SpanishHybridDisambiguator;
import org.languagetool.tagging.es.SpanishTagger;
import org.languagetool.tokenizers.*;
import org.languagetool.tokenizers.es.SpanishWordTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Spanish extends Language implements AutoCloseable{

  private LanguageModel languageModel;

  @Override
  public String getName() {
    return "Spanish";
  }

  @Override
  public String getShortCode() {
    return "es";
  }

  @Override
  public String[] getCountries() {
    return new String[]{
            "ES", "", "MX", "GT", "CR", "PA", "DO",
            "VE", "PE", "AR", "EC", "CL", "UY", "PY",
            "BO", "SV", "HN", "NI", "PR", "US", "CU"
    };
  }

  @NotNull
  @Override
  public Tagger createDefaultTagger() {
    return new SpanishTagger();
  }

  @Override
  public Disambiguator createDefaultDisambiguator() {
    return new SpanishHybridDisambiguator();
  }

  @Override
  public Tokenizer createDefaultWordTokenizer() {
    return new SpanishWordTokenizer();
  }

  @Nullable
  @Override
  public Synthesizer createDefaultSynthesizer() {
    return new SpanishSynthesizer(this);
  }

  @Override
  public SentenceTokenizer createDefaultSentenceTokenizer() {
    if (sentenceTokenizer == null) {
      sentenceTokenizer = new SRXSentenceTokenizer(this);
    }
    sentenceTokenizer.setSingleLineBreaksMarksParagraph(true);
    return sentenceTokenizer;
  }
  
  @Override
  public Contributor[] getMaintainers() {
    return new Contributor[] {
            new Contributor("Juan Martorell", "http://languagetool-es.blogspot.com/"),
            new Contributor("Jaume Ortolà")
    };
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
    return Arrays.asList(
            new CommaWhitespaceRule(messages),
            new DoublePunctuationRule(messages),
            new SpanishUnpairedBracketsRule(messages),
            new QuestionMarkRule(messages),
            new LongSentenceRule(messages, userConfig, -1, true),
            new LongParagraphRule(messages, this, userConfig),
            new MorfologikSpanishSpellerRule(messages, this, userConfig, altLanguages),
            new UppercaseSentenceStartRule(messages, this),
            new WordRepeatRule(messages, this),
            new MultipleWhitespaceRule(messages, this),
            new SentenceWhitespaceRule(messages),
            new WhiteSpaceAtBeginOfParagraph(messages),
            new EmptyLineRule(messages, this),
            new ParagraphRepeatBeginningRule(messages, this),
            new PunctuationMarkAtParagraphEnd(messages, this, true),
            //Specific to Spanish:
            new SpanishWikipediaRule(messages),
            new SpanishStyleRepeatedWordRule(messages, this, userConfig),
            new SpanishWrongWordInContextRule(messages),
            new SimpleReplaceRule(messages),
            new SimpleReplaceVerbsRule(messages, this),
            new SimpleReplaceAnglicismRule(messages)
    );
  }

  /** @since 3.1 */
  @Override
  public synchronized LanguageModel getLanguageModel(File indexDir) throws IOException {
    languageModel = initLanguageModel(indexDir, languageModel);
    return languageModel;
  }

  /** @since 3.1 */
  @Override
  public List<Rule> getRelevantLanguageModelRules(ResourceBundle messages, LanguageModel languageModel, UserConfig userConfig) throws IOException {
    return Arrays.asList(
            new SpanishConfusionProbabilityRule(messages, languageModel, this)
    );
  }

  /**
   * Closes the language model, if any. 
   * @since 3.1
   */
  @Override
  public void close() throws Exception {
    if (languageModel != null) {
      languageModel.close();
    }
  }

  @Override
  public LanguageMaintainedState getMaintainedState() {
    return LanguageMaintainedState.ActivelyMaintained;
  }
  
  @Override
  public int getPriorityForId(String id) {
    switch (id) {
      case "INCORRECT_EXPRESSIONS": return 40;
      case "MISSPELLING": return 40;  
      case "CONFUSIONS": return 40;
      case "NO_SEPARADO": return 40;
      case "DIACRITICS": return 30;
      case "AGREEMENT_DET_NOUN": return 20;
      case "TYPOGRAPHY": return 10;
      case "HALLA_HAYA": return 10;
      case "ES_WIKIPEDIA_COMMON_ERRORS": return -10;
      case "EL_TILDE": return -10;
      case "PREPOSICION_VERBO": return -20;
      case "SUBJUNTIVO_FUTURO": return -30;
      case "SUBJUNTIVO_PASADO": return -30;
      case "SUBJUNTIVO_PASADO2": return -30;
      case "VOSEO": return -40;
    }
    return super.getPriorityForId(id);
  }

}
