PolygonImageView 
=======
    
    正多边形边框的自定义ImageView   
    自定义属性：边数edge_num


# 用法：
静态设置：
---

根Layout中加入命名空间custom：

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        ...>

在自定义的PolygonImageView中，用命名空间custom来访问edge_num属性：

    <com.example.i_jinliangshan.polygonimageview.PolygonImageView
        ....
        custom:edge_num="6"
        />
        

动态设置：
---
    PolygonImageView piv = (PolygonImageView) findViewById(R.id.piv);
    piv.getEdgeNum();
    piv.setEdgeNum(6);
    
    

