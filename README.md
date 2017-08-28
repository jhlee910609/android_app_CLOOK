# 일기예보 기반 패션 정보 APP - CLOOK

![](https://ws4.sinaimg.cn/large/006tKfTcgy1fin4w4a0w0j30b408gwen.jpg)

> Open API 파싱 연습 및 Fragment control 연습을 위해 날씨 앱을 개발해보기로 했다.
> 약간의 차별성을 갖기 위해 '패션'이라는 소재를 가미했다.

- [CLOOK 플로우 차트 보기](https://www.slideshare.net/secret/5aLvyGDSVFLnxL)
- [CLOOK 구현 영상보기](https://www.youtube.com/watch?v=N0Khy7a2ZZQ)




### 1. CLOOK 화면

![](https://ws1.sinaimg.cn/large/006tNc79gy1fizukandqrj31hc0u0gs4.jpg)
![](https://ws1.sinaimg.cn/large/006tNc79gy1fizuk9iijsj31hc0u0n3x.jpg)
![](https://ws3.sinaimg.cn/large/006tNc79gy1fizuk7vtdij31hc0u0gtf.jpg)
![](https://ws3.sinaimg.cn/large/006tNc79gy1fizuk6nnylj31hc0u07bt.jpg)
![](https://ws3.sinaimg.cn/large/006tNc79gy1fizuk4udivj31hc0u0nfb.jpg)

### 1. 처음해보는 일기예보 API Parsing

##### 1.1. API 준비하기 (SK Planet weather API, 기상청 API)

- Firebase 기반 프로젝트 진행 전, 기상 API를 알아보았다.
- 기상청, Open Weather(세계 기상), Yahoo 등 다양한 기상 관련 Open API가 있지만 [SK Planet weatherI](https://developers.skplanetx.com/develop/self-console/)에서 제공하는 기상 API를 사용하기로 결정했다.
- Network Lib를 사용하지 않고, 공부를 위해 `HttpUrlConnection(Java defaul class)`를 사용했다. 





### 2. Parsing 과정

###### 2.1. Network 통신을 통해 필요한 데이터가 무엇인지 정의한다.

- 해당 프로젝트에서는 실시간 날씨 데이터(현재, D+7 주간 예보)이다.




###### 2.2. API가 있다면 받는 데이터 형태(주로 JSON, XML..)가 무엇인지 찾는다.

- planet API는 JSON 형태로 데이터를 전송해준다. 





###### 2.3. 해당 데이터의 형태를 분석한다.

- 기상정보가 굉장히 다양하기 때문에 JSON 데이터 구조가 상당히 복잡하다.
- 이런 상황을 위해 두 가지 사이트를 구비해두었다.
  - [Code Beautify](https://codebeautify.org/) : 직관적으로 JSON 구조를 보여준다.
  - [Convert JSON to JAVA](http://pojo.sodhanalibrary.com/) : JSON 구조를 기반으로 Class 설계를 쉽게 해준다.

> planet api의 JSON 구조는 아래와 같다. 

```javascript
{
    "result":{
        "message":"성공",
        "code":9200,
        "requestUrl":"/weather/current/hourly?lon=&village=도곡동&county=강남구&lat=&city=서울&version=1"
    },
    "common":{
        "alertYn":"Y",
        "stormYn":"N"
    },
    "weather":{
        "hourly":[
            {
                "grid":{
                    "latitude":"37.4870600000",
                    "longitude":"127.0460400000",
                    "city":"서울",
                    "county":"강남구",
                    "village":"도곡동"
                },
                "wind":{
                    "wdir":"266.00",
                    "wspd":"3.20"
                },
                "precipitation":{
                    "type":"0",
                    "sinceOntime":"0.00"
                },
                "sky":{
                    "name":"맑음",
                    "code":"SKY_O01"
                },
                "temperature":{
                    "tc":"6.80",
                    "tmax":"8.10",
                    "tmin":"-0.90"
                },
                "humidity":"31.00",
                "lightning":"0",
                "timeRelease":"2013-11-11 14:00:00"
            }
        ]
    }
}
```



> 기상청 API 구조는 아래와 같다.

```java
{
	"response": {
		"header": {
			"resultCode": "0000",
			"resultMsg": "OK"
		},
		"body": {
			"items": {
				"item": [
					{
						"baseDate": 20170716,
						"baseTime": "0500",
						"category": "POP",
						"fcstDate": 20170716,
						"fcstTime": "0900",
						"fcstValue": 90,
						"nx": 61,
						"ny": 125
					},
					{
						"baseDate": 20170716,
						"baseTime": "0500",
						"category": "PTY",
						"fcstDate": 20170716,
						"fcstTime": "0900",
						"fcstValue": 1,
						"nx": 61,
						"ny": 125
					},
					{
						"baseDate": 20170716,
						"baseTime": "0500",
						"category": "REH",
						"fcstDate": 20170716,
						"fcstTime": "0900",
						"fcstValue": 90,
						"nx": 61,
						"ny": 125
					},
                  ]
			},
			"numOfRows": 10,
			"pageNo": 1,
			"totalCount": 225
		}
	}
}
```

- 기상청 API 데이터를 분석해보니 가져온 데이터를 저장할 때, `fcstTime`을 기준으로 정리하여 데이터를 넣고 싶었다.
- 그래서 `Weather3hr` 라는 클래스에`Map<key, Map<key, Item>>()` 형태로 저장하였다.

```java
public class Weather3hr {

    private static Weather3hr instance;
    public static Weather3hr getInstance() {
        if (instance == null) {
            Log.e("===", "create.........................");
            instance = new Weather3hr();
        }
        return instance;
    }

    public static Map<String, Map<String, Item>> weathers3hr = new HashMap<>();

  	// 기상청 api로부터 Weather3hr에 데이터 셋팅할 수 있도록 함수 작성
    public void setDatasFromKma(Item[] items, String fcstDate, String fcstTime) {
        Map<String, Item> categories = new HashMap<>();
        for (Item tempItem : items) {
            if (tempItem.getFcstDate().equals(fcstDate) && 			tempItem.getFcstTime().equals(fcstTime)) {
                categories.put(tempItem.getCategory(), tempItem);
                weathers3hr.put(tempItem.getFcstTime(), categories);
            }
        }
    }

  	// 예보시간과 기상정보의 종류를 key로 하여, 원하는 값을 꺼낼 수 있게 함수 작성
    public String getFcstValue(String fcstTime, String category) {
        return weathers3hr.get(fcstTime).get(category).getFcstValue();
    }
}
```

###### 1.4. 모델링한 데이터를 기반으로 API 파싱에 돌입한다.

- `Retrofit2`, `Okhttp` Lib 를 이용하면 조금 더 손쉽게 데이터를 파싱할 수 있지만 제공하는 API의 자료 형태에 따라 다를 수도 있겠다는 생각이 들었다.
- 이번 프로젝트에서는 공부를 위해 JAVA 내장 class인 `HttpUrlConnection`을 사용했다.


- Network 통신(Protocol : REST)을 통해 API 정보를 Parsing 한다.
- Log 찍기와 Error code 분석은 꼭 해준다.
  - 당연한 이야기지만 Error code만 잘 분석해도 금방 문제를 해결할 수 있다.
  - 이를 위해 개발자 문서는 꼭 꼼꼼히 읽어보자.

  ​


###### 1.5. 원하는 데이터를 받아, 하고 싶은 작업을 진행한다.

- 비동기로 처리되는 Network의 callback을 받기 위해 interface를 구현하여 관련 메소드를 호출하게 작성했다. [[코드보기](https://github.com/jhlee910609/android_app_CLOOK/blob/master/app/src/main/java/com/example/junhee/weatherparse/util/weatherParser/Remote.java)]


- 데이터를 확인하기 위해 받아온 데이터를 `TextView`에 `.setText();` 하였다. 




### 2. ViewPager

- 메인 화면에 사용된 Vertical ViewPager는 `me.kaelaela:verticalviewpager:1.0.0@aar`라이브러리를 통해 구현할 수 있었다.
- 툴바와 함께 사용할 경우, 사용성이 좋지 못한 것 같아 다음에는 Vertical ViewPager를 사용하진 말아야겠다. 




### 3. CustomDialogAdapter and ListFilter

- 지역 변경하는 부분에 있어 CustomDialog 안에 Filter class 를 상속한 ListFilter를 ListView에 달아 실시간 지역 키워드 검색이 가능하도록 만들었다. [[전체 코드 보기](https://github.com/jhlee910609/android_app_CLOOK/blob/master/app/src/main/java/com/example/junhee/weatherparse/adapter/CustomDialogAdapter.java)]
- ​


### 4. Gradle Dependency

- 아래와 같은 라이브러리를 사용했다.

```groovy
dependencies {
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.google.code.gson:gson:2.8.1'
    compile 'com.github.flavienlaurent.discrollview:library:0.0.2@aar'
    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.tsengvn:typekit:1.0.1'
    compile 'me.kaelaela:verticalviewpager:1.0.0@aar'
    compile 'com.github.bumptech.glide:glide:4.0.0-RC1'
    compile 'jp.wasabeef:glide-transformations:2.0.2'
    testCompile 'junit:junit:4.12'
    compile 'com.squareup.retrofit:converter-gson:2.+'
}
```

