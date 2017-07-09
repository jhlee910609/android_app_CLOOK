# HandlingWeatherApi

### 1. SK Planet Weather API (이하 planet api)

- Firebase 기반 프로젝트 진행 전, 기상 API parsing 연습을 하였다.
- 기상청, Open Weather 등 다양한 기상 관련 Open API가 있지만 SK Planet에서 제공하는 기상 API를 사용하기로 결정했다.
- Network Lib를 사용하지 않고, 공부를 위해 `HttpUrlConnection(defaul class)`를 사용했다. 
- [SK Planet weather API](https://developers.skplanetx.com/develop/self-console/)



### 2. Parsing 과정

##### 1.1. Network 통신을 통해 필요한 데이터가 무엇인지 정의한다.

- 해당 프로젝트에서는 실시간 날씨 데이터(현재, D+7 주간 예보)이다.



##### 1.2. API가 있다면 받는 데이터 형태(주로 JSON, XML..)가 무엇인지 찾는다.

- planet API는 JSON 형태로 데이터를 전송해준다. 



##### 1.3. 해당 데이터의 형태를 분석한다.

- 기상정보가 굉장히 다양하기 때문에 JSON 데이터 구조가 상당히 복잡하다. 초기에 JSON 구조대로 class를 설계했는데 무수히 많은 inner class가 생성됐다. 우선… 가독성이 떨어지고 내가 헷갈려서 모두 삭제해버렸다.
- 이런 상황을 위해 두 가지 사이트를 미리 검색해놓았다.
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



##### 1.4. 모델링한 데이터를 기반으로 API 파싱에 돌입한다.

- `Retrofit2`, `Okhttp` Lib 를 이용하면 조금 더 손쉽게 데이터를 파싱할 수 있다.
- 하지만 이번 프로젝트에서는 공부를 위해 JAVA 내장 class인 `HttpUrlConnection`을 사용했다.


- Network 통신(Protocol : REST)을 통해 API 정보를 Parsing 한다.
- Log 찍기와 Error code 분석은 꼭 해준다.
  - 당연한 이야기지만 Error code만 잘 분석해도 금방 문제를 해결할 수 있다.
  - 이를 위해 개발자 문서는 꼭 꼼꼼히 읽어보자.



##### 1.5. 원하는 데이터를 받아, 하고 싶은 작업을 진행한다.

- 데이터를 확인하기 위해 받아온 데이터를 `TextView`에 `.setText();` 하였다. 



