# LibreGrammar Change Log

## 4.8X-SNAPSHOT (release planned for 2019-XX-XX)

### This fork differences

#### English
  * Rules added:
     - EnglishSimpleGrammarRule for simple replacement rule creation - 877 entries added from Wikipedia;
     - eggcorn detection - 227 entries from Grammark and After the Dealine (AtD);
     - cliche detection - 691 entries from AtD;
     - wordiness database enhancements - 725 entries from Grammark;
     - informal expressions detection - 228 entries from Wikipedia;
     - EnglishStyleRepeatedWordRule;
     - split infinitive detection;
     - comma splice partial detection;
     - weasel word and thought-terminating clichés detection - 87 entries added from Wikipedia;
     - easily confused words - 533 pairs add - active only with
    ngram data (see http://wiki.languagetool.org/finding-errors-using-n-gram-data).
  * English rules re-activated:
     - EnglishPlainEnglishRule - 697 entries from AtD;
     - Plain English category - 105 rules;
     - EnglishRedundancyRule (AtD) - 731 entries from AtD;
     - LongSentenceRule;
     - Passive voice;
     - Tired intensifiers;
     - Number at sentence start;
     - Sentence starting with 'And' or 'But';
     - Several collocation rules;
     - Articles: article missing before a countable noun;
     - Non-standard contractions '(I've a...)';
     - wold vs. would (see 9a1bdd5 and 3a851cb);
     - seas vs. sees (see 9a1bdd5 and 3a851cb);
     - hav vs. have (see 9a1bdd5 and 3a851cb);
     - complaint vs. complained (see 9a1bdd5 and 3a851cb);
     - handover vs. hand over (see 9a1bdd5 and 3a851cb);
     - shutdown vs. shut down (see 9a1bdd5 and 3a851cb);
     - can't help but (can't help + gerund);
     - 'e.g.' without a comma;
     - 'i.e.' without a comma;
     - Missing comma before 'and, or, nor, yet, so, but';
     - Warn when the serial comma is not used;
     - No comma before 'which';
     - Several special redundancy rules;
     - Readability: Three nouns in a row;
     - Word order: 'Hopefully' starting a sentence;
     - Typographical ellipsis;
     - 'also' at the end of the sentence
     - Several adjustments to rules with wide-spectrum antipatterns.

#### French
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph, EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.

#### German
  * activated generic Java rules, including:
     - GermanStyleRepeatedWordRule, LongSentenceRule.

#### Irish
  * Support added thanks to Jim Regan, Emily Barnes, Mícheál J. Ó Meachair, 
  and Seanán Ó Coistín. Support includes:
     - 606 grammar rules;
     - Irish naming conventions converter;
     - generic typographical rules (e.g., spacing rules);
     - generic style rules (e.g., sentence length);
     - [spell checking](https://github.com/jimregan/languagetool-ga-dicts);
     - [part-of-speech dictionary and synthesiser](https://github.com/jimregan/languagetool-ga-dicts).

#### Italian
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph, EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.

#### Portuguese
  * Portugal Portuguese former orthographic agreement locale added (Portugal 
  pre-AO) with support for:
     - [pt-PT pre-AO] [Dicionários Portugueses Complementares 1.2](https://github.com/TiagoSantos81/PortugueseLibreOfficeExtension);
     - common Portuguese rules;
     - pre-AO hyphenation rules;
     - wrong spelling agreement errors (PortuguesePreAgreementReplaceRule);
     - capitalization rules.

#### Spanish
  * re-activated subject-predicate agreement category.
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph, EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.

#### General
  * server and HTTP API connection commits for LibreOffice plug-in reverted.
  * removed Google dependencies from English and Catalan language modules, as well as from several Java rules.
  * dependencies security updates.
  * restored some languagetool-dev and languagetool-standalone scripts.
  * [WIP] removed or comment out Freemium server bindings.

### Other changes common to both projects

#### Catalan
  * improved rules

#### Chinese
  * Now using https://github.com/hankcs/HanLP for tokenization (PR 1981)

#### Dutch
  * improved rules
  * disambiguation improvements
  * POS and spelling improvements

#### English
  * added and improved rules
  * disambiguation improvements
  * POS and spelling improvements

#### French
  * added and improved rules
  * POS and spelling improvements, including:
    - updated part-of-speech dictionaries to dicollecte-6.4.1
    (https://github.com/languagetool-org/languagetool/pull/1963)

#### German
  * added and improved rules
  * POS and spelling improvements

#### Russian
  * improved rules

#### General
  * The unmaintained code from package `org.languagetool.dev.wikipedia.atom`
    has been removed. It hadn't been maintained for years and didn't work properly
    anymore.

#### Internal
  * Experimental: the new `default="temp_off"` attribute in `grammar.xml` files will
    turn off a rule/rulegroup, but keep it activated for our nightly regression tests.
  * Many external dependencies have been updated to new versions

#### General
  * dependencies security updates.

