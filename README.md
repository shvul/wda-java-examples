# wda-java-examples

Repository shows basic use cases of using [WDA Java Client](https://github.com/shvul/WebDriverAgent) for tvOS automation

## Setup
Initiate submodules:
```
git submodule update --init --recursive
```
Move to WebDriverAgent folder:
```
cd ./WebDriverAgent
```
Switch to application-commands branch to use application install/activate/launch commands:
```
git checkout application-commands
```
Build carthage dependencies:
```
sh ./Scripts/bootstrap.sh
```

Open xcode project and sign `WebDriverAgentRunner_tvOS` target under your provisioning profile.

Specify device id and ip in the `TestNGSuiteConfig.xml`

Run tests via `TestNGSuiteConfig.xml`
