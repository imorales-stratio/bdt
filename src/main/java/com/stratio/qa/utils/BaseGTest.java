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

import com.stratio.qa.cucumber.testng.CucumberFeatureWrapper;
import com.stratio.qa.cucumber.testng.CucumberRunner;
import com.stratio.qa.cucumber.testng.PickleEventWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseGTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected CucumberRunner cucumberRunner;

    protected String browser = "";

    /**
     * Method executed before a suite.
     *
     * @param context
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeGSuite(ITestContext context) {
    }

    /**
     * Method executed after a suite.
     *
     * @param context
     */
    @AfterSuite(alwaysRun = true)
    public void afterGSuite(ITestContext context) {
        logger.info("Done executing this test-run.");
    }

    /**
     * Method executed before a test class.
     *
     * @param context
     */
    @BeforeClass(alwaysRun = true)
    public void beforeGClass(ITestContext context) throws Exception {
        ThreadProperty.set("class", this.getClass().getCanonicalName());
        cucumberRunner = new CucumberRunner(this.getClass());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Object[][] scenarios() {
        if (cucumberRunner == null) {
            return new Object[0][0];
        }
        return cucumberRunner.provideScenarios();
    }

    /**
     * Method executed after a test method.
     *
     * @param method
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeGMethod(Method method) {
        ThreadProperty.set("browser", this.browser);
    }

    /**
     * Method executed before method.
     *
     * @param method
     */
    @AfterMethod(alwaysRun = true)
    public void afterGMethod(Method method) {
    }

    /**
     * Method executed before a class.
     */
    @AfterClass(alwaysRun = true)
    public void afterGClass() {
        if (cucumberRunner == null) {
            return;
        }
        cucumberRunner.finish();
    }

    /**
     * Run scenario
     *
     * @param pickleWrapper Wrapper to obtain scenario name
     * @param featureWrapper Wrapper to obtain feature name
     * @throws Throwable
     */
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        cucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }
}
