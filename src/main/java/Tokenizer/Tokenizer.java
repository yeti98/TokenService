package Tokenizer;

import utils.AnalyzerUtils;
import vn.pipeline.Annotation;
import vn.pipeline.VnCoreNLP;
import vn.pipeline.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private VnCoreNLP pipeline;

    public Tokenizer(){
        String[] annotators = {"wseg", "pos", "ner", "parse"};
//        String[] annotators = {"wseg"};
        try {
            pipeline = new VnCoreNLP(annotators);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> tokenizedList(String document) {
        System.out.println("Tokening data...");
        Annotation annotation = new Annotation(document);
        try {
            pipeline.annotate(annotation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> tokenizedList = new ArrayList<>();

        for (Word w : annotation.getWords()) {
            tokenizedList.add(w.getForm());
//            System.out.println(w);
        }
        return tokenizedList;
    }

    public List<AnalyzerUtils> analyze(String document) {
        System.out.println("Analyzing data...");
        Annotation annotation = new Annotation(document);
        try {
            pipeline.annotate(annotation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<AnalyzerUtils> list = new ArrayList<>();
        for(Word w : annotation.getWords()){
            list.add(new AnalyzerUtils(w));
        }
        return list;
    }

}
