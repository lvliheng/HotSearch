package com.lvvi.hotsearch.utils

object API {


    const val HOT_SEARCH_WEIBO = "https://m.weibo.cn/api/container/getIndex?jumpfrom=weibocom&" +
            "containerid=106003type%3D25%26t%3D3%26disable_hot%3D1%26filter_type%3Drealtimehot"

    const val WEIBO_DETAIL = "https://m.weibo.cn/api/container/getIndex?"
    const val WEIBO_DETAIL_PARAMS = "&page_type=searchall"

    const val WEIBO_MEDIA_INFO = "https://m.weibo.cn/statuses/show?id="

    //https://card.weibo.com/article/m/aj/detail?id=2309354511396774674485&_t=1591147601949
    const val WEIBO_ARTICLE_INFO = "https://card.weibo.com/article/m/aj/detail?id="


    const val HOT_SEARCH_BAIDU = "http://top.baidu.com/mobile_v2/buzz?b=1&c=515"
    const val BAIDU_SCHEME = "http://m.baidu.com/s?cl=3&fr=top1000&from=1011885a&sa=fyb_top&word="

    const val HOT_SEARCH_DOUYIN = "https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/aweme/"

    const val HOT_SEARCH_V2EX = "https://www.v2ex.com/api/topics/hot.json"

    const val V2EX_TOPIC = "https://www.v2ex.com/api/topics/show.json?id="

    const val V2EX_REPLIES = "https://www.v2ex.com/api/replies/show.json?topic_id="



}
