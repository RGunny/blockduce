# Git Setting

## 1. 초기 세팅(최초 1회)

### 1.1 Fork Repository

![image](https://user-images.githubusercontent.com/40309812/111105351-1d69e580-8596-11eb-9f87-20eb0787793c.png)



![image](https://user-images.githubusercontent.com/40309812/111105344-1a6ef500-8596-11eb-931d-0fb374d9b883.png)

- 원본(upstream) 팀 레포지토리를 포크한다.



### 1.2 Local PC에 git remote 연결

![image](https://user-images.githubusercontent.com/40309812/111105452-5a35dc80-8596-11eb-813f-fbde53a0a83e.png)

- https URL을 복사한다.



![image](https://user-images.githubusercontent.com/40309812/111105623-c0bafa80-8596-11eb-9812-428c5e7ea612.png)

- $ `git clone [fork repository https url] [해당 레포지토리를 생선하는 디렉토리 이름]`
  - 원하는 디렉토리에 원하는 이름으로 repository를 clone한다
- special-2 라는 디렉토리가 생기고, 그 안에 clone한 repository가 생겼다.
- origin 이라는 remote 이름으로 clone한 repository(포크해서 만든 내 레포지토리)가 자동으로 붙어있다.



### 1.3 upstream 추가

![image](https://user-images.githubusercontent.com/40309812/111106082-ba794e00-8597-11eb-9921-d7b9d01b1127.png)

- $ `git remote add upstream [팀 repository https url]`
  - 팀 레포지토리의 https url로 upstream remote를 추가한다.

- $ `git remote -v` 명령어로 확인해보면 레포지토리가 연결되었다.

  - origin : fork한 레포지토리
  - upstream : 팀(원본) 레포지토리

  

## 2. 깃 브랜치 생성 후 작업하는 단계(반복)

### 2.1 Create Branch(upstream)

![image](https://user-images.githubusercontent.com/40309812/111104928-18f0fd00-8595-11eb-9ea7-2baf359d8030.png)



![gitlab-create-branch](https://user-images.githubusercontent.com/40309812/111104809-de876000-8594-11eb-98e8-6603686c6cf7.png)

- upstream(팀 레포지토리)에서 새로운 작업을 위해 브랜치를 생성한다.
- `feature-[지라 이슈 번호]` 와 같은 네이밍으로 브랜치를 생성한다.
  - ex) feature-56
- 브랜치 생성시 Create from `develop` 으로 `최신 상태`를 유지하고 있는 develop 브랜치에서 가지를 만든다.



###  2.2 Local PC에 작업할 브랜치 생성

![image](https://user-images.githubusercontent.com/40309812/111105125-9a488f80-8595-11eb-916f-f14932c55231.png)

- $ `git fecth --all` 명령어로 연결된 리포지토리의 최신 상태를 불러온다.
  - upstream의 feature-56 브랜치를 로컬에서 접근 가능



![image](https://user-images.githubusercontent.com/40309812/111106312-383d5980-8598-11eb-923b-810307c41ba4.png)

- $ `git checkout -b [로컬 PC에 생성할 브랜치 이름] --track upstream/[작업하기 위해 만든 브랜치 이름]`
  - 앞의 브랜치 이름은 내 로컬 PC에서 작업하게 될 브랜치의 이름을 생성하는 과정이다. 
    헷갈리니 upstream에서 새로운 작업을 위해 만들었던 브랜치 이름과 같게 설정하는 것이 좋다.
  - 뒤의 브랜치 이름은 새로운 작업을 위해 upstream 레포지토리에서 생성했던 브랜치 이름이다. 
    해당 브랜치는 최신 develop의 내용으로부터 분기가 시작된 브랜치이다.
- 리모트 트래킹 브랜치를 로컬 브랜치로 checkout하면 자동으로 `트래킹(Tracking) 브랜치`가 생성된다.
  - 트래킹하는 대상 브랜치를 `upstream 브랜치`라 부른다.
  - 트래킹 브랜치는 리모트 브랜치와 직접적인 연결고리가 있는 로컬 브랜치이다.
  - 트래킹 브랜치에서 `git pull` 명령을 내리면 리모트 저장소로부터 데이터를 내려 받아 연결된 리모트 브랜치와 자동으로 `Merge`한다. 
- 해당 브랜치에서 목표했던 작업을 시작한다.



### 2.3 Local PC에서 작업이 끝난 후 commit하는 과정

![image](https://user-images.githubusercontent.com/40309812/111118639-6331a880-85ac-11eb-94fa-58aa63ebdc48.png)

- $ `git add .` 
  - 해당 명령어로 작업한 내용(기존 코드와의 변경 내역)을 추가한다.

![image](https://user-images.githubusercontent.com/40309812/111118723-878d8500-85ac-11eb-8b9f-1f4e949ca9f3.png)

- $ `git commit -m "{지라이슈번호} [작업분류] : {FE or BE} - 작업커밋내용"`
  - 컨벤션에 맞게 커밋 메세지를 작성한다.
  - ex) $ `git commit -m "S04P22B107-56 [feat] : BE - AWS RDS, S3 서버 및 기본환경 세팅"`

![image](https://user-images.githubusercontent.com/40309812/111118997-dfc48700-85ac-11eb-98ae-a864f35022a2.png)

- $ `git pull --rebase upstream develop` 
  - 해당 명령어를 통해 upstream 레포지토리의 develop 브랜치의 최신 상태를 pull 하고 작업 분기를 최신 상태로 옮긴다.

![image](https://user-images.githubusercontent.com/40309812/111119190-29ad6d00-85ad-11eb-9f4e-7b2d31eb5078.png)

- $`git push origin feature-[지라 이슈 번호]`
  - 해당 명령어를 통해 origin 레포지토리의 feature-[지라 이슈 번호] 브랜치에 commit을 push한다.
  - ex) $`git push origin feature-56`



### 2.4 Merge Request

![image](https://user-images.githubusercontent.com/40309812/111116545-81e27000-85a9-11eb-8cac-bd9b439ebbf6.png)

- push 후 생성된 `Create merge request` 버튼을 누른다.



![image](https://user-images.githubusercontent.com/40309812/111117510-f5d14800-85aa-11eb-89bb-1f8a143f4049.png)

- Title과 Description을 컨벤션에 맞게 작성한다.



![image](https://user-images.githubusercontent.com/40309812/111117584-0f728f80-85ab-11eb-8b29-b5b370739dff.png)

- Source branch와 Target branch를 확인한다.
  - Target branch가 `develop`인지 꼭 확인한다.



![image](https://user-images.githubusercontent.com/40309812/111117703-392bb680-85ab-11eb-90ed-89e2925e118b.png)

- 변경 내역이 맞는 지, 충돌이나 실수한 부분이 없는 지 최종 확인한다.



![image](https://user-images.githubusercontent.com/40309812/111117800-56608500-85ab-11eb-9f61-ea8a252b850a.png)

- 이상이 없다면 `Submit merger request` 버튼을 누른 뒤, 팀원에게 확인 및 코드 리뷰를 부탁한다.



![image](https://user-images.githubusercontent.com/40309812/111117928-814ad900-85ab-11eb-9655-75ced6ad990c.png)

- 이 부분에서 에러가 있을 시 재확인 후  `Close merge request`  혹은 문제없게 다시 처리한다.



![image](https://user-images.githubusercontent.com/40309812/111118171-ce2eaf80-85ab-11eb-99e8-8c496b14dc68.png)

- 팀원은 해당 머지 리퀘스트에 대해 커밋 과 변경 사항에 대해 `충돌, 문제점` 혹은 `개선사항`에 대해 `코드리뷰`를 한다.
- 이상이 없다면 머지 버튼을 눌러 머지시킨다.





### References

- [Git-브랜치-리모트-브랜치](https://git-scm.com/book/ko/v2/Git-%EB%B8%8C%EB%9E%9C%EC%B9%98-%EB%A6%AC%EB%AA%A8%ED%8A%B8-%EB%B8%8C%EB%9E%9C%EC%B9%98)
- [우아한 git flow](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)