package org.plixo.cfg.test;

import org.plixo.cfg.Grammar;
import org.plixo.cfg.exceptions.ParseException;
import org.plixo.cfg.exceptions.VocabularyException;
import org.plixo.cfg.io.FileIO;

public class Main {
    public static void main(String[] args) throws VocabularyException, ParseException {
        Grammar grammar = new Grammar();

        grammar.addRules(FileIO.loadAsString("res/grammar.txt"));

        String sentence = grammar.generateByRule("sentence", true);
        System.out.println(sentence);

    }
}
