package org.plixo.cfg.test;

import org.plixo.cfg.Grammar;
import org.plixo.cfg.ParseException;
import org.plixo.cfg.VocabularyException;
import org.plixo.cfg.io.FileIO;

public class Main {
    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        try {
            grammar.addRules(FileIO.loadAsString("res/grammar.txt"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        grammar.debug();

        try {
            if(grammar.doesRuleExist("code")) {
                String sentence = grammar.generateByRule("code", true);
                System.out.println(sentence);
            } else {
                String sentence = grammar.generateByRule("sentence", true);
                System.out.println(sentence);
            }



        } catch (VocabularyException e) {
            e.printStackTrace();
        }

    }
}
