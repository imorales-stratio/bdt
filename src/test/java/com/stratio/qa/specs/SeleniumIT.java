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
package com.stratio.qa.specs;

import com.stratio.qa.cucumber.testng.CucumberRunner;
import com.stratio.qa.utils.BaseTest;
import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import com.stratio.qa.data.BrowsersDataProvider;

@CucumberOptions(plugin = "json:target/cucumber.json", features ={
        "src/test/resources/features/readWebElementTextToVariable.feature",
        "src/test/resources/features/assertSeleniumNElementExistsIT.feature",
        "src/test/resources/features/assertSeleniumNElementExistsOnTimeOutIT.feature"
})
public class SeleniumIT extends BaseTest {

    @Factory(enabled = false, dataProviderClass = BrowsersDataProvider.class, dataProvider = "availableUniqueBrowsers")
    public SeleniumIT(String browser) {
        this.browser = browser;
    }

    @Test
    public void seleniumTest() throws Exception {
        //new CucumberRunner(this.getClass()).runCukes();
    }
}
