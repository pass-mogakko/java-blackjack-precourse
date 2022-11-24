# 기능 구현 목록

---


## 1.게임에 참여할 사람을 입력 받는다 (쉼표기준 분리) 
- [ ] `예외처리` 쉼표가 아닌 다른 구분자가 존재한다. 
- [ ] 인원 수 만큼 플레이어를 생성한다.

## 2. 각 인원에게 배팅 금액을 입력 받는다.
- [ ] `예외처리` 숫자가 아닌 문자가 들어왔을 때
- [ ] 각 플레이어에게 `bettingMoney`를 할당한다

## 3. 딜러가 카드를 나눠준다.
- [ ] 딜러는 자신에게 2장 나눠주고 첫번째 받은 카드만 공개한다.
- [ ] 각 인원들에게는 2장씩 나눠준다. 모든 카드를 공개한다.

## 4. 첫 카드 합이 21인지 확인한다.
- [ ] 21일 경우 베팅 금액의 1.5배를 받는다. (A + 10, J, Q, K 중 하나)
- [ ] 그러나, 딜러도 21인 경우 플레이어는 베팅한 금액을 돌려받기만 한다.

## 5. 입력받은 인원부터 카드를 더 받을지 정한다.
- [ ] `예외처리` y 또는 n이 아닌 문자가 들어왔을 때
- [ ] 21이 초과될 때까지 입력받을 수 있다.
- [ ] 21이 초과된 경우, 돈을 모두 잃는다.
- [ ] 받을 때 마다 새로운 받은 카드를 포함해 공개한다.
- [ ] n을 입력 받으면 다음 인원이 추가로 받을지 정한다.

## 6. 딜러가 더 받을지 정한다.
- [ ] 16 이하면 무조건 계속 더 받아야한다.
- [ ] 다만, A를 11로 간주했을 때 bust 된다면 1로 계산한다.
- [ ] 만약 bust되면 그대로 끝나고 플레이들에게 배팅금액만큼 준다.
- [ ] 16을 초과하면 그만두고 계산한다.

