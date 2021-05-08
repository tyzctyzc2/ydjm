# ydjm
 ydjm
##PostController
###Create
####/api/post/create
####POST
{
"title": "软糖二五",
"content": "今天的框架也\n\r    是诺丁",
"tags": [{"tagId":0, "tagName":"中文"}, {"tagId":0, "tagName":"确定"}],
"files": [{"fileType":"txt", "fileBody":"g=="}]
}

###List all
####/api/post/list?page=0
####GET
[{
"postId": 44,
"title": "ZXC",
"content": "我是",
"createTime": "2021-04-21T15:02:18",
"tags": ["日常秀"],
"files": [{
"fileType": "jpg",
"fileName": "20210421_100.jpg",
"filePath": "202104",
"fileBody": null
}, {
"fileType": "jpg",
"fileName": "20210421_101.jpg",
"filePath": "202104",
"fileBody": null
}],
"path": "202104"
}, {
"postId": 43,
"title": "我也是",
"content": "终于把\n庆祝一下",
"createTime": "2021-04-20T19:51:51",
"tags": ["饮品"],
"files": [{
"fileType": "jpg",
"fileName": "20210420_97.jpg",
"filePath": "202104",
"fileBody": null
}, {
"fileType": "jpg",
"fileName": "20210420_98.jpg",
"filePath": "202104",
"fileBody": null
}, {
"fileType": "jpg",
"fileName": "20210420_99.jpg",
"filePath": "202104",
"fileBody": null
}],
"path": "202104"
}]
###Find Text
####api/post/list/find?keyword=羊蝎子&page=0
####GET
同上
###Find Tag
####api/post/list/tag?tag=北京&page=0
####GET
同上

##TagController
###List Tag
####api/tag/list
####GET
[{
"tagId": 1,
"tagName": "颐和园"
}, {
"tagId": 2,
"tagName": "香山"
}]
###Create Tag
####api/tag/create
####POST
{
"tagId":0,
"tagName":"灌水测试"
}
###Update Post Tag