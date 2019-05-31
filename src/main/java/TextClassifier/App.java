package TextClassifier;

import Tokenizer.Tokenizer;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);
    private static final String PORT_KEY = "-p";
    private static final String THREAD_POOL_KEY = "-t";
    private static int PORT = 4567;
    private static int WORKER = 8;

    public static void main(String[] args) {
        try {
            for (int i = 0; i < args.length; i += 2) {
                String key = args[i];
                String value = args[i + 1];
                switch (key) {
                    case PORT_KEY:
                        PORT = Integer.valueOf(value);
                        break;
                    case THREAD_POOL_KEY:
                        WORKER = Math.max(WORKER, Integer.valueOf(value));
                        break;
                }
            }
        } catch (Exception e) {
            logger.error("Port and Thread pool size must be an integer");
            System.exit(-1);
        }
        System.out.println("Starting service at port: " + PORT);
        port(PORT);
        threadPool(WORKER);

        Tokenizer tokenizer = new Tokenizer();
        Gson gson = new Gson();
        post("/tokenizer", (req, res) -> {
            String data = req.body();
//            System.out.println(data);
            List<String> tokenList = tokenizer.tokenizedList(data);
            return gson.toJson(tokenList);
        });
        post("/analyzer", (req, res) -> {
            String data = req.body();
            return gson.toJson(tokenizer.analyze(data));
        });
    }
}
