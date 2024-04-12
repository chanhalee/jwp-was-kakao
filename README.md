# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Todo

- [] Request 객체 생성
  - [] first line에서 path 와 메서드를 분리하여 보관할 수 있어야 한다.
  - [] Header를 파싱하여 보관할 수 있어야 한다.
  - [] body를 분리하여 보관할 수 있어야 한다.
- [] Response 객체 생성
  - [] Response의 상태 코드와 메시지를 first line으로 보관
  - [] response의 Header 를 보관할 수 있어야 한다.
  - [] response의 body를 보관할 수 있어야한다.
- [] Request의 종류에 따라 Controller 매핑해주는 RequestMapping 객체 생성
- [] 각종 Request에 따라 다양한 비즈니스 로직을 구사하는 Controller 객체 생성
  - [] index.html을 response Body에 담아줄 수 있어야 한다.
  - [] style.css를 response Body에 담아줄 수 있어야 한다.
  - [] User 생성에 대한 parameter 를 QueryParameter(GET)로 받아 유저를 생성할 수 있다.
  - [] User 생성에 대한 parameter 를 Body(POST)로 받아 유저를 생성할 수 있다.