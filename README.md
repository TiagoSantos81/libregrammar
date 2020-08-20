## LibreGrammar[![Build Status](https://travis-ci.org/TiagoSantos81/languagetool.png)](https://travis-ci.org/TiagoSantos81/languagetool)

This is a **LanguageTool** fork, an Open Source proofreading software for English, French, German,
Polish, Portuguese, Russian, and [other 30 languages](https://github.com/TiagoSantos81/languagetool/tree/master/languagetool-language-modules).

Due to increased hostility related to development divergences, the need for another fork arose. 
More noticeably since late 2017, LanguageTool has had its development constrained by several factors, namely the 
conflict of interests inherent to the existence of a Freemium sister project and the integration 
of contributors owning other derivative commercial projects. LanguageTool development constrains
seem to have been partially addressed since LibreGrammar's inception.

This fork intends to be a **pure free and open-source software** 'editor', developed for [end users](https://en.wikipedia.org/wiki/End_user), so, 
it enables several rules not allowed to be enabled by default in the open-source component, reverts commits in the main branch that degrade the work previously done, and adds rules 
that could be disabled on the grounds of being 'too picky' by some elements of the former team.
In addition, this project has reverted all remote access connections code, updated dependencies 
that have known security vulnerabilities, and removed Freemium bindings.

Future work may also involve making the add-ons work entirely offline, and replacing n-gram rules by faster and lighter XML 
or [word2vec](https://github.com/gulp21/languagetool-neural-network) alternatives that can be shipped with the main package.

Everyone is still welcome to use and comment on this code according to the licencing terms, but feedback 
will only be considered if it has the end-users best interests in mind.

___

## [README](https://github.com/TiagoSantos81/languagetool/blob/master/languagetool-standalone/README.md)   |   [CHANGES](https://github.com/TiagoSantos81/languagetool/blob/master/languagetool-standalone/CHANGES.md)   |   [DOWNLOAD](https://github.com/TiagoSantos81/languagetool/releases)

LanguageTool is freely available under the LGPL 2.1 or later.

___

## Scripted installation and building
To install or build using a script, simply type:
```
curl -L https://raw.githubusercontent.com/TiagoSantos81/languagetool/master/install.sh | sudo bash <options>
```

If you wish to have more options, download the install.sh script. Usage options follow:

```
sudo bash install.sh <options>

Usage: install.sh <option> <package>
Options:
   -h --help                   Show help
   -b --build                  Build packages from the bleeding edge development copy of LibreGrammar
   -c --command <command>      Specify post-installation command to run (default gui when screen is detected)
   -q --quiet                  Silence LibreGrammar installer. Only displays important information.
   -t --text <file>            Specify what text to be spellchecked by LibreGrammar command line (default spellcheck.txt)
   -d --depth <value>          Specify the depth to clone when building LibreGrammar yourself (default 1).
   -p --package <package>      Specify package to install when building (default all)
   -o --override <OS>          Override automatic OS detection with <OS>
   -a --accept                 Agree to all downloading and installing prompts.
   -r --remove <all/partial>   Remove LibreGrammar install. <all> uninstalls the dependencies that were auto-installed. (default partial)

Packages(only if -b is specified):
   standalone                  Install standalone package
   wikipedia                   Install Wikipedia package
   office-extension            Install the LibreOffice/OpenOffice extension package

Commands:
   GUI                         Run GUI version of LibreGrammar
   commandline                 Run command line version of LibreGrammar
   server                      Run server version of LibreGrammar
```

## Alternate way to build from source

Before start: you will need to clone from GitHub and install Java 8 and Apache Maven.

Warning: a complete clone requires downloading more than 360 MB and needs more than 500 MB on disk.
This can be reduced if you only need the last few revisions of the master branch
by creating a shallow clone:

    git clone --depth 5 https://github.com/TiagoSantos81/languagetool.git

A shallow clone downloads less than 60 MB and needs less than 200 MB on disk.

In the root project folder, run:

    mvn clean test

(sometimes you can skip Maven step for repeated builds)

    ./build.sh languagetool-standalone package -DskipTests

Test the result in `languagetool-standalone/target/`.

    ./build.sh languagetool-wikipedia package -DskipTests

Test the result in `languagetool-wikipedia/target`.

    ./build.sh languagetool-office-extension package -DskipTests

Test the result in `languagetool-office-extension/target`, rename the `*.zip` to `*.oxt` to install it in LibreOffice/OpenOffice.

Now you can use the bleeding edge development copy of LanguageTool `*.jar` files, be aware that it might contain regressions.

### License

Unless otherwise noted, this software is distributed under the LGPL, see file [COPYING.txt](https://github.com/TiagoSantos81/languagetool/blob/master/COPYING.txt).
