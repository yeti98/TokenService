package utils;

import vn.pipeline.Word;

public class AnalyzerUtils {
    private final String ner;
    private final String dep;
    private final int head;
    private final String postag;
    private final int index;
    private final String form;

    public AnalyzerUtils(Word word) {
        this.index = word.getIndex();
        this.form = word.getForm();
        this.postag = word.getPosTag();
        this.ner = word.getNerLabel();
        this.dep = word.getDepLabel();
        this.head = word.getHead();
    }
}
