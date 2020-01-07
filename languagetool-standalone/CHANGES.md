# LibreGrammar Change Log

## 4.9-SNAPSHOT (release planned for 2020-03-24)

### This fork differences

#### Breton
  * compound rule added:
     - 4136 entries thanks to Wanibzh29 (https://github.com/languagetool-org/languagetool/issues/2299).

#### English
  * rules added and improved

#### Portuguese
  * some commit fixes to upstream project

### Other changes common to both projects

#### Arabic
  * support now also available on upstream project 
    (https://github.com/languagetool-org/languagetool/pull/2219);
  * rules added and improved.

#### Dutch
  * added and improved rules

#### German
  * added and improved rules

#### Irish
  * support now also available on upstream project
    (https://github.com/languagetool-org/languagetool/pull/2260);
  * rules added and improved.

#### General
  * dependency security update
  * [upstream hunspell support dropped for 32-bit systems](https://github.com/languagetool-org/languagetool/commit/b769f5ec319a53c10c45a8aa33a9fd6a4600792c).

## 4.8 (released 2019-12-28)

### This fork differences

#### English
  * rules added:
     - `EnglishSimpleGrammarRule` for simple replacement rule creation - 877 error patterns added from Wikipedia;
     - verb-noun confusion rule - 1547 error patterns mostly from After the Deadline (AtD);
     - adjective-noun confusion rule - 103 error patterns;
     - adjective order rule;
     - eggcorn detection - 227 error patterns from Grammark and AtD;
     - specific case rule - 751 extra entries from Wikipedia;
     - nonstandard English replacement rule - 22 error patterns;
     - cliché detection - 691 error patterns from AtD;
     - wordiness database enhancements - 653 error patterns from Grammark;
     - informal expressions detection - 228 error patterns from Wikipedia;
     - `EnglishStyleRepeatedWordRule`;
     - split infinitive detection;
     - comma splice partial detection;
     - weasel word and thought-terminating clichés detection - 87 error patterns added from Wikipedia;
     - typography of mathematical symbols, chemical formulas and number formatting;
     - easily confused words - 533 pairs added - active only with
    ngram data (see http://wiki.languagetool.org/finding-errors-using-n-gram-data);
     - other rules added and improved.
  * English rules re-activated:
     - `EnglishPlainEnglishRule` - 697 error patterns from AtD;
     - Plain English category - 105 rules;
     - `EnglishRedundancyRule` - 731 error patterns from AtD;
     - `LongSentenceRule`;
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
  * disambiguation improvements.
  * ignore spelling of roman numbers.
  * sentences and paragraphs now end with a single line break.

#### Arabic
  * Support added thanks to [Taha Zerrouki](https://github.com/linuxscout/languagetool), [Sohaib Afifi](https://github.com/sohaibafifi/languagetool), Imen Kali, and Karima Tchoketch. Support includes:
     - 117 grammar rules;
     - [spell checking](http://ayaspell.sourceforge.net/) for Algerian, Egyptian, Saudi Arabian, and Tunisian language variants;
     - [part-of-speech dictionary and synthesiser](https://github.com/sohaibafifi/languagetool).
     - a few generic typographical rules (e.g., spacing rules);
     - a few generic style rules (e.g., sentence length);

#### Danish
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `MultipleWhispacesRule`, `SentenceWhitespaceRule`,
     `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`, `ParagraphRepeatBeginningRule`,
     `PunctuationMarkAtParagraphEnd`.
  * improved tokenization.

#### Dutch
  * rules added:
     - `DutchReadabilityRule`;
     - typography rules for mathematical symbols, and chemical formulas.
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `SentenceWhitespaceRule`, `WhiteSpaceAtBeginOfParagraph`,
     `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * improved tokenization.

#### Esperanto
  * sentences and paragraphs now end with a single line break.
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`,
     `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * improved tokenization.

#### French
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `SentenceWhitespaceRule`, `WhiteSpaceAtBeginOfParagraph`,
     `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * rules added:
     - `FrenchReadabilityRule`;
     - typography rules for mathematical symbols, chemical formulas and arrows.
  * ignore spelling of roman numbers.
  * sentences and paragraphs now end with a single line break.
  * improved tokenization.

#### Galician
  * ignore spelling of roman numbers.
  * sentences and paragraphs now end with a single line break.
  * improved tokenization.

#### German
  * activated generic Java rules, including:
     - `GermanStyleRepeatedWordRule`, `LongSentenceRule`.
  * rules added:
     - typography rules for mathematical symbols, and chemical formulas.

#### Icelandic
  * spelling suggestions now active.
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `SentenceWhitespaceRule`, `WhiteSpaceAtBeginOfParagraph`,
     `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.

#### Irish
  * Support added thanks to [Jim Regan](https://github.com/jimregan/languagetool), Emily Barnes, Mícheál J. Ó Meachair,
  and Seanán Ó Coistín. Support includes:
     - 1361 grammar rules;
     - [spell checking](https://github.com/jimregan/languagetool-ga-dicts);
     - [part-of-speech dictionary and synthesiser](https://github.com/jimregan/languagetool-ga-dicts);
     - Irish naming conventions converter;
     - generic typographical rules (e.g., spacing rules);
     - generic style rules (e.g., sentence length).

#### Italian
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `SentenceWhitespaceRule`, `WhiteSpaceAtBeginOfParagraph`,
     `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * add rules:
     - typography rules for mathematical symbols, chemical formulas and arrows.
  * ignore spelling of roman numbers.
  * sentences and paragraphs now end with a single line break.

#### Khmer
  * improved tokenization.

#### Polish
  * rules added:
     - typography rules for mathematical symbols, and chemical formulas.
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `UppercaseSentenceStartRule`, `WhiteSpaceBeforeParagraphEnd`,
     `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * improved tokenization.

#### Portuguese
  * add rules:
    - vocative punctuation;
    - personal infinitive rules;
    - subject-predicate agreement;
    - specific case rules with 1867 entries mostly from Wikipedia;
    - various other rules added or improved.
  * re-activated diacritics rule;
  * Portugal Portuguese former orthographic agreement locale added (Portugal pre-AO) with support for:
     - [pt-PT pre-AO] [Dicionários Portugueses Complementares 1.2](https://github.com/TiagoSantos81/PortugueseLibreOfficeExtension);
     - common Portuguese rules;
     - pre-AO hyphenation rules;
     - wrong spelling agreement errors (`PortuguesePreAgreementReplaceRule`);
     - capitalization rules.
  * ignore spelling of roman numbers.
  * add narrow no-break space option to typography rules.
  * sentences and paragraphs now end with a single line break.
  * various localization fixes.
  * improved disambiguation.
  * improved tokenization.

#### Romenian
  * improved tokenization.

#### Spanish
  * rules added:
     - typography rules for mathematical symbols, and chemical formulas.
  * re-activated subject-predicate agreement category.
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`, `SentenceWhitespaceRule`, `WhiteSpaceAtBeginOfParagraph`,
     `EmptyLineRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * ignore spelling of roman numbers.
  * sentences and paragraphs now end with a single line break.

#### General
  * `spelling_global.txt` has 8481 extra entries on the database, including:
    - 1666 compound chemical formulas;
    - 1666 species scientific names;
    - 1666 mediatic people.
  * dependencies security updates.
  * restored some languagetool-dev and languagetool-standalone scripts.
  * [WIP] removed Google dependencies from English and Catalan language modules, as well as from several Java rules.
  * removed or comment out Freemium server bindings.
  * standalone default underline colors changed:
    - grey: style repetitions and redundancies;
    - cyan: typographical and whitespaces related suggestions;
    - red: casing and misspelings.
  * some bug fixes to java functions.

#### LibreOffice / Apache OpenOffice Integration
  * remote server commits for LibreOffice plug-in reverted (#1929).
  * multiple core CPU support active by default.

### Other changes common to both projects

#### Catalan
  * added and improved rules
  * disambiguation improvements
  * updated dictionary (catalan-pos-dict-2.6)

#### Chinese
  * Now using https://github.com/hankcs/HanLP for tokenization (PR 1981)

#### Danish
  * corrections are now offered for spell check errors
  * updated spell checker to version 2.4 (2018-04-15)
    (source: https://extensions.libreoffice.org/extensions/stavekontrolden-danish-dictionary)

#### Dutch
  * added and improved rules
  * disambiguation improvements
  * POS and spelling improvements

#### English
  * many rules added and significant rules improvements
    - `SpecificCaseRule` for case specific phrases;
    - modal verbs in conditional clauses.
  * multiwords support added
  * disambiguation improvements
  * POS and spelling improvements, including:
   - updated en_GB spellchecker dictionary from https://github.com/marcoagpinto/aoo-mozilla-en-dict (Version 2.79 - 2019-12-01)
   - updated en_US spellchecker dictionary from http://wordlist.aspell.net (Version 2019.10.06)
   - updated en_CA spellchecker dictionary from http://wordlist.aspell.net (Version 2019.10.06)
   - updated en_AU spellchecker dictionary from http://wordlist.aspell.net (Version 2019.10.06)


#### Esperanto
  * corrections are now offered for spell check errors

#### French
  * added and improved rules
  * updated spell checker (Grammalecte·dic/Dicollecte) to version 6.4.1 (2019-04-05)
    (source: https://grammalecte.net/download.php?prj=fr)
  * POS and spelling improvements, including:
    - updated part-of-speech dictionaries to [dicollecte-6.4.1](https://github.com/languagetool-org/languagetool/pull/1963)

#### German
  * added and improved rules
  * POS and spelling improvements

#### Greek
  * updated spelling dictionary to el_GR 0.9 (14/03/2019), by George Zougianos

#### Khmer
  * updated spell checker to version 1.82 (2015-10-23)
    (source: https://extensions.libreoffice.org/extensions/khmer-spelling-checker-sbbic-version)

#### Portuguese
  * added and improved rules
  * POS and spelling improvements

#### Russian
  * added new words
  * improve java rule

#### Spanish
  * added rules
  * POS and spelling improvements

#### Swedish
  * updated spelling dictionary to version 2.42 (Released Feb 03, 2019)
    (source: https://extensions.libreoffice.org/extensions/swedish-spelling-dictionary-den-stora-svenska-ordlistan)

#### General
  * `spelling_global.txt` has been added. Words or phrases added here will
    be accepted for all languages.
  * `prohibit_custom.txt` and `spelling_custom.txt` can be used to make your
    own additions to `spelling.txt` and `prohibit.txt` without having to edit those
    files after a `LanguageTool` update (you will still need to manually copy those
    files).  
    Paths to these files (`xx` = language code):  
    `./org/languagetool/resource/xx/hunspell/prohibit_custom.txt`
    `./org/languagetool/resource/xx/hunspell/spelling_custom.txt`  
    Note that you can simply create these files if they don't exist for your language yet.
  * The unmaintained code from package `org.languagetool.dev.wikipedia.atom`
    has been removed. It hadn't been maintained for years and didn't work properly
    anymore.
  * several bug fixes to java functions.

#### LibreOffice / Apache OpenOffice Integration
  * 'disable rule' option added to context menu, thanks to Fred Kruse.
  * `TextLevelRules` now have the option to check chapter blocks, thanks to Fred Kruse.
  * several bug fixes, thanks to Fred Kruse.

#### HTTP API / LT server
  * The dynamic languages feature (`lang-xx=...` and `lang-xx-dictPath=...`) now
    also supports hunspell dictionaries. Just let `lang-xx-dictPath` point to the
    absolute path of the `.dic` file. Note that hunspell is quite slow when it
    comes to offering suggestions for misspelled words.

#### Java API
  * `AbstractSimpleReplaceRule2` has been fixed so that it's now case-insensitive.
    If you implement a sub class of it and you want the old behavior, please implement
    `isCaseSensitive()` and have it return `true`. (Issue #2051)

#### Internal
  * The internal hunspell has been updated from 1.3 to 1.7, now using
    https://gitlab.com/dumonts/hunspell-java as the project providing the bindings, thanks to Daniel Naber.
    E.g., for Portuguese, misspellings suggestions generation is three times faster,
    although it is still slower than the lighter Morfologik methods.
  * Experimental: the new `default="temp_off"` attribute in `grammar.xml` files will
    turn off a rule/rulegroup, but keep it activated for our nightly regression tests.
  * Many external dependencies have been updated to new versions

