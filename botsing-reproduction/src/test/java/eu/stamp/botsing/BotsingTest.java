package eu.stamp.botsing;

import org.evosuite.result.TestGenerationResult;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains integration test cases for botsing. Each test method runs Botsing against one of the exmaple of crashes in the module <b>botsing-example</b>
 */
public class BotsingTest {

    @Test
    public void testFractionCrash() {
        Botsing botsing = new Botsing();

        // setup of the directory with file *class and file *.log
        String user_dir = System.getProperty("user.dir"); // the current directory is the module <b>botsing-reproduction</b>
        File file = new File(user_dir);
        String base_dir = Paths.get(file.getParent(), "botsing-examples").toString(); // the crash to replicate is inside the module <b>botsing-examples</b>

        //run Botsing
        String[] prop = {
                "-crash_log",
                Paths.get(base_dir, "src","main","resources","Fraction.log").toString(),
                "-target_frame",
                ""+1,
                "-projectCP",
                Paths.get(base_dir, "target","classes").toString() + System.getProperty("path.separator"),
        };
        List<TestGenerationResult> results = botsing.parseCommandLine(prop);
        assertTrue(results.size() > 0);
        assertEquals(TestGenerationResult.Status.SUCCESS, results.get(0).getTestGenerationStatus());
    }
}