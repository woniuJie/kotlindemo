package com.example.kotlindemo.textview

/**
 *                 "longitude": 116.506467,
"latitude": 39.874143,
"range": 100,
"location": "北京市朝阳区王四营乡化工路辅路",
"showLocation": 0

 */
data class LocationBean(
    var longitude:String?,
    var latitude:String?,
    var range:String?,
    var location:String?,
    var showLocation:String?
)