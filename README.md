# java-gte8-workshop

## Setup

### Update your IDE (Optional but recommended)
If you use IntelliJ, VSCode, Eclipse or NetBeans(!) you might want to update your IDE or its Java plugin, all of them give support to Java 11 already.

### Installing JDK 11 Manually (or use SDKMAN!, see below)
* `brew update`
* `brew tap homebrew/cask-versions`
<br/>One of:
* `brew cask install java`
* `brew cask reinstall java`

To reinstall JDK 8 if gets overridden
* `brew cask install java8`

### Install with SDKMAN! (Optional)
If you have multiple JDKs installed and need to switch between them I recommend you to install SDKMAN!: 
* `curl -s "https://get.sdkman.io" | bash`

Installing new JDKs
* `sdk install java 11`

Adding an already installed JDKs
* `sdk install java 11 /Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home/`

Setting global/local version
* `sdk default java 11`
* `sdk use java 11`

## Boilerplate disclaimer
There are two methods that are statically imported everywhere to save some boilerplate code: `delayedResult` and `println`, these are not Java 8-11 features (although I wish they were). They come from the `boilerplate.Boilerplate` class.

## Compiling Modules and Running Custom JREs
For the JLink explanation we're going to use the following commands:

```
javac -d target/mods/content --module-path content $(find content/src/main -name "*.java")
javac -d target/mods/moduleConsumer --module-path target/mods/content:moduleConsumer $(find moduleConsumer -name "*.java")
jlink --module-path target/mods/ --add-modules moduleConsumer --output myJre --launcher moduleConsumer=moduleConsumer/consumer.Consumer
./myJre/bin/moduleConsumer
```
