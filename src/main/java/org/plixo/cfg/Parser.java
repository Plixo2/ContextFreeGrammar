package org.plixo.cfg;

import org.plixo.cfg.exceptions.ParseException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public class Parser {

    private final static String ASSIGN = "->";
    private final static String OR = "|";
    private final static String TERMINAL = "\"";
    private final static char KEY_SEPARATOR = ' ';

    int index;

    public Parser() {
        index = 0;
    }

    public void getRule(String name, BiConsumer<String, String> result) throws ParseException {

        if (name.contains(ASSIGN)) {
            String[] split = name.split(Pattern.quote(ASSIGN));
            if (split.length == 2) {
                result.accept(split[0].strip(),split[1]);
                return;
            }
        }
        throw new ParseException(name);
    }

    public String[] getVariants(String or) {
        return or.split(Pattern.quote(OR));
    }
    public String[] getKeys(String variant) {
        List<String> str = new ArrayList<>();
        byte[] bytes =  variant.stripLeading().stripTrailing().getBytes(StandardCharsets.UTF_8);

        StringBuilder string = new StringBuilder();
        boolean inString = false;
        for (byte aByte : bytes) {
            char c = (char) aByte;
            if (c == '\"') {
                inString = !inString;
            }
            if (c == KEY_SEPARATOR && !inString) {
                str.add(string.toString());
                string = new StringBuilder();
                continue;
            }
            string.append(c);
        }
        str.add(string.toString());

        return str.toArray(new String[0]);
    }

    public boolean isKeyTerminal(String key) {
        String parse = key.strip();
        return parse.startsWith(TERMINAL) && parse.endsWith(TERMINAL);
    }
    public String removeQuotationMarks(String str) {
        return str.substring(1, str.length()-1);
    }


    private final static String abc = "abcdefghijklmnopqrstuvwxyz";

    private boolean isAlphabetic(char character) {
        return abc.contains("" + character);
    }
}
