/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * Parse the time to string
 * @param {(Object|string|number)} time
 * @param {string} cFormat
 * @returns {string | null}
 */
export function parseTime(time, cFormat) {
  if (arguments.length === 0 || !time) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if ((typeof time === 'string')) {
      if ((/^[0-9]+$/.test(time))) {
        // support "1548221490638"
        time = parseInt(time)
      } else {
        // support safari
        // https://stackoverflow.com/questions/4310953/invalid-date-in-safari
        time = time.replace(new RegExp(/-/gm), '/')
      }
    }

    if ((typeof time === 'number') && (time.toString().length === 10)) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
    const value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') {
      return ['日', '一', '二', '三', '四', '五', '六'][value]
    }
    return value.toString().padStart(2, '0')
  })
  return time_str
}

/**
 * @param {number} time
 * @param {string} option
 * @returns {string}
 */
export function formatTime(time, option) {
  if (('' + time).length === 10) {
    time = parseInt(time) * 1000
  } else {
    time = +time
  }
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return (
      d.getMonth() +
      1 +
      '月' +
      d.getDate() +
      '日' +
      d.getHours() +
      '时' +
      d.getMinutes() +
      '分'
    )
  }
}

/**
 * @param {string} url
 * @returns {Object}
 */
export function param2Obj(url) {
  const search = decodeURIComponent(url.split('?')[1]).replace(/\+/g, ' ')
  if (!search) {
    return {}
  }
  const obj = {}
  const searchArr = search.split('&')
  searchArr.forEach(v => {
    const index = v.indexOf('=')
    if (index !== -1) {
      const name = v.substring(0, index)
      const val = v.substring(index + 1, v.length)
      obj[name] = val
    }
  })
  return obj
}

export function wordsCet4ListParse(wordsCet4List) {
  let wordDetailList = []
  wordsCet4List.forEach(e => {
    let wordDetail = []
    let a = e['baidu']['dict_result']['oxford']['entry'][0]['data']
    a.forEach(e1 => {
      if ((a.length === 1 && e1['tag'] === 'h-g') || (e1['tag'] === 'p-g')) {
        let b = e1['data']
        // 词型
        let wp = {}
        wp['ciyi'] = []
        b.forEach(e2 => {
          // 词型，公有
          if (e2['tag'] === 'p') {
            wp['cixing'] = e2
          }
          // 部分有
          if (e2['tag'] === 'd') {
            wp['ciyi'].push(e2)
            wp['ciyi'][0]['liju'] = []
          }
          if (e2['tag'] === 'x') {
            // 如果不存在词义，就默认构造一个词义
            if (wp['ciyi'].length === 0) {
              wp['ciyi'].push({chText: '---'})
              wp['ciyi'][0]['liju'] = []
            }
            wp['ciyi'][0]['liju'].push(e2)
          }
          // 部分有
          if (e2['tag'] === 'n-g') {
            let c = e2['data']
            // 词义
            let wd = {}
            let wx = []
            wd = {}
            c.forEach(e3 => {
              if (e3['tag'] === 'd') {
                wd = e3
              }
              if (e3['tag'] === 'x') {
                wx.push(e3)
              }
            })
            wd['liju'] = wx
            wp['ciyi'].push(wd)
          }
        })
        wordDetail.push(wp)
      }
    })
    wordDetailList.push(wordDetail)
  })
  return wordDetailList
}

export function FixedQueue(size) {
  let list = [];
  let len = 0;
  this.push = (data) => {
    list.push(data)
    len++
    if (len > size) {
      list.shift()
      len--
    }
  }
  this.array = () => {
    return list
  }
  this.refresh = () => {
    list = []
    len = 0;
  }
}
