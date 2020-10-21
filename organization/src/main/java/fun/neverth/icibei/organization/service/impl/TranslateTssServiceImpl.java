package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.neverth.icibei.organization.dao.WordTtsMapper;
import fun.neverth.icibei.organization.entity.po.WordTts;
import fun.neverth.icibei.organization.service.TranslateTssService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author neverth.li
 * @date 2020/10/21 15:50
 */
@Service
public class TranslateTssServiceImpl implements TranslateTssService {

    @Autowired
    private WordTtsMapper wordTtsMapper;

    @Override
    public byte[] get(String word) {
        QueryWrapper<WordTts> query = new QueryWrapper<>();
        query.eq("word", word);
        WordTts wordTts = wordTtsMapper.selectOne(query);

        if (wordTts != null){
            return wordTts.getBaiduGettts();
        }
        HttpResponse<byte[]> response = Unirest.get("https://fanyi.baidu.com/gettts?lan=en&text=" + word + "&spd=3&source=web").asBytes();
        wordTts = new WordTts(word, response.getBody());
        wordTtsMapper.insert(wordTts);
        return response.getBody();
    }
}
