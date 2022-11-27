# 기능목록

### 플레이어 정보 입력
- [x] 플레이어의 이름을 입력받는다.
  - **예외처리** IllegalArgumentException 발생시키고 메시지 출력후 재입력
    - [x] 알파벳 소문자만 입력되었는지 확인한다.
- [x] 플레이어의 베팅 금액을 입력받는다.
  - **예외처리** IllegalArgumentException 발생시키고 메시지 출력후 재입력
    - [x] 숫자만 입력되었는지 확인한다.
    - [x] 1원 이상 100,000,000원 이하인지 확인한다.
  - [x] 각 베팅 금액을 리스트에 저장한다. 

### 카드 덱 생성
- [x] 카드 덱을 생성한다.
- [x] 카드 덱과 숫자 리스트를 HashMap으로 연결한다.

### 카드 세팅
- [] 딜러와 플레이어에게 카드를 2장씩 나눠준다.
  - [] 0 ~ 51의 숫자 중 하나씩 랜덤 번호를 생성한다.
    - [x] 랜덤 번호가 덱에 존재하는지 확인한다.
  - [x] 해당 번호 인덱스의 카드를 덱에서 삭제한다.

### 카드 출력
- [] 딜러와 플레이어가 받은 카드를 출력한다.
  - 딜러의 카드는 첫 장만 출력한다.
  - **카드 출력 형식**
    - [] `심볼` + `타입`으로 한다.
    - ex. 7스페이드, K하트 등

### 플레이어 카드 확인
- [] 플레이어의 차례가 종료될 때까지 순서대로 카드를 확인한다.
  - **에이스 카드**
    - [] 나머지 카드의 합이 10인 경우 11로 계산한다.
    - [] 나머지 카드의 합이 11이상인 경우 1로 계산한다.
- [] 카드의 합이 21인 경우 `딜러 -(플레이어가 베팅한 금액 * 1.5)` / `플레이어 +(베팅한 금액 * 1.5)`
- 카드의 합이 21 미만인 경우
  - [] 1장을 더 뽑을지 다시 결정한다.
    - 1장을 더 뽑은 경우
      - [] 21을 초과한 경우 `딜러 +플레이어가 베팅한 금액` / `플레이어 -베팅한 금액`
    - 뽑지 않은 경우
      - [] 다음 플레이어의 카드 확인

### 딜러 카드 확인
- 두 장의 카드를 확인한다.
  - [] 16이하인 경우 17이상이 될 때까지 카드 덱에서 1장씩 뽑는다.
  - [] 카드를 뽑을 때 마다 합계를 확인하여 문구를 출력한다.

### 결과 확인
- 남아있는 플레이어의 결과를 확인한다.
- [] 딜러의 카드가 21을 초과한 경우 `딜러 -남아있는 플레이어가 베팅한 금액` / `남아있는 플레이어 +각자 베팅한 금액`
- [] 딜러와 플레이어 모두 21인 경우 `플레이어 +베팅한 금액`
- 딜러의 카드가 21 미만인 경우
  - [] 플레이어의 카드가 21인 경우 `딜러 -플레이어가 베팅한 금액` / `플레이어 +베팅한 금액`
  - 플레이어의 카드가 21 미만인 경우
    - [] 딜러가 높은 경우 `딜러 +플레이어가 베팅한 금액` / `플레이어 -베팅한 금액`
    - [] 플레이어가 높은 경우 `딜러 -플레이어가 베팅한 금액` / `플레이어 +베팅한 금액`

### 결과 출력
- [] 딜러, 플레이어의 카드와 결과를 출력한다.
- [] 딜러, 플레이어의 최종 수익을 출력한다.