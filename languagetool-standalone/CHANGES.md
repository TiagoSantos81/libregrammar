# LibreGrammar Change Log

## 4.8X-SNAPSHOT (release planned for 2019-XX-XX)

### This fork differences

#### English
  * rules added:
     - EnglishSimpleGrammarRule for simple replacement rule creation - 877 error patterns added from Wikipedia;
     - verb-noun confusion rule - 1465 error patterns mostly from After the Deadline (AtD);
     - eggcorn detection - 227 error patterns from Grammark and AtD;
     - specific case rule - 751 entries from Wikipedia;
     - nonstandard English replacement rule - 7 error patterns;
     - cliche detection - 691 error patterns from AtD;
     - wordiness database enhancements - 653 error patterns from Grammark;
     - informal expressions detection - 228 error patterns from Wikipedia;
     - EnglishStyleRepeatedWordRule;
     - split infinitive detection;
     - comma splice partial detection;
     - weasel word and thought-terminating cliches detection - 87 error patterns added from Wikipedia;
     - typography of mathematical symbols;
     - easily confused words - 533 pairs added - active only with
    ngram data (see http://wiki.languagetool.org/finding-errors-using-n-gram-data);
     - other rules added and improved.
  * English rules re-activated:
     - EnglishPlainEnglishRule - 697 error patterns from AtD;
     - Plain English category - 105 rules;
     - EnglishRedundancyRule - 731 error patterns from AtD;
     - LongSentenceRule;
     - Passive voice;
     - Tired intensifiers;
     - Number at sentence start;
     - Sentence starting with 'And' or 'But';
     - Several collocation rules;
     - Articles: article missing before a countable noun;
     - Non-standard contractions '(I've a...)';
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
  * sentences and paragraphs now end with a single line break.

#### Esperanto
  * sentences and paragraphs now end with a single line break.

#### French
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph,
     EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.
  * add typography rules for mathematical symbols and arrows.
  * sentences and paragraphs now end with a single line break.

#### Galician
  * sentences and paragraphs now end with a single line break.

#### German
  * activated generic Java rules, including:
     - GermanStyleRepeatedWordRule, LongSentenceRule.

#### Irish
  * Support added thanks to Jim Regan, Emily Barnes, Mícheál J. Ó Meachair, 
  and Seanán Ó Coistín. Support includes:
     - 963 grammar rules;
     - Irish naming conventions converter;
     - generic typographical rules (e.g., spacing rules);
     - generic style rules (e.g., sentence length);
     - [spell checking](https://github.com/jimregan/languagetool-ga-dicts);
     - [part-of-speech dictionary and synthesiser](https://github.com/jimregan/languagetool-ga-dicts).

#### Italian
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph,
     EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.
  * add typography rules for mathematical symbols and arrows.
  * sentences and paragraphs now end with a single line break.

#### Portuguese
  * add a few more rules:
    - vocative punctuation;
    - personal infinitive rules;
    - subject-predicate agreement;
    - specific case rules with 1057 entries from Wikipedia.
  * re-activated diacritics rule;
  * Portugal Portuguese former orthographic agreement locale added (Portugal 
  pre-AO) with support for:
     - [pt-PT pre-AO] [Dicionários Portugueses Complementares 1.2](https://github.com/TiagoSantos81/PortugueseLibreOfficeExtension);
     - common Portuguese rules;
     - pre-AO hyphenation rules;
     - wrong spelling agreement errors (PortuguesePreAgreementReplaceRule);
     - capitalization rules.
  * sentences and paragraphs now end with a single line break.
  * various localization fixes.

#### Polish
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, UppercaseSentenceStartRule, WhiteSpaceBeforeParagraphEnd,
     WhiteSpaceAtBeginOfParagraph, EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.

#### Spanish
  * re-activated subject-predicate agreement category.
  * activated generic Java rules, including:
     - LongSentenceRule, LongParagraphRule, SentenceWhitespaceRule, WhiteSpaceAtBeginOfParagraph,
     EmptyLineRule, ParagraphRepeatBeginningRule, PunctuationMarkAtParagraphEnd.
  * add typography rules for mathematical symbols.
  * sentences and paragraphs now end with a single line break.

#### General
  * dependencies security updates.
  * restored some languagetool-dev and languagetool-standalone scripts.
  * [WIP] removed Google dependencies from English and Catalan language modules, as well as from several Java rules.
  * [WIP] removed or comment out Freemium server bindings.
  * standalone default underline colors changed:
    - grey: style repetitions and redundancies;
    - cyan: typographical and whitespaces related suggestions.
  * ParagraphRepeatBeginning now triggers properly, shows the correct messages, and is now active by default.

#### LibreOffice / Apache OpenOffice Integration
  * remote server commits for LibreOffice plug-in reverted (#1929).
  * multiple core CPU support active by default.

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
  * many rules added and significant rules improvements
    - SpecificCaseRule for case specific phrases
  * multiwords support added
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

#### Greek
  * updated spelling dictionary to el_GR 0.9 (14/03/2019), by George Zougianos

#### Portuguese
  * added and improved rules
  * POS and spelling improvements

#### Russian
  * improved rules

#### Spanish
  * added rules
  * POS and spelling improvements

#### General
  * The unmaintained code from package `org.languagetool.dev.wikipedia.atom`
    has been removed. It hadn't been maintained for years and didn't work properly
    anymore.

#### LibreOffice / Apache OpenOffice Integration
  * 'disable rule' option added to context menu.
 
#### Java API
  * `AbstractSimpleReplaceRule2` has been fixed so that it's now case-insensitive.
    If you implement a sub class of it and you want the old behavior, please implement
    `isCaseSensitive()` and have it return `true`. (Issue #2051)

#### HTTP API / LT server
  * The dynamic languages feature (`lang-xx=...` and `lang-xx-dictPath=...`) now
    also supports hunspell dictionaries. Just let `lang-xx-dictPath` point to the
    absolute path of the `.dic` file. Note that hunspell is quite slow when it
    comes to offering suggestions for misspelled words. 

#### Internal
  * The internal hunspell has been updated from 1.3 to 1.7, now using
    https://gitlab.com/dumonts/hunspell-java as the project providing the bindings.
    For Portuguese, this speeds up generating suggestions for misspellings by
    a factor of about 3 (but it's still slow compared to Morfologik).
  * Experimental: the new `default="temp_off"` attribute in `grammar.xml` files will
    turn off a rule/rulegroup, but keep it activated for our nightly regression tests.
  * Many external dependencies have been updated to new versions

