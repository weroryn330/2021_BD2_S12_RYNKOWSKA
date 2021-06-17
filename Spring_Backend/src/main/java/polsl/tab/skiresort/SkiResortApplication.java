package polsl.tab.skiresort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SkiResortApplication implements CommandLineRunner {

    private static final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    private static final Logger logger = LoggerFactory.getLogger(SkiResortApplication.class);

    private final PythonMockupComponent pythonMockupComponent;

    private final Boolean pythonMockupRun;

    public SkiResortApplication(
            @Value("${resort.python.mockup.run}") Boolean pythonMockupRun,
            PythonMockupComponent pythonMockupComponent
    ) {
        this.pythonMockupComponent = pythonMockupComponent;
        this.pythonMockupRun = pythonMockupRun;
    }

    public static void main(String[] args) {
        SpringApplication.run(SkiResortApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (Boolean.TRUE.equals(pythonMockupRun)) {
            logger.info("Starting python mockup process");
            pythonMockupComponent.startPythonProcess();
        }
    }

    @Bean("pythonMockupExecutor")
    private static TaskExecutor pythonMockup() {
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Python_Mock - ");
        return executor;
    }
}
