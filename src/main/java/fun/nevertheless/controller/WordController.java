package fun.nevertheless.controller;


import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.po.WordCet4;
import fun.nevertheless.dao.WordCet4Dao;
import fun.nevertheless.service.WordCet4Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WordController {
    @Autowired
    WordCet4Service wordCet4Service;


    @RequestMapping(value = "/updateWordById/{wordId}", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleupdateWordByIdApiRequest(@PathVariable("wordId") Integer wordId,
                                              HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String word =  httpServletRequest.getParameter("word");
        String phonetic =  httpServletRequest.getParameter("phonetic");
        String definition =  httpServletRequest.getParameter("definition");
        String translation =  httpServletRequest.getParameter("translation");
        String pos =  httpServletRequest.getParameter("pos");
        String collins =  httpServletRequest.getParameter("collins");
        String tag =  httpServletRequest.getParameter("tag");
        String bnc =  httpServletRequest.getParameter("bnc");
        String frq =  httpServletRequest.getParameter("frq");
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setId(wordId);
        wordCet4.setWord(word);
        wordCet4.setPhonetic(phonetic);
        wordCet4.setDefinition(definition);
        wordCet4.setTranslation(translation);
        wordCet4.setPos(pos);
        wordCet4.setCollins(collins);
        wordCet4.setTag(tag);
        wordCet4.setBnc(Integer.parseInt(bnc) );
        wordCet4.setFrq(Integer.parseInt(frq));

        int result = wordCet4Service.updateWordById(wordCet4);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/insertWord", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleInsertWordApiRequest(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String word =  httpServletRequest.getParameter("word");
        String phonetic =  httpServletRequest.getParameter("phonetic");
        String definition =  httpServletRequest.getParameter("definition");
        String translation =  httpServletRequest.getParameter("translation");
        String pos =  httpServletRequest.getParameter("pos");
        String collins =  httpServletRequest.getParameter("collins");
        String tag =  httpServletRequest.getParameter("tag");
        String bnc =  httpServletRequest.getParameter("bnc");
        String frq =  httpServletRequest.getParameter("frq");
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setWord(word);
        wordCet4.setPhonetic(phonetic);
        wordCet4.setDefinition(definition);
        wordCet4.setTranslation(translation);
        wordCet4.setPos(pos);
        wordCet4.setCollins(collins);
        wordCet4.setTag(tag);
        wordCet4.setBnc(Integer.parseInt(bnc) );
        wordCet4.setFrq(Integer.parseInt(frq));
        int result = wordCet4Service.insertCet4Word(wordCet4);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject.toString();
    }
    @RequestMapping(value = "/selectWordByWord", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleSelectWordByWordApiRequest(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String word =  httpServletRequest.getParameter("word");
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setWord(word);
        JSONObject jsonObject = new JSONObject();
        jsonObject = wordCet4Service.selectCet4WordByWord(wordCet4);
        return jsonObject.toString();
    }
}
