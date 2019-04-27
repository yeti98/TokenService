package TextClassifier;

import Tokenizer.Tokenizer;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args ) {
        Tokenizer tokenizer = new Tokenizer();
        Gson gson = new Gson();

        post("/tokenizer", (req, res) -> {
            String data = req.body();
//            System.out.println(data);
            List<String> tokenList = tokenizer.tokenizedList(data);
            return gson.toJson(tokenList);
        });
        post("/analyzer", (req, res) ->{
            String data = req.body();
            return gson.toJson(tokenizer.analyze(data));
        });
    }
}
