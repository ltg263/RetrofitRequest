# RetrofitRequest
### 基于Retrofit和Rxjava封装的请求工具类

#### 请求的方法
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

 #### 请求的回掉
                    //完成、成功、失败 返回不同的方法
                  public abstract static class HttpCallBackImpl<T> {
                      /**
                       * 请求返回的对象
                       * @param t：对象
                       */
                      public abstract void onCompleted(T t);

                      /**
                       * 请求完成
                       */
                      public void onFinish() {
                      }
                      /**
                       *  请求失败
                       * @param message
                       */
                      public void onError(String message) {
                      }
                  }

