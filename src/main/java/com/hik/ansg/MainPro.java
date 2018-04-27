package com.hik.ansg;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainPro {
    public static void main(String[] args)  throws Exception{
        String content=null;
        File file=new File( new MainPro().getClass().getClassLoader().getResource("abc").getFile());

        content=FileUtils.readFileToString(file,"utf-8");

        System.out.println(content);

        /**
         *
         */
//        System.out.println(ToAnalysis.parse(content));

        /**
         *
         */
        KeyWordComputer keyWordComputer=new KeyWordComputer(5);
        List<Keyword> keywordList=keyWordComputer.computeArticleTfidf(content);
        System.out.println(keywordList);
    }
}
