# RPAL-Interpreter
Interpret RPAL abstract syntax trees and give the output of the program.

## Executing the program.
Before the execution need to compile the java classes. For that run
```shell
make
```
Then, it will compile the java classes to the root directory.
Execute the program with following command.
```shell
java myrpal <file-name>
```

Note: 
* Replace `<file-name>` with the path to the file containing AST.
* Used java version `18`
* Used `intellij` for development. So, code can be compiled and executed with `intellij build system`
