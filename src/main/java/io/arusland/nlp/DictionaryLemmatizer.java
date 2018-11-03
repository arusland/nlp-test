package io.arusland.nlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.*;
import java.util.Arrays;

/**
 * Dictionary Lemmatizer Example in Apache OpenNLP
 * <p>
 * Download https://raw.githubusercontent.com/richardwilly98/elasticsearch-opennlp-auto-tagging/master/src/main/resources/models/en-lemmatizer.dict
 * Download en-pos-maxent.bin from http://opennlp.sourceforge.net/models-1.5/
 */
public class DictionaryLemmatizer {

    public static void main(String[] args) {
        try {
            // test sentence
            /*String[] tokens = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
                    "morning", "and", "afternoon", "newspapers", ".", "She", "is", "naked"};*/

            String[] tokens = new String[]{"morning"};

            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream
            InputStream posModelIn = new FileInputStream("models" + File.separator + "en-pos-maxent.bin");
            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);

            // loading the dictionary to input stream
            InputStream dictLemmatizer = new FileInputStream("dict" + File.separator + "en-lemmatizer.dict");
            // loading the lemmatizer with dictionary
            opennlp.tools.lemmatizer.DictionaryLemmatizer lemmatizer = new opennlp.tools.lemmatizer.DictionaryLemmatizer(dictLemmatizer);

            System.out.println(Arrays.asList(tags));

            System.out.println();

            // finding the lemmas
            String[] lemmas = lemmatizer.lemmatize(tokens, tags);

            // printing the results
            System.out.println("\nPrinting lemmas for the given sentence...");
            System.out.println("WORD -POSTAG : LEMMA");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + " -" + tags[i] + " : " + lemmas[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 