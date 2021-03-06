/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.teradata.presto.yarn.test.utils;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resources
{
    private static final File tempDir = Files.createTempDir();

    static {
        tempDir.deleteOnExit();
    }

    public static Path extractResource(String resourcePath)
    {
        URL fixScriptResource = Resources.class.getResource(resourcePath);
        File temporaryFile = new File(tempDir, Paths.get(resourcePath).getFileName().toString());
        try {
            FileUtils.copyURLToFile(fixScriptResource, temporaryFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Paths.get(temporaryFile.toURI());
    }

    private Resources() {}
}
