# RetrofitRequest
### 基于Retrofit和Rxjava封装的请求工具类

                //此Demo 使用的是聚合网的api接口
                // 聚合网申请的key
                String key = "*************************";
                RxHttpParams params = new RxHttpParams.Build()
                        .url("http://v.juhe.cn/wepiao/query")
                        .addQuery("key", key)
                        .build();
                //网络请求方式 默认为POST
                params.setMethod(RxHttpParams.HttpMethod.GET);
                RetrofitUtil.request(params, String.class, new RetrofitUtil.HttpCallBackImpl<String>() {
                    @Override
                    public void onCompleted(String contactsBean) {
                        tvStr.setText(contactsBean);
                    }
                });