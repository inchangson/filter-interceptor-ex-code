# filter-interceptor-ex-code
- filter, interceptor 활용 예제 코드
- 상품 관리 서비스(회원 관리, 상품 관리 등 기능)를 기반으로 합니다.

## 참고 방식
- feat/filter, feat/interceptor 브랜치가 있는 커밋 기준으로 각각 filter, interceptor만으로 부가 기능을 구현한 버젼입니다.
  - filter: 로깅, 로그인 처리, csrf 방지(referrer tag 확인을 통한)
  - interceptor: 로깅, 로그인 처리
- 최종 master 브랜치는 filter, interceptor의 특징에 맞게 기능을 간추린 최종 버젼입니다.
