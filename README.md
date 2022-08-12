#网页中使用API获取数据的案例网页：
https://api.wmdb.tv/testapi 可根据代码自行整合API至自己的网站中！

#根据豆瓣id查询数据，doubanid为id，若数据库没有数据则会从维基百科等接口获取数据。doubanUrl/subject/1428581/ 最后数字1428581为doubanid
https://api.wmdb.tv/movie/api?id=doubanid

#自媒体/微博/网页引用分享等专用图片生成接口
https://api.wmdb.tv/movie/api/generateimage?doubanId=1306123&lang=En
doubanId是必填项，lang可选En或者Cn，分别对应英文版和中文版
返回为json：{"image":"https://wmdb.querydata.org/movie/poster/1637210344774-5fa652fb0cea9b0e1eba060a.png","success":1}
image就是生成的分享图片！

#支持id、imdbId、doubanId，至少需要传递其中一个
https://api.wmdb.tv/api/v1/movie?imdbId=tt6264654
https://api.wmdb.tv/api/v1/movie?doubanId=1302425

#全文模糊搜索，根据匹配分数排序
https://api.wmdb.tv/api/v1/movie/search?q=英雄本色&limit=10&skip=0&lang=Cn&year=2002
q为必填，其他为选填，limit和skip用于分页，lang用于返回指定语言的查询数据，支持Cn或者En，year支持限定年份，例如q=英雄&year=2002，则只会返回张艺谋导演、李连杰主演的那一部英雄，可用于各种使用片名+年份精准获取数据的场景！

#TOP250接口
https://api.wmdb.tv/api/v1/top?type=Imdb&skip=0&limit=50&lang=Cn
type为必填，可选Douban或Imdb，skip和limit为分页使用，lang调用指定语言的数据，支持Cn或者En