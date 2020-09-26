# LibreGrammar Change Log

## 5.1 (release planned for 2020-10-01)

### This fork differences
#### (new comparisons only)

#### General
  * Each pair of `ConfusionProbabilityRule` has its own ID now, so it can be separately turned on/off


#### Catalan
  * added a few punctuation rules

#### English
  * added and improved rules
  * disambiguation improvements
  * POS and spelling improvements

#### French
  * re-actived noun number agreement rulegroup

#### German
  * formal speech rules, including:
    - colloquialism detection.

#### Portuguese
  * added and improved rules, including:
    - re-activation of rules that were de-activated upstream in this realease
  * disambiguation improvements
  * POS and spelling improvements
  
#### Spanish
  * add and improved rules, including:
    - formal writing category added with detection for vulgar speech and barbarisms usage
    - re-activate some agreement rules and style rules

### Other changes common to both projects

#### Catalan
  * added and improved rules
  * POS and spelling improvements

#### Dutch
  * added and improved rules
  * POS and spelling improvements

#### English
  * added and improved rules
  * POS and spelling improvements
  * updated en_GB spellchecker dictionary from https://github.com/marcoagpinto/aoo-mozilla-en-dict (Version 2.88 - 2020-09-01)


#### French
  * added and improved rules, thanks to Jaume Órtola, Rigaut Theótime and thelittlefireman
  * [spell checking now uses Morfologik instead of Hunspell](https://github.com/languagetool-org/languagetool/commit/d7b866b)

#### German
  * added and improved rules, thanks to Christopher Blum and Florian Knorr
  * POS and spelling improvements

#### Portuguese
  * disambiguation improvements
  * POS and spelling improvements

#### Russian
  * added and improved rules

#### Spanish
  * added and improved rules
  * POS and spelling improvements

#### Ukrainian
  * dictionary update
  * many new punctuation rules
  * many new styling rules
  * tokenization and tagging improvements
  * disambiguation improvements

#### LibreOffice / Apache OpenOffice Integration
  * dedicated custom spellchecker dialog, that allows greater control of LibreGrammar's functions, thanks to Fred Kruse

#### General
  * various fixes and improvements to abstract replacement rules, including:
    - retrieve only the longest string when matches overlap;
    - possibility of adding exceptions.
  * back-end work to allow tagging rule as 'picky', so a subset of rules can be
    optionally activated.
    LibreGrammar does not use this implementation yet and activates by default a rule set
    considered adequate for formal writing, which includes many rules tagged as 'picky'
    in the upstream project.
  * New XML attribute `chunk_re` for `<token>`, which specifies a chunk as a regular expression
  * removed optional AfterTheDeadline server mode

## 5.0 ( 2020-10-01)

### This fork differences
#### (new comparisons only)

#### Arabic
  * mostly work from [Tara Zerrouki](https://github.com/linuxscout/languagetool)
  * 69 rules re-added (probably lost on upstream merge)
  * 88 diacritic rules re-added (probably lost on upstream merge)
  * homophones rules check lemmas

#### Asturian
  * activated generic Java rules, including: `SentenceWhitespaceRule`,
    `WhiteSpaceBeforeParagraphEnd`, `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`,
    `LongSentenceRule`, `LongParagraphRule`, `ParagraphRepeatBeginningRule`,
    `PunctuationMarkAtParagraphEnd`

#### Catalan
  * activated generic Java rules, including: `SentenceWhitespaceRule`,
    `WhiteSpaceBeforeParagraphEnd`, `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`,
    `LongParagraphRule`, `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.
  * added `CatalanStyleRepeatedWordRule`

#### Dutch
  * [restored 256 word confusion rules](https://github.com/TiagoSantos81/languagetool/commit/ff74bea)

#### Esperanto
  * added URLs explaining most single word suggestions

#### English
  * added and improved rules, including:
    - several agreement rules added or significantly improved;
    - archaic language detection;
    - revert several crippling changes;
    - explanation URLs added to ~600 rules.
  * disambiguation improvements
  * use `UpperCaseRule` instead of `UpperCaseNgramRule` by default

#### French
  * added `FrenchStyleRepeatedWordRule`
  * 17 n-gram confusion pairs added

#### Galician
  * added URLs explaining most single word suggestions

#### German
  * formal speech rules, including:
    - archaisms detection;
    - profanity detection;
    - T-V distinction rules.

#### Italian
  * [re-enable rules](https://github.com/TiagoSantos81/languagetool/commit/0176b6cb378ed39f3e7e39ef25cab026362d947b):
    - determiner agreement rules;
    - adverb instead of adjective rules;
    - start of sentence with conjunction (style rule);
    - verb form agreement rulegroup;
    - repetitions rulegroup (style);
    - agreement rules;
    - several loose word confusion rules.
  * enable by default:
    - verbose small number (style).
  * added URLs explaining most single word suggestions

#### Papamiento
  * initial support added thanks to [Manuel Ortega](https://github.com/ortegacmanuel)
    - spellchecking support added
    - generic Java rules added, including: `CommaWhitespaceRule`, `DoublePunctuationRule`,
    `GenericUnpairedBracketsRule`, `MorfologikAsturianSpellerRule`,
    `UppercaseSentenceStartRule`, `MultipleWhitespaceRule`, `SentenceWhitespaceRule`,
    `WhiteSpaceBeforeParagraphEnd`, `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`,
    `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`

#### Persian
  * [re-enable rules](https://github.com/TiagoSantos81/languagetool/commit/3f9277466c3a42c790cefecc71b160b39f2305eb), including:
    - `Complex_Past_Verbs` rulegroup;
    - `Complex_Present_Verbs` rulegroup;
    - `PluralFix` rulegroup;
    - `PersianCommaWhitespaceRule`;
    - `PersianSpaceBeforeRule`.

#### Portuguese
  * added and improved rules, including:
    - new agreement rules;
    - removal of many crippling changes made upstream;
    - URLs added to explain most single word suggestions;
    - style rule regarding repeated words now also suggests alternative words on LibreOffice.
  * [rules disabled up-stream are still active in LibreGrammar](https://github.com/languagetool-org/languagetool/issues/3158)
  * disambiguation improvements
  * POS and spelling improvements

#### Russian
  * activated generic Java rules, including:
     - `WhiteSpaceBeforeParagraphEnd`, `EmptyLineRule`, `PunctuationMarkAtParagraphEnd`.

#### Spanish
  * generic Java rules added
    - `LongSentenceRule`, `LongParagraphRule`

#### Slovak
  * [improve and re-enable rules](https://github.com/TiagoSantos81/languagetool/commit/9acbaa5e4358da17f4f4f269d2853ce30bb5a486):
    - five adjective-noun agreement rulegroups (83 rules).

#### Ukranian
  * activated generic Java rules, including:
     - `LongSentenceRule`, `LongParagraphRule`,  `SentenceWhitespaceRule`,
     `WhiteSpaceAtBeginOfParagraph`, `WhiteSpaceBeforeParagraphEnd`, `EmptyLineRule`,
     `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd`.

#### LibreOffice / Apache OpenOffice Integration
  * style review rules show synonyms by default

#### Standalone Tool
  * suggestions limit increased to 10 results

#### General
  * commented out all new remote code commits, so they become inactive (e.g., [ac960f9](https://github.com/TiagoSantos81/languagetool/commit/ac960f9b24a41b53ec245ee6fab3aad2c454b020))
  * [removed unused dependencies](https://github.com/TiagoSantos81/languagetool/commit/68105705732c95cfc358e55598580627bd66063c)
  * spellchecking now checks words with numbers in all languages

### Other changes common to both projects

#### Arabic
  * [added many rules and improved existing rules](https://github.com/linuxscout/languagetool)
  * updated POS dictionary ([Arramooz](https://github.com/linuxscout/arramooz/commit/e33794e787d56e7c185c0e281fd8e6d6274f3fdc))
  * remove the Algerian variant (ar-DZ)
  * [add support of ngram data](https://github.com/sohaibafifi/languagetool-tools-ar), thanks to Sohaib Afifi.
  * add Darja, Diacritics, Redundancy, WrongWordInContext, Wordiness, Homophones and WordCoherency rules, thanks to Sohaib Afifi.

#### Catalan
  * added and improved rules
  * updated dictionary (catalan-pos-dict-2.8), now with a specific dictionary file for spelling suggestions

#### Dutch
  * added and improved rules

#### English
  * added and significantly improved rules, including:
    - `Use 'to' before the base form of a verb` now also activated upstream
    - `UpperCaseNgramRule`, a stricter version of `UpperCaseRule` that
    requires n-gram data
  * spelling and POS improvements
  * [updated en_GB spellchecker dictionary by Marco A.G. Pinto](https://github.com/marcoagpinto/aoo-mozilla-en-dict) (Version 2.85 - 2020-06-01)

#### French
  * added and improved rules, thanks to [Rigaut Theótime](https://github.com/vkyfox) and [Jaume Ortolà](https://github.com/jaumeortola/spanish-dict-tools)
  * spelling improvements

#### German
  * added and improved rules
  * rules that apply to de-DE and de-AT (but not de-CH) can now be placed in `de/de-DE-AT/grammar.xml`
  * spelling and POS improvements
  * Updated the [German part-of-speech dictionary](https://github.com/languagetool-org/german-pos-dict) to version 1.2.1.

#### Italian
  * added and improved rules

#### Russian
  * added and improved rules, including:
    - `WhiteSpaceAtBeginOfParagraph`, `LongSentenceRule`, `LongParagraphRule`
    `ParagraphRepeatBeginningRule`, `RussianFillerWordsRule`,
    `MorfologikRussianYOSpellerRule`.
  * added new Java rules
  * rebuilt and improved main spellchecker dictionary, added many new words
  * new variant (only yo "ё") spellchecker dictionary and new java rule for it (set off by default)
  * new filter's arguments: prefix and suffix to be used for matching the part-of-speech of parts of words 
    with prefix and suffix added to original token, e.g.:
```xml
       <filter class="org.languagetool.rules.ru.RussianPartialPosTagFilter" 
                args="no:2 regexp:(.*) postag_regexp:(ADV) prefix:не suffix:  "/>
```

#### Spanish
  * added many rules and significantly improved existing ones, thanks to [Jaume Ortolà](https://github.com/jaumeortola/spanish-dict-tools), including:
    - [agreement rules](https://github.com/languagetool-org/languagetool/commit/d197a2de6d7703ecf33c5a9bbcac5e38fead7276);
    - semantic rules;
    - several typography rules;
    - word-confusion rules;
    - punctuation rules.
  * spelling and POS improvements
    - [new tagger dictionary by Jaume Ortolà, LGPL](https://github.com/jaumeortola/spanish-dict-tools)

#### Ukrainian
  * dictionary update, including many rare and slang words
  * new rules, including:
    - archaisms detection;
    - vulgar language detection.
  * tokenization and tagging improvements
  * disambiguation improvements

#### LibreOffice / Apache OpenOffice Integration
  * most work done by [Fred Kruse](https://github.com/languagetool-org/languagetool/commits?author=fredkruse), including:
    - add-on toolbar added
    - custom spellings added to `spelling.txt` are now also ignored by LibreOffice
    spellchecking service
    - new performance settings added to the options panel

#### General
  * added `replace_custom.txt` for several languages so users can have their 
    own very simple replace rules without worrying about updates (they still
    need to copy the file to the new LT version, though).
  * server logging functions removed
    - revert 553ee42 to re-enable them

## 4.9 (released 2020-04-24)

### This fork differences
#### (new comparisons only)

#### Breton
  * activated generic Java rules, including:
     - `GenericUnpairedBracketsRule`, `WordRepeatRule`, `LongSentenceRule`, `LongParagraphRule`, 
     `WhiteSpaceBeforeParagraphEnd`, `WhiteSpaceAtBeginOfParagraph`, `EmptyLineRule`, 
     `ParagraphRepeatBeginningRule`, `PunctuationMarkAtParagraphEnd` .

#### Dutch
  * restored old [prefered words database](https://github.com/TiagoSantos81/languagetool/commit/5a3084a92142349926ee5f9772ea63eb100573fb)
    since performance issues were fixed in the meantime.

#### English
  * rules added and improved, including:
     - agreement rules;
     - slang terms.
  * improves accuracy and recall of n-gram based confusion sets by using directional
  n-grams.
  * disambiguation improvements
  * POS and spelling improvements
     - [updated en_GB spellchecker dictionary by Marco A.G. Pinto](https://github.com/marcoagpinto/aoo-mozilla-en-dict) (Version 2.83 - 2020-04-01)
     - scientific nomenclature POS support added

#### Esperanto
  * added and improved rules

#### French
  * added and improved rules

#### Portuguese
  * rules added and improved, including:
     - mesoclisis related rules added.
  * revert all `grammar.xml` commits from upstream project, and replace those with appropriate solutions for
    the false positives they try to fix. Most upstream solutions are either semantically incorrect, or too
    crippling to detection while not providing meaningful improvements to accuracy.

#### Russian
  * added and improved rules

#### Spanish
  * added `SpanishStyleRepeatedWordRule`

#### General
  * 1547 species taxa entries added to `spelling_global.txt`.
  * revert remote rules implementation and Google's BERT suggestion sorting.
  * false friends rules will not depend on n-grams, and they will always 
  report false friends, since known words can be disabled individually.
     N-grams usage is not promoted in this project since the files are too
  big to be packaged with the main program, and they make the program too slow
  to be used for real-time checking (see 1956c54).

#### LibreOffice / Apache OpenOffice Integration
  * default rule underline style is 'bold wave'. Only applies to LibreOffice version 6.3.0 or superior.

### Other changes common to both projects

#### Arabic
  * support now also available on upstream project 
    (https://github.com/languagetool-org/languagetool/pull/2219);
  * rules added and improved.

#### Breton
  * compound rule added:
     - 4101 entries thanks to [Pierre Morvan](https://github.com/languagetool-org/languagetool/issues/2300).

#### Catalan
  * added and improved rules
  * updated dictionary (catalan-pos-dict-2.8), now with a specific dictionary file for spelling suggestions

#### Dutch
  * added and improved rules, including:
    - MissingSpaceRule.
  
#### English
  * significantly improve rule set with many rules added and improved, including:
    - rule to verify wrongly uppercased words.
  * false friends improvements:
    - 184 entries added to English for French natives;
    - EnglishForFrenchFalseFriendRule false friends n-gram based rule;
  * disambiguation improvements
  * POS and spelling improvements, including:
    - [updated en_GB spellchecker dictionary Marco A.G. Pinto](https://github.com/marcoagpinto/aoo-mozilla-en-dict) (Version 2.82 - 2020-03-01)
    - new part-of-speech tag `ORD` for ordinal numbers (e.g., first, second, twenty-third etc.)

#### Esperanto
  * added and improved rules

#### French
  * various rules improvements, thanks to Rigaut Theótime (vkyfox) and Dpelle
  * POS and spelling improvements
  * false friends improvements:
    - 162 entries added to French for English natives;

#### German
  * added and improved rules
  * `compounds.txt` now automatically expands `ß` to `ss` when using German (Switzerland)
  * German `spelling.txt` now supports `prefix_verb` syntax like `vorüber_eilen` so
    the speller will accept all forms of "eilen" prefixed by "vorüber" 

#### Irish
  * support now also available on upstream project
    (https://github.com/languagetool-org/languagetool/pull/2260);
  * rules added and improved.

#### Portuguese
  * some entries added to rules databases
  * POS and spelling improvements

#### Russian
  * rules added and improved

#### Spanish
  * added and improved rules, thanks to Jaume Órtola, including:
    - SimpleReplaceRule;
    - SimpleReplaceVerbsRule;
    --SimpleReplaceAnglicismRule.
  * new tagger dictionary by Jaume Ortolà, LGPL, source: https://github.com/jaumeortola/spanish-dict-tools

#### Ukrainian
  * dictionary update
  * new rules
  * tokenization and tagging improvements

#### General
  * dependency security update
  * [upstream hunspell support dropped for 32-bit systems](https://github.com/languagetool-org/languagetool/commit/b769f5ec319a53c10c45a8aa33a9fd6a4600792c).
  * `AbstractReplaceRule2` now accepts per entry custom messages
  * [translation framework for secondary languages in a text](https://github.com/languagetool-org/languagetool/commit/209fc7dc97bd5c92a3c87769795545491642df34).
  * ~250 extra entries added to `spelling_global.txt`.

#### LibreOffice / Apache OpenOffice Integration
  * define and use custom underline types in LibreOffice 6.3.0 or superior, thanks to Fred Kruse.
  * more responsive rule queue mechanism, thanks to Fred Kruse.
  * various optimizations and stability improvements, thanks to Fred Kruse.

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
   - [updated en_GB spellchecker dictionary by Marco A.G. Pinto](https://github.com/marcoagpinto/aoo-mozilla-en-dict) (Version 2.79 - 2019-12-01)
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

