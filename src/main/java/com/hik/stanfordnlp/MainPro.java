package com.hik.stanfordnlp;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class MainPro {
    public static void main(String[] args) {

        /**
         * 创建一个StanfordCoreNLP object
         * tokenize(分词)、ssplit(断句)、 pos(词性标注)、lemma(词形还原)、
         * ner(命名实体识别)、parse(语法解析)、指代消解？同义词分辨？
         */

        Properties properties = new Properties();
        //        七种Annotator
        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

        StanfordCoreNLP pipline = new StanfordCoreNLP(properties);

        String text = "this is text";

        Annotation annotation = new Annotation(text);
        pipline.annotate(annotation);

        List<CoreMap> coreMapList = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        System.out.println("word\tpos\tlemma\tner");
        for (CoreMap coreMap : coreMapList) {
            for (CoreLabel coreLabel : coreMap.get(CoreAnnotations.TokensAnnotation.class)) {

//                分词
                String word = coreLabel.get(CoreAnnotations.TextAnnotation.class);
//                词性标注
                String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//                命名实体识别结果
                String ne = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//                获取词形还原结果
                String lemma = coreLabel.get(CoreAnnotations.LemmaAnnotation.class);
                System.out.println(word + "\t" + pos + "\t" + lemma + "\t" + ne);
            }

            // 获取parse tree
            Tree tree = coreMap.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println(tree.toString());

            // 获取dependency graph

            SemanticGraph semanticGraph = coreMap.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            System.out.println(semanticGraph);

        }
    }
}
