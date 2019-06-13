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

package com.stratio.qa.aspects;

import cucumber.runner.Runner;
import gherkin.events.PickleEvent;
import gherkin.pickles.PickleTag;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
public class MandatoryAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @Pointcut("execution (* cucumber.runner.Runner.runPickle(..)) && "
            + "args (pickle)")
    protected void addMandatoryPointcutScenario(PickleEvent pickle) {
    }

    /**
     * @param pjp ProceedingJoinPoint
     * @param pickle pickle
     * @throws Throwable exception
     */
    @Around(value = "addMandatoryPointcutScenario(pickle)")
    public void aroundAddMandatoryPointcut(ProceedingJoinPoint pjp, PickleEvent pickle) throws Throwable {
        Runner runner = (Runner) pjp.getThis();

        Class<?> sc = runner.getClass();
        Method tt = sc.getDeclaredMethod("buildBackendWorlds");
        tt.setAccessible(true);
        tt.invoke(runner);

        String scenarioName = pickle.pickle.getName();
        List<PickleTag> pickleTagList = pickle.pickle.getTags();
        List<String> tagList = new ArrayList<>();
        for (PickleTag pt:pickleTagList) {
            tagList.add(pt.getName());
        }

        boolean exec = manageTags(tagList);
        if (!exec) {
            logger.error("Feature will not be executed. Mandatory variables not defined.");
        } else {
            pjp.proceed();
        }
    }

    public boolean manageTags(List<String> tagList) {
        boolean exec = true;
        if (tagList.contains("@mandatory")) {
            for (String tag: tagList) {
                Pattern pattern = Pattern.compile("@vars\\((.*?)\\)");
                Matcher matcher = pattern.matcher(tag);
                if (matcher.find()) {
                    String[] vars = matcher.group(1).split(",");

                    for (String var: vars) {
                        if (System.getProperty(var) == null) {
                            logger.warn("Mandatory variable '" + var + "' has not been defined.\n");
                            exec = false;
                        }
                    }
                }
            }
        }
        return exec;
    }
}