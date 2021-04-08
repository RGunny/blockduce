- # BLOCKDUCE

  ---

  ![BLOCKDUCE_LOGO](https://user-images.githubusercontent.com/70404643/112577381-8f181e00-8e37-11eb-86f2-b35b12c89ce6.png)

  > [**BLOCKDUCE** **LINK**📌]
http://j4b107.p.ssafy.io/login

---

# 기술 스택

---

# Convention

## Git Convention
## Git flow 규약

### 우아한 형제들 기술 블로그d

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

  ![슬라이드2](https://user-images.githubusercontent.com/70404643/112589331-3e5af200-8e44-11eb-840e-1d7ab171f4bc.PNG)

|     Name     |                            황호연                            |                            류건희                            |                            박상우                            |                 최주아                 |                            황영준                            |
| :----------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :------------------------------------: | :----------------------------------------------------------: |
| **Profile**  | ![KakaoTalk_20210326_135538764](https://user-images.githubusercontent.com/70404643/112585091-80803580-8e3c-11eb-9d5d-8b5972443cf1.jpg) | ![KakaoTalk_20210326_135652125](https://user-images.githubusercontent.com/70404643/112585083-7d854500-8e3c-11eb-8ed0-45f16d12b940.jpg) | **![A급](https://user-images.githubusercontent.com/70404643/112585018-63e3fd80-8e3c-11eb-98ca-46264e090ef4.jpg)** |                                        | ![KakaoTalk_20210326_135558007](https://user-images.githubusercontent.com/70404643/112585061-7827fa80-8e3c-11eb-823c-7940cfe1e206.jpg) |
|   **R&R**    |                           **팀장**                           |                           **팀원**                           |                           **팀원**                           |                **팀원**                |                           **팀원**                           |
|   **Git**    |        [@HoYeonHwang](https://github.com/HoYeonHwang)        |             [@RGunny](https://github.com/RGunny)             |              [@upswp](https://github.com/upswp)              | [@bourzua](https://github.com/bourzua) |         [@junjun0905](https://github.com/junjun0905)         |
| **Position** |                   Backend, Project Manager                   |                     Backend, Tech Leader                     |                         Backend, QA                          |         Frontend, Tech Leader          |                        Backend, CI/CD                        |

  ## Team Communication Rule 🤙

  - Webex는 09:00 - 18:00 까지 항상 Online !

    - 비대면을 위한 개발환경이지만 모두를 위한 매너는 지킵시다!

  - 질문은 언제나 자유롭게 !

    - 프로젝트를 진행하며 책임은 철저히! 고민은 다 같이 !

  - 약속시간은 모두를 위한 약속 !

    - 비대면으로 이뤄지는 모든 환경은 오해를 하기 쉬운 환경이므로 문제가 생기면 항상 사전에 미리미리 말하기!

  - ### [Git Convention🧐](docs/Git Convention.md)

  - ### [JIRA Convention🤓](docs/JIRA Convention.md) 

---

  ![슬라이드3](https://user-images.githubusercontent.com/70404643/112586039-62b3d000-8e3e-11eb-9748-c3430adb1535.PNG)

  - 프로듀스 101 투표조작 사건을 기반으로 올바른 온라인 투표 시스템 문화 정착을 위한 프로젝트입니다.
  - 블록체인을 이용하여 누구나 믿을 수 있는 온라인 투표 시스템 BLOCKDUCE를 기획했습니다.

---

  ![슬라이드4](https://user-images.githubusercontent.com/70404643/112587237-97c12200-8e40-11eb-8cdc-cf04af3a4216.PNG)


---
  - 어카운트 페이지
<img src="/uploads/c275149c19f1424518e945b0c5e249c0/화면_캡처_2021-04-02_104718.png"  width="600" height="500">

    - 지갑을 생성한 후 투표횟수 투표에 사용한 DBC, 보상으로 받은 ETH 등을 확인할 수 있습니다.
    - * DBC는 BLOCK DUCE에서 투표에 사용되는 재화 입니다. ETH는 투표에 대한 보상으로 받는 재화입니다.

  - 카카오 로그인 구조
<img src="/uploads/70e2cf44475501195e81d0eb65b7616a/화면_캡처_2021-04-07_220534.png"  width="600" height="500">

    - BLOCK DUCE는 소셜 로그인을 지원합니다.

   - 일반 로그인
<img src="/uploads/9ab7525c5e843865357b040b3e7b79a3/화면_캡처_2021-04-07_222524.png"  width="600" height="500">

    - 이메일 인증을 통한 일반 로그인 또한 지원합니다.
    
- 투표 페이지
<img src="/uploads/147ccc3f0864bdbf21be9a8a166dbea3/화면_캡처_2021-04-07_223033.png"  width="600" height="500">
    
    - 오늘의 보상으로 DBC를 받고 지지하는 후보에게 투표할 수 있습니다.

   - 캘린더 페이지
<img src="/uploads/e2662807521e098618ea0c4e8fe72b6c/화면_캡처_2021-04-07_223303.png"  width="600" height="500">

    - 캘린더를 통해 자신이 언제 얼마를 어떤 후보자에게 투자했는지 파악할 수 있습니다.
    
  <details>
      <summary>Front 자세히 살펴보기 🌈</summary>
      <ul>
          <li>기술스택 ⚙</li>
      </ul>   
      <ul>
          <li>Vue: 2.6.11</li>
      </ul>
       <ul>
          <li>라이브러리 📚</li>
      </ul>   
      <ul>
          <li>Vue: 2.6.11</li>
      </ul>
  </details>



  <details>
      <summary>Back 자세히 살펴보기 🔥</summary>
      <ul>
          <li>Swagger: 2.9.2</li>
          <li>Spring Boot</li>
          <li>Spring Security</li>
          <li>Spring Jenkins</li>
          <li>E2C</li>
      </ul>
  </details>



  <br>

---

  