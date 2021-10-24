package org.plixo.cfg;

import org.plixo.cfg.exceptions.ParseException;
import org.plixo.cfg.exceptions.VocabularyException;

import java.util.*;

public class Grammar {

    private final static String newLine = System.getProperty("line.separator");
    private final static Random random = new Random();
    Map<String, List<String[]>> rules = new HashMap<>();


    Parser parser = new Parser();

    public Grammar() {

    }

    public void addRules(String lines) throws ParseException {
        for (String line : lines.split(newLine)) {
            addRule(line);
        }
    }

    String name = "";
    String value = "";

    public void addRule(String rule) throws ParseException {
        if(rule.isEmpty()) {
            return;
        }

        parser.getRule(rule, (ref0, ref1) -> {
            name = ref0;
            value = ref1;
        });
        String[] variants = parser.getVariants(value);
        for (String variant : variants) {
            List<String[]> orDefault = rules.getOrDefault(name, new ArrayList<>());
            String[] keys = parser.getKeys(variant);
            orDefault.add(keys);
            rules.put(name, orDefault);

        }



        System.out.println(name);
    }

    public void debug() {
        rules.forEach((k,v) -> {
            System.out.println("");
            System.out.println("Key: " + k);
            v.forEach(ref -> {
                System.out.println(Arrays.toString(ref));
            });

        });
        System.out.println("");
    }
    public boolean doesRuleExist(String rule) {
        return rules.containsKey(rule);
    }

    public String generateByRule(String rule , boolean randomize) throws VocabularyException {

        if(!rules.containsKey(rule)) {
            throw new VocabularyException(rule + " rule is not in the Vocabulary");
        }

        List<String[]> strings = rules.get(rule);

        if(strings.size() == 0) {
            throw new VocabularyException(rule + " doesnt contain any rules");
        }
        int index = 0;
        if(randomize) {
            index = random.nextInt(strings.size());
        }
        String[] aRule = strings.get(index);
        StringBuilder builder = new StringBuilder();
        for (String object : aRule) {
            if(parser.isKeyTerminal(object)) {
                String str = parser.removeQuotationMarks(object);
                builder.append(str);
            } else {
                System.out.println(rule + " last");
                builder.append(generateByRule(object,randomize));
            }
        }

        return builder.toString();
    }

    public String getValueByName(String rule) {
        return "";
    }
}
