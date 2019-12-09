/* LanguageTool, a natural language style checker
 * Copyright (C) 2019 Tiago F. Santos (https://github.com/TiagoSantos81)
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

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.JLanguageTool;
import org.languagetool.tools.Tools;
import org.languagetool.Language;
import org.languagetool.tagging.disambiguation.rules.DisambiguationPatternRule;
import org.languagetool.rules.*;
import org.languagetool.rules.patterns.PatternToken;
import org.languagetool.rules.patterns.PatternTokenBuilder;
import org.languagetool.tagging.en.EnglishTagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.languagetool.tools.Tools;
import java.net.URL;

import static org.languagetool.rules.patterns.PatternRuleBuilderHelper.*;

/**
 * A rule that checks the usage of adjectives in place of nouns. 
 * Loads the list of words from <code>/pt/adjective_nouns.txt</code>.
 *
 * @author Tiago F. Santos 
 * @since 4.8
 */
public class EnglishAdjectiveNounConfusionRule extends Rule {

  private final EnglishTagger tagger;
  private final Language english;

  private static final Map<String,String> ADJECTIVE_NOUN_DB = loadWordlist("en/adjective_nouns.txt", 0);
  private static final Pattern PRECEEDS_NOUN = Pattern.compile("[Tt]h(e|is)|[Aa]n?|[Mm]y|[Yy]?[Oo]ur|[Hh](is|er|a[ds]|ave)|[Tt]heir|[Ii]ts|[Aa]t|[In]n|[Oo][fn]|[Ff]or|[Ww]ith");
  private static final Pattern FOLLOWS_NOUN = Pattern.compile("[Oo][fn]|[Ff]or|[\\.!\\?]");

  private static final Map<String,String> NOUN_ADJECTIVE_DB = loadWordlist("en/adjective_nouns.txt", 1);
  private static final Pattern PRECEEDS_ADJECTIVE = Pattern.compile("[Ii]s|[Ww](as|ere)"); // + the most
  private static final Pattern FOLLOWS_ADJECTIVE = Pattern.compile("[\\.!\\?]");

  @Override
  public List<DisambiguationPatternRule> getAntiPatterns() {
    return makeAntiPatterns(ANTI_PATTERNS, english);
  }

  private static final List<List<PatternToken>> ANTI_PATTERNS = Arrays.asList(
    Arrays.asList(
      token("nature"),
      regex("c(?:onservation|risis)|magazine|polic(?:y|ies)|reserves?")
    ),
    Arrays.asList(
      token("on"),
      token("screen")
    ),
    Arrays.asList(
      token("for"),
      token("the"),
      token("good"),
      token("of")
    ),
    Arrays.asList(
      regex("an?|the"),
      posRegex("J.+"),
      posRegex("J.+"),
      posRegex("N.+")
    ),
    Arrays.asList(
      token("beauty"),
      regex("pageants?")
    ),
    Arrays.asList(
      token("danger"),
      regex("zones?")
    ),
    Arrays.asList(
      token("guilt"),
      regex("trips?")
    ),
    Arrays.asList(
      token("juice"),
      regex("shots?")
    ),
    Arrays.asList(
      token("pain"),
      regex("treatments?")
    ),
    Arrays.asList(
      token("truth"),
      regex("(movement|teller)s?")
    )
  );

  public EnglishAdjectiveNounConfusionRule(ResourceBundle messages, Language language) {
    super.setCategory(Categories.GRAMMAR.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Grammar);
    addExamplePair(Example.wrong("<marker>The easy</marker> of use of this product is obvious."),
      Example.fixed("<marker>The ease</marker> of use of this product is obvious."));
    addExamplePair(Example.wrong("It was nice to see the animals in their <marker>nature environment.</marker>"),
      Example.fixed("It was nice to see the animals in their <marker>natural environment.</marker>"));
    this.english = language;
    this.tagger = (EnglishTagger) english.getTagger();
  }

  @Override
  public URL getUrl() {
    return Tools.getUrl("https://www.ecenglish.com/learnenglish/lessons/noun-and-adjective-forms");
  }

  @Override
  public String getId() {
    return "ADJECTIVE_NOUN_CONFUSION";
  }

  @Override
  public String getDescription() {
    return "Incorrect use of an adjective.";
  }

  @Override
  public RuleMatch[] match(AnalyzedSentence sentence) {
    List<RuleMatch> ruleMatches = new ArrayList<>();
    AnalyzedTokenReadings[] tokens = getSentenceWithImmunization(sentence).getTokensWithoutWhitespace();
    int markEnd = 1;
    String replacement = null;
    String msg = null;
    String shortmsg = null;
    for (int i = 0; i < (tokens.length - 1); i++) {
      if (tokens[i].isImmunized()
       || tokens[i + 1].isImmunized()) {
        continue;
      }
      if (preceedsNoun(tokens[i])) {
        shortmsg = "noun";
        if (isListedAdjective(tokens[i + 1])
              && (followsNoun(tokens[i + 2]) || (isVerb(tokens[i + 2]) && !isNoun(tokens[i + 2])))) {
          markEnd = i + 1;
          if (replacement == null) {
            replacement = getNounReplacements().get(tokens[i + 1].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i + 1].getToken() + "’ is an adjective. If you are referring to the related " + shortmsg + ", you probably should use <suggestion>" + tokens[i].getToken() + " " + replacement + "</suggestion> instead.";
            }
          }
        }
      }
      if (isListedNoun(tokens[i])) {
        shortmsg = "adjective";
        if (isNoun(tokens[i + 1]) && !isVerb(tokens[i + 1])) {
          markEnd = i + 1;
          if (replacement == null) {
            replacement = getAdjectiveReplacements().get(tokens[i].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i].getToken() + "’ is a noun. If you are referring to the related " + shortmsg + ", you probably should use <suggestion>" + replacement + " " + tokens[i + 1].getToken() + "</suggestion> instead.";
            }
          }
        }
      }
      if (msg != null) {
        RuleMatch match = new RuleMatch(
          this, sentence, tokens[i].getStartPos(), tokens[markEnd].getEndPos(), msg, "Here you should use the " + shortmsg + " instead.");
        ruleMatches.add(match);
        msg = null;
      }
    }
    return toRuleMatchArray(ruleMatches);
  }

  public boolean preceedsNoun(AnalyzedTokenReadings token) {
    return PRECEEDS_NOUN.matcher(token.getToken()).matches();
  }

  public boolean followsNoun(AnalyzedTokenReadings token) {
    return FOLLOWS_NOUN.matcher(token.getToken()).matches();
  }

  private boolean isVerb(AnalyzedTokenReadings token) {
    if (token.hasPosTagStartingWith("V")) {
      return true;
      }
    return false;
  }

  private boolean isAdjective(AnalyzedTokenReadings token) {
    if (token.hasPosTagStartingWith("J")) {
      return true;
      }
    return false;
  }

  private boolean isNoun(AnalyzedTokenReadings token) {
    if (token.hasPosTagStartingWith("N")) {
      return true;
      }
    return false;
  }

  static Map<String, String> getNounReplacements() {
    return ADJECTIVE_NOUN_DB;
  }

  private boolean isListedAdjective(AnalyzedTokenReadings token) {
    for (String adjective : getNounReplacements().keySet()) {
      if (adjective.equals(token.getToken())) {
        return true;
      }
    }
    return false;
  }

  static Map<String, String> getAdjectiveReplacements() {
    return NOUN_ADJECTIVE_DB;
  }

  private boolean isListedNoun(AnalyzedTokenReadings token) {
    for (String noun : getAdjectiveReplacements().keySet()) {
      if (noun.equals(token.getToken())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int estimateContextForSureMatch() {
    return ANTI_PATTERNS.stream().mapToInt(List::size).max().orElse(0);
  }

  private static Map<String,String> loadWordlist(String path, int column) {
    if (column != 0 && column != 1) {
      throw new IllegalArgumentException("Only column 0 and 1 are supported: " + column);
    }
    Map<String,String> words = new HashMap<>();
    InputStream stream = JLanguageTool.getDataBroker().getFromRulesDirAsStream(path);
    try (Scanner scanner = new Scanner(stream, "utf-8")) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty() ||  line.startsWith("#")) {
          continue;
        }
        String[] parts = line.split("=");
        if (parts.length != 2) {
          throw new IOException("Unexpected format in " + path + ": " + line + " - expected two parts delimited by '='");
        }
        words.put(parts[column], parts[column == 1 ? 0 : 1]);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return words;
  }
}
