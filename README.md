# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


## Todo

- 모든 Request Header 를 (log)출력한다.
  - BufferedReader.readLine() 메소드 활용해 라인별로 http header 를 읽는다.
  - 읽어온 데이터를 log로 출력한다.
    - 마지막 메서드인지 확인은 `while (!"".equals(line)) {}`를 이용한다.
    - line에 대한 null check 또한 수행하여 위 코드가 무한루프에 빠지지 않도록 한다.
  - request header에서 첫 번째 라인을 추출한다.
    - 첫 번째 라인을 받아와 `String[] tokens = line.split(" ");`를 활용해 문자열을 분리한다.
    - 이 과정으로 만들어낸 path를 

string 이 들어왔을 떄 나눠서 분리하는 것에 대한 테스트
http 8080 index... 로 get 요청이 들어왔을 때 헤더 형식에 읽는 것에 대한 테스트
-> 헤더 뭉치를 적절하게 서버에서 파싱할 수 있는지에 대한 테스트