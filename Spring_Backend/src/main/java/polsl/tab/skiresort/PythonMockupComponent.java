package polsl.tab.skiresort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;

enum OS {
    WINDOWS("cmd"),
    LINUX("bash"),
    MAC("bash"),
    NONE("");

    public final String commandLineRunner;

    OS(String commandLineRunner) {
        this.commandLineRunner = commandLineRunner;
    }
}

@Component
public class PythonMockupComponent {

    private static final Logger logger = LoggerFactory.getLogger(PythonMockupComponent.class);

    private ProcessBuilder processBuilder;

    private final String pythonCommandLineInterpreter;

    private final String pythonMockupAbsolutePathToMain;

    private final String postgresUsername;

    private final String postgresPassword;

    private OS checkOS() {
        var os = System.getProperty("os.name").toLowerCase();
        if (os.contains("unix") || os.contains("nux") || os.contains("aix")) {
            return OS.LINUX;
        } else if (os.contains("win")) {
            return OS.WINDOWS;
        } else if (os.contains("mac")) {
            return OS.MAC;
        } else {
            return OS.NONE;
        }
    }

    private void runtime() {
        try {
            logger.info("--------- Welcome to python mockup ---------");
            var process = processBuilder.start();
            var bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            var bufferedErrorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            var message = "";
            while ((message = bufferedErrorReader.readLine()) != null) {
                logger.info("Python: " + message);
            }
            while ((message = bufferedReader.readLine()) != null) {
                logger.info("Python: " + message);
            }
            logger.info("Python: Exited with code " + process.waitFor());
            bufferedReader.close();
            logger.info("---------- Goodbye python mockup -----------");
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            logger.info("---------- Goodbye python mockup -----------");
        }
    }

    public PythonMockupComponent(
            @Value("${resort.mockup.pythonCommandLineInterpreter:#{null}}") Optional<String> interpreter,
            @Value("${resort.mockup.pythonMockupAbsolutePathToMain:#{null}}") Optional<String> path,
            @Value("${spring.datasource.username}") String postgresUsername,
            @Value("${spring.datasource.password}") String postgresPassword
    ) {
        this.pythonCommandLineInterpreter = interpreter.orElse("");
        this.pythonMockupAbsolutePathToMain = path.orElse("");
        this.postgresPassword = postgresPassword;
        this.postgresUsername = postgresUsername;
    }

    @Async("pythonMockupExecutor")
    public void startPythonProcess() {
        var operatingSystem = checkOS();
        processBuilder = new ProcessBuilder();
        if (pythonCommandLineInterpreter.isBlank() || pythonMockupAbsolutePathToMain.isBlank()) {
            logger.error("Path or interpreter not provided");
            return;
        }
        if (operatingSystem.commandLineRunner.isBlank()) {
            logger.error("Operating system not detected could not execute script");
            return;
        }
        processBuilder.command(operatingSystem.commandLineRunner, "-c", pythonCommandLineInterpreter + " "
                        + pythonMockupAbsolutePathToMain + " "
                        + postgresUsername + " "
                        + postgresPassword);
        this.runtime();
    }
}
