package polsl.tab.skiresort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PythonMockupComponent {

    private static final Logger logger = LoggerFactory.getLogger(PythonMockupComponent.class);

    private static final String MOCKUP_LANGUAGE = "python3";

    private static final String MAIN_PROGRAM_NAME = "main.py";

    private static final String PATH_TO_MAIN_FUNCTION = "~/Tab/2021_BD2_S12_RYNKOWSKA/Spring_Backend/src/main/python/main.py";

    private ProcessBuilder initializeProcessBuilder() {
        return new ProcessBuilder(
                MOCKUP_LANGUAGE,
                PATH_TO_MAIN_FUNCTION
        );
    }

    public Process startPythonProcess(ProcessBuilder processBuilder) throws IOException {
        return processBuilder.start();
    }

    @Async("pythonMockupExecutor")
    public void startPythonProcess() {
        logger.info("Python mockup process started");

    }
}
