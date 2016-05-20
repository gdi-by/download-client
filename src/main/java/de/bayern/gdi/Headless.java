/*
 * DownloadClient Geodateninfrastruktur Bayern
 *
 * (c) 2016 GSt. GDI-BY (gdi.bayern.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bayern.gdi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bayern.gdi.model.DownloadStep;

/**
 * The command line tool.
 */
public class Headless {

    private static final Logger log
        = Logger.getLogger(Headless.class.getName());

    private Headless() {
    }

    /**
     * @param downloadConfig The path to the downloadConfig file.
     * @param args The command line arguments.
     * @return Non zero if the operation fails.
     */
    public static int main(String downloadConfig, String [] args) {
        log.info("Running in headless mode");

        File downloadConfigFile = downloadConfig != null
            ? new File(downloadConfig)
            : new File(
                new File(System.getProperty("user.home", "~")),
                    "downloadConfig.xml");

        log.info("Using download config file: "
            + downloadConfigFile);

        DownloadStep dls;
        try {
            dls = DownloadStep.read(downloadConfigFile);
        } catch (IOException ioe) {
            log.log(
                Level.SEVERE,
                "Cannot find downloadConfig file: "
                    + downloadConfigFile,
                ioe);
            return 1;
        }

        // TODO: Convert DownloadStep into
        //       sequence of processor jobs.
        //
        return 0;
    }
}
