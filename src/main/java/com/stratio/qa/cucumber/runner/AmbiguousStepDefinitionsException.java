/*
 * Copyright (C) 2014 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.qa.cucumber.runner;

import cucumber.runtime.CucumberException;
import gherkin.pickles.PickleStep;

import java.util.List;

final public class AmbiguousStepDefinitionsException extends CucumberException {
    private final List<PickleStepDefinitionMatch> matches;

    AmbiguousStepDefinitionsException(PickleStep step, List<PickleStepDefinitionMatch> matches) {
        super(createMessage(step, matches));
        this.matches = matches;
    }

    private static String createMessage(PickleStep step, List<PickleStepDefinitionMatch> matches) {
        StringBuilder msg = new StringBuilder();
        msg.append(quoteText(step.getText())).append(" matches more than one step definition:\n");
        for (PickleStepDefinitionMatch match : matches) {
            msg.append("  ").append(quoteText(match.getPattern())).append(" in ").append(match.getLocation()).append("\n");
        }
        return msg.toString();
    }

    private static String quoteText(String text) {
        return "\"" + text + "\"";
    }

    public List<PickleStepDefinitionMatch> getMatches() {
        return matches;
    }
}

