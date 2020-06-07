#### 问题记录
1. 国际化时，中文乱码，原因为文件编码格式，按如下方式修改IDEA配置文件即可
    ```
   1.首先我们的IDEA文件编码一般都修改为utf-8(setting-->file encodings--->Global Encoding 和 Project Encoding 都设置为UTF-8)
       
   2.对于 Properties 文件，重要属性 Transparent native-to-ascii conversion 主要用于转换 ascii，一般都要勾选，不然 Properties 文件中的注释显示的都不会是中文。
    ```
1. 