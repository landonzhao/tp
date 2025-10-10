package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Tests for HelpWindow.
 * - Ensures docs/UserGuide.html is available on the classpath.
 * - Ensures constructing HelpWindow on the FX thread succeeds and loads the HTML.
 */
public class HelpWindowTest {

    @BeforeAll
    static void initFx() throws Exception {
        // Start JavaFX toolkit once for all tests (safe if already started)
        try {
            Platform.startup(() -> { /* no-op */ });
        } catch (IllegalStateException alreadyStarted) {
            // Toolkit already initialized in other tests
        }
    }

    @Test
    void userGuideHtml_isOnClasspath() {
        URL url = HelpWindow.class.getResource("/docs/UserGuide.html");
        assertNotNull(url, "docs/UserGuide.html should be present on classpath under src/main/resources/docs");
    }

    @Test
    void constructor_loadsLocalHelpPage() throws Exception {
        // Create HelpWindow on the JavaFX Application Thread and capture it
        CompletableFuture<HelpWindow> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                future.complete(new HelpWindow());
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        HelpWindow helpWindow = future.get(5, TimeUnit.SECONDS);
        assertNotNull(helpWindow, "HelpWindow should be constructed without exceptions");

        // Reflect the private WebView field to check what was loaded
        Field webViewField = HelpWindow.class.getDeclaredField("helpWebView");
        webViewField.setAccessible(true);
        WebView webView = (WebView) webViewField.get(helpWindow);
        assertNotNull(webView, "HelpWindow should have a WebView with fx:id=helpWebView");

        // The WebEngine location should contain the HTML path after loadHelpPage()
        WebEngine engine = webView.getEngine();

        // Allow a brief tick for the WebView to resolve local URL (usually immediate for classpath resources)
        // If the CI is very strict, spin-wait a few times.
        String location = engine.getLocation();
        assertNotNull(location, "WebEngine should have a non-null location after loadHelpPage()");
        assertTrue(location.contains("/docs/UserGuide.html") || location.contains("docs/UserGuide.html"),
                "WebEngine should load the local docs/UserGuide.html (was: " + location + ")");
    }
}

