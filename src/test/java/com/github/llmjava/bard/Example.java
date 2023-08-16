package com.github.llmjava.bard;

import org.apache.commons.configuration2.ex.ConfigurationException;

public class Example {

    public static void main(String[] args) throws ConfigurationException {
        BardConfig config = BardConfig.fromProperties("bard4j.properties");
        BardClient client = new BardClient.Builder().withConfig(config).build();

        String input = "provide a calcite example of how to get the logical plan for postgres query";

        System.out.println(client.generate(input));
    }
}
