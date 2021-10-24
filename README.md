# ContextFreeGrammar
Forward reaching contex free grammar

## grammar.txt
```
sentence -> "The " subject predicate object

subject -> "cat " | "world " | "water "
predicate -> "is " | "was "
object -> "on fire." | "lit." | "jumping."
```

## Java

```java
Grammar grammar = new Grammar();

grammar.addRules(FileIO.loadAsString("grammar.txt"));

String sentence = grammar.generateByRule("sentence", true);
System.out.println(sentence);

```

# Output
> "The cat is jumping."
