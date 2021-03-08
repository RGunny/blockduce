# 특화 Sub2_B107

## 역할
| 이름   | 직책 | 역할                             |
| ------ | ---- | -------------------------------- |
| 황호연 | 팀장 | Backend, Project Manager         |
| 류건희 | 팀원 | Backend, Tech Leader, Git Master |
| 박상우 | 팀원 | Backend, QA                      |
| 최주아 | 팀원 | Frontend, Tech Leader            |
| 황영준 | 팀원 | Backend, CI/CD                   |


# 주제

# 프로젝트 명

# 기능

# 기술 스택

# Convention

## Git flow 규약

### 우아한 형제들 기술 블로그

[우린 Git-flow를 사용하고 있어요 - 우아한형제들 기술 블로그](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)

### 브랜치 네이밍

- Backend : master → develop → feature-(JIRA에픽 name) → JIRA이슈번호_BE
- Frontend : master → develop → feature-(JIRA에픽 name) → JIRA이슈번호_FE
    - master : 제품으로 출시될 수 있는 브랜치
    - develop : 다음 출시 버전을 개발하는 브랜치

                     (기능이 완성되면 feature를 develop에 merge 시킴)

    - feature : 기능을 개발하는 브랜치(JIRA 에픽 네임으로 설정 ex)Login)

                     (JIRA 이슈가 해결되면 이슈를 feature에 merge시킴)

    - JIRA-BE : JIRA에 등록된 이슈 대로 브랜치를 만들어 기능을 만듦

### Merge 규칙

- 작업을 시작하기 전에 JIRA 이슈를 생성한다.
- 이슈는 되도록 하나의 커밋으로 한다.
- 커밋 그래프는 최대한 단순하게 한다.
- 서로 공유하는 브랜치의 커밋 그래프는 함부로 변경하지 않는다.
- merge 를 하기 전 적어도 1명의 팀원에게 코드 리뷰 후 merge를 시킴
- 자신의 Pull Request는 스스로 merge를 한다.

### 브랜치 Fork하기

1. 프로젝트(upstream)에서 fork를 떠서 나의 레포(origin)를 생성한다
2. 나의 레포(origin)에서 나의 컴퓨터(local)에 git clone 을 받는다
3. 로컬에서 upstream을 추가해준다.

    > $ git remote add upstream [https://lab.ssafy.com/s04-webmobile2-sub3/s04p13b204.git](https://lab.ssafy.com/s04-webmobile2-sub3/s04p13b204.git)

    > $ git remote [-](https://lab.ssafy.com/s04-webmobile2-sub2/s04p12b204.git)v 로 확인

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b36e72b0-2051-46a3-851f-a74f7ffa751e/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b36e72b0-2051-46a3-851f-a74f7ffa751e/Untitled.png)

4. 로컬에서 upstream/feature 자신이 작업할 feature-{작업내용} 를 생성한다. (track은 upstream/feature

    project에 develop에 new branch 형성

    이후 git fetch —all 로 받아온 후 아래 적용 -v

    > $ git checkout -b feature-{작업내용} --track upstream/feature

### 브랜치 작업하기

1. 해당 브랜치에서 해당 내용을 작업하고 add 한다

    > $ git add .

2. commit 한다(양식에 맞춰서)

    > $ git commit -m “이슈번호 [feat] : BE - 로그인 기능 추가”

    - 아래와 같이 사용

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c4fb8bbe-79f9-4fd0-9a4e-30a8e9e9de55/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c4fb8bbe-79f9-4fd0-9a4e-30a8e9e9de55/Untitled.png)

    ```css
    feat       : 새로운 기능 추가
    fix        : 버그 수정
    refactor   : 코드 리팩토링
    style      : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    docs       : 문서 수정
    test       : 테스트 코드, 리팩토링 테스트 코드 추가
    chore      : 빌드 업무 수정, 패키지 매니저 수정
    ```

3. 필요하면 rebase로 커밋을 줄인다 아래는 2개 합친 것(선택사항)

    > $ git rebase -i HEAD~2

4. fetch로 최신화 시켜준다

    > $ git fetch --all 로 local을 r최신화 시켜준다

5. upstream레포의 해당 브랜치의 최신 작업 내용을 땡겨서 rebase한다.(dev로 합칠경우)

    > $ git pull --rebase upstream develop

    - 오류발생시!!!

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc97f4c7-fb8b-4bb0-8b16-e67403abc3a6/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc97f4c7-fb8b-4bb0-8b16-e67403abc3a6/Untitled.png)

    - git status 찍으면 아래와 같이

        Unmerged가 있음(local에서 하나하나 해결해야함)

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b8f06d20-dd5f-42fc-8d48-12ac286bebb6/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b8f06d20-dd5f-42fc-8d48-12ac286bebb6/Untitled.png)

    - git rebase —continue를 하면 다시 리베이스 진행

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/01e8d7a5-80a8-4a45-bf73-a8ba5dc01134/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/01e8d7a5-80a8-4a45-bf73-a8ba5dc01134/Untitled.png)

    - commit이 불필요하게 여러개 있으면
    - git rebase -i HEAD~{갯수}

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8d3c7dc8-bda0-4762-bdb5-d16bca6eb46a/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8d3c7dc8-bda0-4762-bdb5-d16bca6eb46a/Untitled.png)

6. origin레포로 push 한다.c

    > $ git push origin 작업브랜치

1. Fork뜬 나의 origin 브랜치를 upstream develop에 merge하는 Pull Request를 생성합니다.
2. 같은 feature를 개발하는 동료에게 리뷰 승인을 받은 후 자신의 Pull Request를 merge합니다. 만약 혼자 feature를 개발한다면 1~2명의 동료에게 리뷰 승인을 받은 후 Pull Request를 merge합니다.

### Option (작업중 최신화)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68abae8f-0f00-4ccc-ac62-c1191a744ca5/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68abae8f-0f00-4ccc-ac62-c1191a744ca5/Untitled.png)