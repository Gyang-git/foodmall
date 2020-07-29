~~~java
异常一：
org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: type is missing;
//局部代码
 @Override
    public boolean foodStatusUp(SkuEsModel skuEsModel) throws IOException {
        //将数据保存到es
        BulkRequest bulkRequest = new BulkRequest();
        String s = JSON.toJSONString(skuEsModel);
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOOD_INDEX);
        if(skuEsModel.getIsSingle() == 1){
            indexRequest.id(skuEsModel.getSingleId().toString());
        }else {
            indexRequest.id(skuEsModel.getSetmealId().toString());
        }

        indexRequest.source(s,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
    }

~~~

~~~xml
<!--es使用高级client需额外依赖于其他jar包-->
<!--elasticsearch-->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.4.2</version>
</dependency>
<!--使用高级client需导入以下两个jar包-->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>7.4.2</version>
</dependency>
<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>7.4.2</version>
</dependency>
~~~

~~~java
异常二：
java.lang.IllegalArgumentException: The number of object passed must be even but was [1]
	at org.elasticsearch.action.index.IndexRequest.source(IndexRequest.java:358) ~[elasticsearch-6.4.3.jar:6.4.3]
	at org.elasticsearch.action.index.IndexRequest.source(IndexRequest.java:345) ~[elasticsearch-6.4.3.jar:6.4.3]
//局部代码
@Override
    public boolean foodStatusUp(SkuEsModel skuEsModel) throws IOException {
        //将数据保存到es
        BulkRequest bulkRequest = new BulkRequest();
        String s = JSON.toJSONString(skuEsModel);
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOOD_INDEX);
        if(skuEsModel.getIsSingle() == 1){
            indexRequest.id(skuEsModel.getSingleId().toString());
        }else {
            indexRequest.id(skuEsModel.getSetmealId().toString());
        }

        indexRequest.source(s);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
}
//解决
@Override
    public boolean foodStatusUp(SkuEsModel skuEsModel) throws IOException {
        //将数据保存到es
        BulkRequest bulkRequest = new BulkRequest();
        String s = JSON.toJSONString(skuEsModel);
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOOD_INDEX);
        if(skuEsModel.getIsSingle() == 1){
            indexRequest.id(skuEsModel.getSingleId().toString());
        }else {
            indexRequest.id(skuEsModel.getSetmealId().toString());
        }

        indexRequest.source(s,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
}
//总结
esindex时发送的source为key-value形式 因此需将数据转为map或json格式
~~~