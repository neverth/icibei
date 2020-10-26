import json
import time

import pymysql
import requests
import js2py
import re
import execjs
import base64


def log(text):
    text = str(text)
    print(text)
    with open('log.log', 'a', encoding='utf-8') as f:
        f.write(text + '\n')


class BaiDuTranslateAPI(object):
    headers = {
        'cookie': 'REALTIME_TRANS_SWITCH=1; FANYI_WORD_SWITCH=1; HISTORY_SWITCH=1; SOUND_SPD_SWITCH=1; SOUND_PREFER_SWITCH=1; PSTM=1602669279; BAIDUID=D8338F424B846873AE86FCB8A0E2C009:FG=1; BIDUPSID=7B1DC8DCA52F7C79EB5E10163F01F0F6; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1602296904,1602469789,1602642448,1602831644; delPer=0; PSINO=3; BA_HECTOR=20aka1842l0h25bc221foir600l; H_PS_PSSID=7506_1438_7567_31254_32705_32230_7516_7605_26350; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1602843003; __yjsv5_shitong=1.0_7_7e119efd869d25ffecb7630b49307dca632a_300_1602843003429_112.65.125.218_bc7df63a; yjs_js_security_passport=eb7ec8e61c248176817684b92f635aae9187e9f8_1602843069_js'
    }

    def __init__(self):
        super(BaiDuTranslateAPI).__init__()
        self.GetReady()

    def GetReady(self):
        url_index = 'https://www.baidu.com'
        self.session = requests.session()
        self.session.get(url=url_index, headers=self.headers)
        self.headers['Referer'] = url_index
        url_html = 'https://fanyi.baidu.com/translate?aldtype=16047&query=&keyfrom=baidu&smartresult=dict&lang=auto2zh'
        html = self.session.get(url=url_html, headers=self.headers)
        comm = re.compile('token: \'(\w+)\'')
        self.token = comm.search(html.text).group(1)
        self.headers['Referer'] = url_html

    def Get_Js(self):
        with open('./test.js', 'r', encoding='utf-8') as f:
            return f.read()

    def Translate(self, file):
        files = False
        file = str(file)
        for i in file:
            if '\u4e00' <= i.encode().decode('utf-8') <= '\u9fff':
                files = True
            else:
                files = False
            if files == False:
                break
        return files

    def BaiDu(self, file):
        sign = execjs.compile(self.Get_Js()).call("e", file)
        url_api = 'https://fanyi.baidu.com/v2transapi'
        is_it = self.Translate(file)
        if is_it:
            iia = 'zh'
            iib = 'en'
        else:
            iia = 'en'
            iib = 'zh'
        data = {
            'from': iia,
            'to': iib,
            'query': file,
            'transtype': 'realtime',
            'simple_means_flag': '3',
            'sign': sign,
            'token': self.token
        }
        html = self.session.post(url=url_api, headers=self.headers, data=data).json()
        return (html)

    def Start(self, file='i'):
        try:
            int(file)
            return file
        except:
            # self.zhunbei()
            files = self.BaiDu(file)
            return files


baidu = BaiDuTranslateAPI()


def run(word):
    try:
        ooo = baidu.Start(word)
        del ooo['liju_result']
        log(word + "-" + str(ooo)[0:100])
        return ooo
    except Exception:
        log("发生异常" + word)
        return '-1'


if __name__ == '__main__':
    baidu = BaiDuTranslateAPI()

    resultList = []

    conn = pymysql.connect(
        host='106.14.44.108',
        user='root',
        password='ly19980911',
        db='sc_admin',
        charset='utf8',
    )

    conn1 = pymysql.connect(
        host='106.14.44.108',
        user='root',
        password='ly19980911',
        db='sc_admin',
        charset='utf8',
    )

    cur1 = conn1.cursor()

    cur = conn.cursor()
    sql = "SELECT word FROM words_cet4"
    sqlRes = cur.execute(sql)
    dbWordList = cur.fetchall()
    conn.close()

    index = 0
    index1 = 0
    next = False
    for word in dbWordList:

        if word[0] == 'commonly':
            next = True

        if next == False:
            continue

        resStr = run(word[0])
        while resStr == '-1':
            time.sleep(30)
            resStr = run(word[0])

        result = dict()
        result['word'] = word[0]
        result['ex'] = resStr
        resultList.append(result)
        index += 1

        sql1 = "INSERT into word_detail (word, baidu) VALUES (%s, %s)"
        try :
            cur1.execute(sql1, (str(result['word']), str(result['ex'])))
            conn1.commit()
        except Exception:
            conn1.rollback()
            encodeStr = ''
            bb = base64.b64encode(str(result['ex']).encode('utf-8'))
            bb = str(bb)
            cur1.execute(sql1, (str(result['word']), '#base64#' + bb))
            conn1.commit()

        if index == 50:
            with open('word-{}.json'.format(index), 'w', encoding='utf-8') as f:
                index1 += 1
                log('写入到文件' + 'word-{}.json'.format(index1))
                f.write(json.dumps(resultList))
            index = 0
        time.sleep(10)

    conn1.close()

    log()
