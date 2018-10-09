# java-gte8-workshop

## Setup

### Installing JDK 11 Manually
`brew update`<br/>
`brew tap homebrew/cask-versions`<br/>
One of:<br/>
`brew cask install java`<br/>
`brew cask reinstall java`<br/>

To reinstall JDK 8 if gets overridden<br/>
`brew cask install java8`<br/>

### Install with SDKMAN! (Optional)
If you have multiple JDKs installed and need to witch between them<br/>
`curl -s "https://get.sdkman.io" | bash`

Installing JDK<br/>
`sdk install java 11`

Adding already installed versions<br/>
`sdk install java 11 /Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home/`

Setting global/local version<br/>
`sdk default java 11`
`sdk use java 11`

## Compiling and Running Modules


`javac -d target/mods/content --module-path content $(find content/src/main -name "*.java")`<br/>
`javac -d target/mods/moduleConsumer --module-path target/mods/content:moduleConsumer $(find moduleConsumer -name "*.java")`<br/>
`jlink --module-path target/mods/ --add-modules moduleConsumer --output myJre --launcher moduleConsumer=moduleConsumer/consumer.Consumer`<br/>
`./myJre/bin/moduleConsumer`<br/>