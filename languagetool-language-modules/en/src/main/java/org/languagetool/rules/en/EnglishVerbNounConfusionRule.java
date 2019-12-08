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

import static org.languagetool.rules.patterns.PatternRuleBuilderHelper.*;

/**
 * A rule that checks the usage of verbs in place of nouns.
 * Loads the list of words from <code>/pt/verb_nouns.txt</code>.
 *
 * @author Tiago F. Santos
 * @since 4.8
 */
public class EnglishVerbNounConfusionRule extends Rule {

  private final EnglishTagger tagger;
  private final Language english;

  private static final Map<String,String> VERB_NOUN_DB = loadWordlist("en/verb_nouns.txt", 0);
  private static final Pattern PRECEDES_NOUN = Pattern.compile("[Tt]h(e|is)|[Aa]n?|[Mm]y|[Yy]?[Oo]ur|[Hh](is|er)|[Tt]heir|[Ii]ts|[Oo][fn]|[Ff]or|[Ww]ith"); // excluded also non-determiners s|that

  private static final Map<String,String> NOUN_VERB_DB = loadWordlist("en/verb_nouns.txt", 1);
  private static final Pattern PRECEDES_VERB = Pattern.compile("can(not)?|[wc]ould|should|m(?:ight|ust|ay)|did|will|It?|[Yy]ou|[Ss][Hh]e|[Tt]hey|[Ww]e"); // excluded to|ha([ds]|ve)|ve
                                                                                                                                       // TODO add conditional for have + inflected verb
  private static final Pattern PRECEDES_VERB_NOT = Pattern.compile("can|shouldn|couldn|wouldn|won|didn|mustn");
  private static final Pattern APOSTROPHE = Pattern.compile("['’`´‘]");

  private static final List<List<PatternToken>> ANTI_PATTERNS = Arrays.asList(
  // antipatterns from grammar.xml::A_INFINITIVE
    Arrays.asList(
      token("lynch"),
      regex("mobs?")
    ),
    Arrays.asList(/*  https://www.rotary.org/en/get-involved/interact-clubs  */
      token("interact"),
      regex("clubs?")
    ),
    Arrays.asList(/*  brexit term  */
      token("remain"),
      regex("campaigns?|people|movement|party|politicians?")
    ),
    Arrays.asList(
      token("redeem"),
      regex("code|gift")
    ),
    Arrays.asList(
      regex("write|read"),
      regex("access|protection")
    ),
    Arrays.asList(/*  "Thanks for the add." (https://www.urbandictionary.com/define.php?term=thanks%20for%20the%20add)  */
      token("for"),
      token("the"),
      token("add")
    ),
    Arrays.asList( /*  "Please click the unsubscribe link at the end of the mail" (TODO: we should probably have a rule that suggests putting the verb in quotes)  */
      posRegex("VBP"),
      regex("buttons?|links?|teasers?|icons?|keys?|functions?|functionalit(y|ies)|methods?|features?|page|parameters?|options?|steps?|columns?|plans?|sections?|signals?|actions?|process(es)?|modes?")
    ),
    Arrays.asList( /*  this is better covered by rule ALTER_BOY  */
      regex("at|to|worship(s|ped)?|around"),
      regex("an?|the"),
      token("alter")
    ),
    Arrays.asList(
      pos("CD"),
      token("."),// TODO space_before
      token("a")// TODO space_before
    ),
    Arrays.asList(/*  "Let's start with the do's" (already caught by VERB_APOSTROPHE_S)  */
      token("the"),
      token("do"),
      regex("['’`´‘]"),
      token("s")
    ),
    Arrays.asList(/*  the merge process/es to be/become  */
      token("the"),
      regex(".+"),
      posRegex("NN(S|:UN?)?"),
      token("to"),
      regex("be(come)?")
    ),
    Arrays.asList(/*  a be ce. MFU 2019-08-25 I did not find a reference.  */
      token("a"),
      token("be"),
      token("ce")
    ),
    Arrays.asList(/*  Technical talk: "The delete request failed."  */
      regex("an?|the"),
      regex("put|delete|get|post|patch"),
      token("request")
    ),
    Arrays.asList(/*  https://www.collinsdictionary.com/dictionary/english/scissor  */
      regex("an?|the"),
      token("scissor"),
      posRegex("NN.*")
    ),
    Arrays.asList(/*  #489 file name extensions  */
      regex(".+"),
      token("."), // TODO space_before
      regex("a(?:spx?|vi)|b(?:ak|at|mp)|c(?:ab|fg|gi|om|ss|sv)|d(?:at|b|bf|ll|ocx?)|e(?:ps|xe)|flv|gif|html?|i(?:books|co|ni)|j(?:ar|pe?g|s|sp)|lnk|m(?:d|db|id|ov|p3|p4|pa|pg)|o[dt][tspfg]|p(?:df|hp|l|ng|ps|ptx?|s|sd|y)|rss|s(?:h|ql|vg|ys)|t(?:ar|if?|mp|xt)|w(?:av|ma)|x(?:html|lsx?|sl|ml)|zipx?|7z") // TODO space_before
    ),
    Arrays.asList(/*  the translate.google.com website  */
      regex(".+"),
      token("."),
      regex("[a-z0-9\\-]+"), // TODO space_before
      regex("."), // TODO space_before
      regex("a(?:e|r|sia|t|u)|b(?:e|id|iz|r)|c(?:a|at|c|f|h|l|lub|n|o|om|z)|d(?:e|k)|e(?:du|s|u)|f(?:i|r)|g(?:a|dn|ov|q|r)|h(?:k|u)|i(?:d|e|l|n|nfo|nt|o|r|t)|jp|k(?:iwi|r|z)|l(?:ife|ink|oan|t)|m(?:e|en|il|l|obi|x|y)|n(?:ame|et|l|o|u|z)|o(?:nline|rg)|p(?:arty|l|ro|t|w)|r(?:acing|o|u)|s(?:e|g|hop|ite|k|pace|tore|u)|t(?:ech|k|op|r|rade|v|w)|u(?:a|k|s)|v(?:ip|n)|w(?:ang|ebsite|in|s)|x(?:in|yz)|za")
    ),
    Arrays.asList(
      token("from"),
      token("now"),
      token("on")
    ),
    Arrays.asList(
      regex("makes?"),
      regex("this|that"),
      token("happen")
    ),
    // antipatterns for PRECEDES_VERB + NOUN
    Arrays.asList(
      pos("MD"),
      regex("th(?:is|at)")
    ),
    Arrays.asList(
      regex("d(?:oes|id)"),
      regex("th(?:is|at)")
    ),
    Arrays.asList(
      token("this"),
      pos("VBZ")
    )
    /*
    Arrays.asList(
      posRegex("[^D].+"),
      regex("(?:collapse|continue|forward|go|move|proceed|refer|send)(?:e?[ds])?"),
      token("to"),
      posRegex("NN.*")
    ),
    Arrays.asList(
      regex("(?:call|drive|thank)(?:e?[ds])?"),
      token("to"),
      posRegex("NN.*")
    )
    */
  );

  @Override
  public List<DisambiguationPatternRule> getAntiPatterns() {
    return makeAntiPatterns(ANTI_PATTERNS, english);
  }

  public EnglishVerbNounConfusionRule(ResourceBundle messages, Language language) {
    super.setCategory(Categories.GRAMMAR.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Grammar);
    addExamplePair(Example.wrong("<marker>The achieve</marker> is obvious."),
      Example.fixed("<marker>The achievement</marker> is obvious."));
    addExamplePair(Example.wrong("<marker>The pursue</marker> is on."),
      Example.fixed("<marker>The pursuit</marker> is on."));
    this.english = language;
    this.tagger = (EnglishTagger) english.getTagger();
  }

  @Override
  public String getId() {
    return "VERB_NOUN_CONFUSION";
  }

  @Override
  public String getDescription() {
    return "Incorrect use of a verb.";
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
      if (precedesNoun(tokens[i])) {
        shortmsg = "noun";
        if (isVerb(tokens[i + 1])) {
          markEnd = i + 1;
          if (replacement == null) {
            replacement = getNounReplacements().get(tokens[i + 1].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i + 1].getToken() + "’ is a verb. If you are referring to the related noun, you probably should use <suggestion>" + tokens[i].getToken() + " " + replacement + "</suggestion> instead.";
            }
          }
        }
        if ((isAdjective(tokens[i + 1]))
              && (isVerb(tokens[i + 2]))
                    && !(tokens[i + 2].isImmunized())) {
          markEnd = i + 2;
          if (replacement == null) {
            replacement = getNounReplacements().get(tokens[i + 2].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i + 2].getToken() + "’ is a verb. If you are referring to the related noun, you probably should use <suggestion>" + tokens[i].getToken() + " " + tokens[i + 1].getToken() + " " + replacement + "</suggestion> instead.";
            }
          }
        }
      }
      if (precedesVerb(tokens[i])) {
        shortmsg = "verb";
        if (isNoun(tokens[i + 1])) {
          markEnd = i + 1;
          if (replacement == null) {
            replacement = getVerbReplacements().get(tokens[i + 1].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i + 1].getToken() + "’ is a noun. If you are referring to the related verb, you probably should use <suggestion>" + tokens[i].getToken() + " " + replacement + "</suggestion> instead.";
            }
          }
        }
        if ((isAdverb(tokens[i + 1]))
           && (isNoun(tokens[i + 2]))
                 && !(tokens[i + 2].isImmunized())) {
          markEnd = i + 2;
          if (replacement == null) {
            replacement = getVerbReplacements().get(tokens[i + 2].getToken());
            if (msg == null) {
              msg = "Notice ‘" + tokens[i + 2].getToken() + "’ is a noun. If you are referring to the related verb, you probably should use <suggestion>" + tokens[i].getToken() + " " + tokens[i + 1].getToken() + " " + replacement + "</suggestion> instead.";
            }
          }
        }
      }
      if (precedesNegativeVerb(tokens[i])) {
        shortmsg = "verb";
        if (isApostrophe(tokens[i + 1])
                      || tokens[i + 2].equals("t")) { // FIXME && would make sense but it fails to trigger the rule here. Why?
          if (isNoun(tokens[i + 3])
          &&       !(tokens[i + 3].isImmunized())) {
            markEnd = i + 3;
            if (replacement == null) {
              replacement = getVerbReplacements().get(tokens[i + 3].getToken());
              if (msg == null) {
                msg = "Notice ‘" + tokens[i + 3].getToken() + "’ is a noun. If you are referring to the related verb, you probably should use <suggestion>" + tokens[i].getToken() + tokens[i + 1].getToken() + tokens[i + 2].getToken() + " " + replacement + "</suggestion> instead.";
              }
            }
          }
          if ((isAdverb(tokens[i + 3]))
             && (isNoun(tokens[i + 4]))
                   && !(tokens[i + 4].isImmunized())) {
            markEnd = i + 4;
            if (replacement == null) {
              replacement = getVerbReplacements().get(tokens[i + 4].getToken());
              if (msg == null) {
                msg = "Notice ‘" + tokens[i + 4].getToken() + "’ is a noun. If you are referring to the related verb, you probably should use <suggestion>" + tokens[i].getToken() + tokens[i + 1].getToken() + tokens[i + 2].getToken() + " " + tokens[i + 3].getToken() + " " + replacement + "</suggestion> instead.";
              }
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

  public boolean precedesNoun(AnalyzedTokenReadings token) {
    return PRECEDES_NOUN.matcher(token.getToken()).matches();
  }

  private boolean isAdjective(AnalyzedTokenReadings token) {
    if ((token.hasPosTagStartingWith("JJ"))
    && !(token.hasPosTagStartingWith("NN"))) {
      return true;
      }
    return false;
  }

  static Map<String, String> getNounReplacements() {
    return VERB_NOUN_DB;
  }

  private boolean isVerb(AnalyzedTokenReadings token) {
    for (String verb : getNounReplacements().keySet()) {
      if (verb.equals(token.getToken())) {
        return true;
      }
    }
    return false;
  }

  public boolean precedesVerb(AnalyzedTokenReadings token) {
    return PRECEDES_VERB.matcher(token.getToken()).matches();
  }

  public boolean precedesNegativeVerb(AnalyzedTokenReadings token) {
    return PRECEDES_VERB_NOT.matcher(token.getToken()).matches();
  }

  public boolean isApostrophe(AnalyzedTokenReadings token) {
    return APOSTROPHE.matcher(token.getToken()).matches();
  }

  static Map<String, String> getVerbReplacements() {
    return NOUN_VERB_DB;
  }

  private boolean isNoun(AnalyzedTokenReadings token) {
    for (String verb : getVerbReplacements().keySet()) {
      if (verb.equals(token.getToken())) {
        return true;
      }
    }
    return false;
  }

  private boolean isAdverb(AnalyzedTokenReadings token) {
    if ((token.hasPosTagStartingWith("R"))
    && !(token.hasPosTagStartingWith("N"))
    && !(token.hasPosTagStartingWith("V"))) {
      return true;
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
