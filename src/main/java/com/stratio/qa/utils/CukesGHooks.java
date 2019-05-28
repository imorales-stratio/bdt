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

package com.stratio.qa.utils;

import com.stratio.qa.cucumber.testng.TestSourcesModel;
import com.stratio.qa.cucumber.testng.TestSourcesModelUtil;
import com.stratio.qa.specs.BaseGSpec;
import com.stratio.qa.specs.HookGSpec;
import cucumber.api.PickleStepTestStep;
import cucumber.api.event.*;
import cucumber.api.event.EventListener;
import cucumber.api.formatter.StrictAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CukesGHooks extends BaseGSpec implements EventListener, StrictAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    private String currentFeatureFile = null;

    private boolean isLastStepBackground = false;

    public CukesGHooks() {
    }

//    private EventHandler<TestSourceRead> testSourceReadHandler = new EventHandler<TestSourceRead>() {
//        @Override
//        public void receive(TestSourceRead event) {
//            handleTestSourceRead(event);
//        }
//    };
//
//    private EventHandler<TestStepStarted> stepStartedHandler = new EventHandler<TestStepStarted>() {
//        @Override
//        public void receive(TestStepStarted event) {
//            handleTestStepStarted(event);
//        }
//    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
//        publisher.registerHandlerFor(TestSourceRead.class, testSourceReadHandler);
//        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
    }

    @Override
    public void setStrict(boolean strict) {
    }

//    private void handleTestSourceRead(TestSourceRead event) {
//        TestSourcesModelUtil.INSTANCE.getTestSourcesModel().addTestSourceReadEvent(event.uri, event);
//    }
//
//    private void handleTestStepStarted(TestStepStarted event) {
//        if (HookGSpec.loggerEnabled) {
//            if (event.testStep instanceof PickleStepTestStep) {
//                PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;
//                TestSourcesModel.AstNode astNode = TestSourcesModelUtil.INSTANCE.getTestSourcesModel().getAstNode(currentFeatureFile, testStep.getStepLine());
//                if (astNode != null) {
//                    if (TestSourcesModel.isBackgroundStep(astNode)) {
//                        if (!isLastStepBackground) {
//                            logger.info(" Background:");
//                        }
//                        isLastStepBackground = true;
//                    } else {
//                        if (isLastStepBackground) {
//                            logger.info(" Steps:");
//                        }
//                        isLastStepBackground = false;
//                    }
//                }
//            }
//        }
//    }
}