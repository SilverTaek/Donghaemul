<h1 align="center">동해물가 <img src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/271/flag-south-korea_1f1f0-1f1f7.png" srcset="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/240/apple/271/flag-south-korea_1f1f0-1f1f7.png 2x" alt="Flag: South Korea on Apple iOS 14.2" width="33" height="33"> 농수산물</h1>
<p>
</p>
<p align="center"><img  width=600 src="https://user-images.githubusercontent.com/30452963/113331210-9c169f00-935a-11eb-9ebb-900d1158369c.png" alt="logo" />
<br />
동해물가 농수산물은 지자체 농수산물 물가 데이터를 사용하여 가격 변동 추이를 확인하고, 저렴하게 구입 할 수 있도록 도와주는 서비스 입니다.
<br />
<br />
<img src="https://img.shields.io/static/v1?label=SSAFY&message=4%EA%B8%B0&color=ff69b4">
<img src="https://img.shields.io/static/v1?label=서울&message=3반&color=violet">
<img src="https://img.shields.io/static/v1?label=Domain&message=Bigdata&color=blueviolet">
<br />
<img src="https://user-images.githubusercontent.com/30452963/113960062-d69ea100-985e-11eb-9d0a-baea4c05d0ae.png" width=720 />
</p>

&nbsp;
&nbsp;

---

&nbsp;
&nbsp;

## 목차

- [기획배경](#기획배경)
- [기획](#기획)
  - 와이어프레임
  - UI디자인
  - ERD
- [주요기능](#주요기능)
- [기술스택](#기술스택)
- [개발환경](#개발환경)
  - Git flow 전략
  - 컨벤션
- [서비스구조](#서비스구조)
  - 아키텍쳐
  - 디렉토리 구조
- [시작하기](#시작하기)
- [만든사람들](#만든사람들)

&nbsp;
&nbsp;

---

&nbsp;
&nbsp;

# 🍓기획배경

<img src="https://user-images.githubusercontent.com/30452963/113334646-e8fc7480-935e-11eb-9db3-fb64670076ce.png" width=720 />
<img src="https://user-images.githubusercontent.com/30452963/113334679-f1ed4600-935e-11eb-8555-6bfbfa46da64.png" width=720 />

&nbsp;
&nbsp;

# 🍊기획

## 와이어프레임

> [🔗Figma에서 보기](https://www.figma.com/file/hO0uShXzagyy9WQgvnq0e0/Wireframe?node-id=243%3A1)

<img src="https://user-images.githubusercontent.com/30452963/113335504-03831d80-9360-11eb-8d7a-a1ba497755a4.png" width=720 />

&nbsp;

## UI 디자인

> [🔗Figma에서 보기](https://www.figma.com/file/fBnCyMULsrcTRYgCc5NmWt/UI-Design?node-id=2%3A667)

### 메인 페이지

<img src="https://user-images.githubusercontent.com/30452963/113335739-5230b780-9360-11eb-8d0b-a2eedb48311f.png" width=720 />

### 상세 페이지

<img src="https://user-images.githubusercontent.com/30452963/113957779-d7353880-985a-11eb-9adc-18d86727549a.png" width=720 />

&nbsp;

## ERD

> [🔗ERDCloud에서 보기](https://www.erdcloud.com/d/QiA8ksWRJtSpDKDYo)

<img src="https://user-images.githubusercontent.com/48380687/113811085-85cb7180-97a6-11eb-9e8b-8a3542990172.png" width=720 />

&nbsp;
&nbsp;

# 🍋주요기능

<img src="https://user-images.githubusercontent.com/30452963/113960295-3e54ec00-985f-11eb-8793-42c91fee8a42.png" width=720 />

### 1. 농수산물 물가 확인
### 2. 가격 등락률에 따른 구입 추천/비추천
### 3. 3일 후 예측가격 안내
### 4. 사용자 맞춤 상품 추천

```markdown
## 추천 시스템 - 잠재요인 기반 협업 필터링

사용자-아이템 평점 행렬에 잠재되어 있는 어떤 요인(factor)이 있다고 가정하고, 행렬 분해를 통해 그 요인들을 찾아내는 방식입니다.

SVD(특이값 분해)를 사용하여 사용자-요인, 요인-아이템 행렬로 분해합니다. 또한 분해한 행렬로 사용자들에게 추천할 수 있도록 SGD(확률적 경사하강법)를 사용하여 기존 매트릭스와 아이템-요인 \* 유저-요인의 차이가 최소가 되는 값을 찾아 사용자가 아직 방문하지 않은 정보 중 가장 연관성이 있을 데이터를 추천합니다.
```

&nbsp;
&nbsp;

# 🍏기술스택

<img src="https://user-images.githubusercontent.com/30452963/113966706-904f3f00-986a-11eb-9ff7-9f8f224681a3.png" width=720 />


| Domain   | Name       | Version |
|----------|------------|---------|
| Frontend | vue        | 2.6.11  |
| Frontend | vuetify    | 2.4.0   |
| Frontend | vuex       | 3.6.2   |
| Frontend | charts.js  | 2.9.4   |
| Backend  | Springboot |         |
| Backend  | MariaDB    |         |
| Backend  | FastAPI    | 0.63.0  |
| Backend  | python     | 3.7     |


&nbsp;
&nbsp;

# 🍇개발환경

> [📓git flow 전략](https://www.notion.so/sewonkimm/Git-13372a07ecba456eae0d1ed5637e0861)  
> [📕git 컨벤션](https://www.notion.so/sewonkimm/Git-13372a07ecba456eae0d1ed5637e0861)  
> [📙HTML/CSS 컨벤션](https://www.notion.so/sewonkimm/HTML-CSS-e954f3fba9f947699bb077fa4dd90b8d)  
> [📒JS 컨벤션](https://www.notion.so/sewonkimm/ES6-JS-3c94c15d43a34c5796f676723c8700ff)  
> [📗Spring/Java 컨벤션](https://www.notion.so/sewonkimm/Spring-Java-f5a0d05840c045b48171d79a8fae0a2e)  
> [📘이미지파일 및 폴더명 컨벤션](https://www.notion.so/sewonkimm/452f5f37f624499b9432d2d0e7121b7a)

&nbsp;
&nbsp;

# 🍑서비스구조

### 아키텍쳐

### 디렉토리 구조

```markdown
.
├──📁frontend
├──📁backend
├──📁data
├──📁sql
├──📃.gitignore
├──📃changelog.config.js // commit convention을 위한 설정
└──📃README.md
```

### 디렉토리 구조 - Frontend

```markdown
📁frontend
├──📁views
│  ├──📃Splash.vue
│  ├──📃Main.vue
│  ├──📃Detail.vue
│  ├──📃Login.vue
│  └──📃Register.vue
├──📁components
│   ├──📁css
│   ├──📁main
│   ├──📁detail
│   ├──📁login
│   └──📁register
├──📁assets // 각종 이미지
│   └──📃index.js // svg파일은 index.js 파일 하나로 관리
├──📁router // vue-router
├──📁store  // vuex 
├──📃.eslintrc.js // lint 설정
├──📃vue.config.js
└──📃package.json // 패키지 의존성 관리
```

### 디렉토리 구조 - Backend

```markdown
📁backend
 └──📁src/main
    ├──📁java/com/ssafy/j301
    │   ├──📁common
    │   ├──📁config
    │   ├──📁exception
    │   ├──📁security
    │   ├──📁favorite
    │   │  ├──📃Favorite.java
    │   │  ├──📃FavoriteController.java
    │   │  └──📃FavoriteService.java
    │   ├──📁fluctuationRate
    │   ├──📁ingredient
    │   ├──📁ingredientAvg
    │   ├──📁member
    │   ├──📁popularity
    │   ├──📁shopping
    │   ├──📁transition
    │   ├──📁watch
    │   └──📁mapper
    │       ├──📃FavoriteMapper.java
    │       ├──📃FluctuationRateMapper.java
    │       ├──📃IngredientAvgMapper.java
    │       ├──📃IngredientMapper.java
    │       ├──📃MemberMapper.java
    │       ├──📃PopularityMapper.java
    │       ├──📃ShoppingMapper.java
    │       ├──📃TransitionMapper.java
    │       └──📃WatchMapper.java
    ├──📁resources
    │   ├──📃application.properties
    │   └──📁mapper
    │       ├──📃favorite.xml
    │       ├──📃fluctuationRate.xml
    │       ├──📃ingredient.xml
    │       ├──📃ingredientAvg.xml
    │       ├──📃member.xml
    │       ├──📃popularity.xml
    │       ├──📃shopping.xml
    │       ├──📃transition.xml
    │       └──📃watch.xml
    ├──📃.gitignore
    ├──📃build.gradle  // gradle 빌드 툴 설정
    └──📃keystore.p12  // SSL 기능을 위한 설정
```

### 디렉토리 구조 - Data(빅데이터 관련)

```markdown
📁data
 └──📁fastApi
    ├──📁app
    │  ├──📁common // 환경설정 폴더
    │  ├──📁database // DB연결 및 스키마 폴더
    │  └──📁routes // RestApi routes 폴더
    ├──📃main.py // 실행 파일
    ├──📃README.md
    └──📃requirements.txt // 패키지 의존성 관리
 
    
```

&nbsp;
&nbsp;

# 🍭시작하기


## Client

```bash
$ cd frontend
$ npm install
$ npm run serve
```

## Data

```bash
$ cd data/fastApi
$ python version : 3.7
$ pip install -r requirements.txt
$ python main.py
```

## DB

sql 폴더 내 쿼리문 실행


## 서버

### 1. Java 설치

```bash
$ sudo apt-get install openjdk-8-jre
$ sudo apt-get install openjdk-8-jdk
```

### 2. Npm 설치

```bash
$ sudo apt install npm
```

### 3. Python3.7 설치

```bash
$ sudo apt update
$ sudo apt install software-properties-common
$ sudo add-apt-repository ppa:deadsnakes/ppa 
$ sudo apt update 
$ sudo apt install python3.7
```

### 4. Python3.7 venv

```bash
$ sudo apt-get install python3.7-venv //python3.7 가상환경 설치
$ python3.7 -m venv my_common_env //python3.7 가상환경 활성화
$ cd my_common_env/bin
$ source activate 
```

### 5. Docker 설치

```bash
$ sudo apt install apt-transport-https ca-certificates curl gnupg-agent software-properties-common // 다음 패키지들을 설치
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add – // Docker의 공식 GPG키를 추가한다.
$ sudo add-apt-repository "deb [arch=amd64] 
https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" // stable repository를 세팅하기 위한 명령어
$ sudo apt install docker-ce docker-ce-cli containerd.io // 가장 최신 버전의 Docker 엔진을 설치한다.
```

### 6. Docker MariaDB install

```bash
$ sudo docker run --name DB이름 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=비밀번호 - d mariadb // Docker로 Mariadb 설치 및 실행
```

### 7. Gradle install

```bash
$ apt-get update
$ apt-get install unzip wget
$ wget https://downloads.gradle-dn.com/distributions/gradle-6.3-bin.zip //gradle 6.3 설치
$ unzip gradle-6.3-bin.zip -d /opt
$ In -s /opt/gradle-6.3 /opt/gradle
$ vi /etc/profile.d/gradle.sh

gradle 설정

#/bin/bash
export GRADLE_HOME=/opt/gradle
export PATH=/opt/gradle/bin:${PATH}

$ gradle -v //gradle 설치 확인
```

### 8. 프로젝트 설치 및 실행

```bash
$ git clone https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301.git         #프로젝트 받기(Git)
$ npm install #jar, dist 파일 생성
$ npm run build #dist 파일 생성
$ gradle builder #jar 파일 생성
```

### 9.배포

```bash
<data 폴더> 파이썬 가상환경
$ pip install -r requirements # 필요한 모듈 설치
$ uvicorn main:app --reload --host=0.0.0.0 --port=8000 # fastApi 실행하기

<front 폴더>
$ mv dist /var/www/html # 명령어로 이동

<backend 폴더>
$ java -jar (파일이름).jar
```

### 10. Nginx 설정

```bash
$ cd /etc/nginx/sites-available
$ sudo vi default // 설정파일 열기
```

![image](https://user-images.githubusercontent.com/43171179/113813360-ba412c80-97aa-11eb-8701-b4bb4d1a35a1.png)

![image](https://user-images.githubusercontent.com/43171179/113813530-0c824d80-97ab-11eb-8765-7e1aa7592943.png)



# 🧙‍♂️만든사람들

## SSAFY 4기 서울 3반 A301팀

### 팀명 - 오.합.지.존

다섯명의 지존이 모였다. **五.合.至.尊.**

### 팀원

<table>
  <tr>
    <td align="center">
    <img src="https://secure.gravatar.com/avatar/8d818ca9720e049554b26576590e251e?s=180&d=identicon" width="75px;" alt="이은택"/><br /><sub>서버지존<br/><b>이은택</b></sub><br />
    </td>
    <td>
    💻인프라&BE개발
    <br/>
        <a href="https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&assignee_username=rudus1012" title="Code">📜 Commit Log</a>
        <br/>
    </td>
    <tr>
    <td align="center"><a href="https://github.com/sewonkimm"><img src="https://avatars.githubusercontent.com/u/30452963?v=4" width="75px;" alt="김세원"/><br /><sub>거지존<br/><b>김세원</b></sub></a><br /></td>
    <td>
    🎨UI디자인&FE개발
    <br/>
    <a href="https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&assignee_username=swon962" title="Code">📜 Commit Log</a>
    </td>
    <tr>
    <td align="center"><img src="https://lab.ssafy.com/uploads/-/system/user/avatar/2093/avatar.png?width=90" width="75px;" alt="김지영"/><br /><sub>UI지존<br/><b>김지영</b></sub><br /> </td>
    <td>
    🎨UI디자인&FE개발
    <br/>
    <a href="https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&assignee_username=jiiioiyoung96" title="Code">📜 Commit Log</a>
    </td>
    <tr>
    <td align="center"><img src="https://secure.gravatar.com/avatar/455ee6feb7db33aca75d87bbd37fbf59?s=180&d=identicon" width="75px;" alt="여인구"/><br /><sub>돼지존<br/><b>여인구</b></sub><br /></td>
    <td>
    💻BE개발
    <br/>
     <a href="https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&assignee_username=yeoingu94" title="Code">📜 Commit Log</a>       
    </td>
  </tr>
    <tr>
    <td align="center"><img src="https://secure.gravatar.com/avatar/444729ddcc1195a2e6598a378faa2ac1?s=180&d=identicon" width="75px;" alt="천창민"/><br /><sub>미소지존<br/><b>천창민</b></sub><br /></td>
    <td>
    💻BE개발&데이터분석
    <br/>
     <a href="https://lab.ssafy.com/s04-bigdata-sub3/s04p23a301/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&assignee_username=chunawoos" title="Code">📜 Commit Log</a>       
    </td>
  </tr>
</table>
