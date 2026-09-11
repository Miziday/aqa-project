package utils;

import io.qameta.allure.Allure;
import org.selenide.videorecorder.core.RecordedVideos;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class AllureVideoAttachListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Optional<Path> video = RecordedVideos.getRecordedVideo(Thread.currentThread().getId());
        video.ifPresent(this::attachVideo);
    }

    private void attachVideo(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}