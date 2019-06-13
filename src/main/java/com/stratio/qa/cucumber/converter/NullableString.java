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

package com.stratio.qa.cucumber.converter;

public class NullableString {
    private static final int DEFAULT_RADIX = 16;

    public static String transform(String input) {
        if ("//NONE//".equals(input)) {
            return "";
        } else if ("//NULL//".equals(input)) {
            return null;
        } else if (input.startsWith("0x")) {
            int cInt = Integer.parseInt(input.substring(2), DEFAULT_RADIX);
            char[] cArr = Character.toChars(cInt);
            return String.valueOf(cArr);
        } else {
            return input;
        }
    }
}
