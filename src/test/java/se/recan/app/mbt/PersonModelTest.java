package se.recan.app.mbt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.condition.StopConditionException;
//import org.graphwalker.generators.A_StarPathGenerator;
//import org.graphwalker.generators.RandomPathGenerator;
//import org.graphwalker.multipleModels.ModelHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import se.recan.app.Driver;

/**
 * 2013-feb-16
 *
 * Connect the model to a java class, and add it to graphwalker's modelhandler.
 * The model is to be executed using the following criteria:
 * Extended finite state machine is set to true, which means we are using the data domain in the model
        // Generator:      a_star, we want to walk through the model using shortest possible path.
 * @author Anders Recksén (recan)
 */
public class PersonModelTest {
/*
    @ClassRule
    public static Driver classRule = new Driver();
    
    private static final String GRAPH_LOCATION = "/mbt/person.graphml";
    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void a_star() throws InterruptedException, StopConditionException, URISyntaxException, IOException {
        ModelHandler modelhandler = new ModelHandler();

        // Get the model from resources
        File file = stream2file();

        // Stop condition: Edge coverage 100%, we want to walk every edge in the model.
        modelhandler.add("PersonModel", new PersonModel(file, true, new A_StarPathGenerator(new EdgeCoverage(1.0)), false));

        // Start executing the test
        modelhandler.execute("PersonModel");

        // Verify that the execution is complete, fulfilling the criteria from above.
        Assert.assertTrue("Not all models are done", modelhandler.isAllModelsDone());

        //Print the statistics from graphwalker
        String actualResult = modelhandler.getStatistics();
        LOGGER.debug(actualResult);
    }

//    @Test
    public void random() throws InterruptedException, StopConditionException, URISyntaxException {
        ModelHandler modelhandler = new ModelHandler();

        URL url = PersonModel.class.getResource(GRAPH_LOCATION);
        File file = new File(url.toURI());

        modelhandler.add("PersonModel", new PersonModel(file, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
        modelhandler.execute("PersonModel");

        Assert.assertTrue("Not all models are done", modelhandler.isAllModelsDone());

        String actualResult = modelhandler.getStatistics();
        System.out.println(actualResult);
    }
    
    public void success() {
        Vertex start = new Vertex();
        Model model = new Model().addEdge(new Edge()
                .setName("edge1")
                .setGuard(new Guard("isTrue()"))
                .setSourceVertex(start
                        .setName("vertex1"))
                .setTargetVertex(new Vertex()
                        .setName("vertex2"))
                .addAction(new Action("myAction();")));
        this.setModel(model.build());
        this.setPathGenerator(new RandomPath(new VertexCoverage(100)));
        setNextElement(start);
        Machine machine = new SimpleMachine(this);
        while (machine.hasNextStep()) {
            machine.getNextStep();
        }
    }

//    @Test
    public void stability() throws InterruptedException, StopConditionException, URISyntaxException {
        ModelHandler modelhandler = new ModelHandler();

        URL url = PersonModel.class.getResource(GRAPH_LOCATION);
        File file = new File(url.toURI());
        modelhandler.add("PersonModel", new PersonModel(file, true, new RandomPathGenerator(new TimeDuration(10)), false));
        modelhandler.execute("PersonModel");
        Assert.assertTrue("Not all models are done", modelhandler.isAllModelsDone());
        String actualResult = modelhandler.getStatistics();
        System.out.println(actualResult);
    }
    
    private File stream2file () {
        try {
        InputStream in = this.getClass().getResourceAsStream("/mbt/person.graphml");
            
        File tempFile = File.createTempFile("stream2File", "tmp");
        tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(in, out);
        return tempFile;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
            LOGGER.error(ioe);
        }
        return null;
    }
    
    @BeforeClass
    public static void init() {
        File log = new File("logs");
        if(!log.exists()) {
            log.mkdir();
        }
    }
    */
}
