# RetrofitRequest
### 基于Retrofit和Rxjava封装的请求工具类
RxHttpParams params = new RxHttpParams.Build()
                        .url("http://v.juhe.cn/wepiao/query")
                        .addQuery("key", "b9028c6563dcb2cf68efeac77395341a")
                        .build();
                //网络请求方式 默认为POST
                params.setMethod(RxHttpParams.HttpMethod.GET);
                RetrofitUtil.request(params, String.class, new RetrofitUtil.HttpCallBackImpl<String>() {
                    @Override
                    public void onCompleted(String contactsBean) {
                        tvStr.setText(contactsBean);
                    }
                });