# 대학교에 기자재 대여 관련 프로젝트 입니다

<p align="center">
  <br>
  <img src="./images/common/main-page-info.gif">
  <br>
</p>

## 프로젝트 소개

<p align="justify">
이 프로젝트는 학교에서 사용되는 다양한 장비들을 효율적으로 관리하기 위한 시스템입니다. 
조교들이 장비의 대여 및 반납을 쉽게 관리하고, 장비의 현재 상태 도움을 주고 싶게 되어 만들게 되었습니다.
</p>

<p align="center">
GIF Images
</p>

<br>

## 기술 스택

### 백엔드

| Spring-Boot  |  Spring-Data   |  Querydsl   | mysql    |
|:------------:|:--------------:|:-----------:|----------|
| ![spring-boot] | ![spring-data] | ![querydsl] | ![mysql] |

### 프론트엔드
| Html    | JavaScript |  jQuery   |
|---------| :--------: |:---------:|
| ![html] |   ![js]    | ![jquery] |

### 배포
| docker    | amazon-ec2    | amazon-rds    |
|-----------|---------------|---------------|
| ![docker] | ![amazon-ec2] | ![amazon-rds] |


<br>

## 구현 기능

### 카테고리
#### 관리자
    상위 카테고리와 하위 카테고리 생성,조회,수정,삭제 기능 구현하고
    기자재를 상위, 하위 카테고리 분리함으로써 보기 편하게 했습니다
#### 일반 사용자
    권한X

### 기자재
#### 관리자
    카테고리, 기자재명, 내용, 이미지, 수량 조회 할 수 있고 
    생성, 수정, 삭제 기능 이 있습니다
#### 일반사용자
    현재 요일에 기자재가 몇 개 남았는지 실시간으로 볼 수 있게 스프링 스케줄링 이용하여
    수업 반납 뺀 후에 웹 사이트에 기자재 개수, 기자재 이름이 표시됩니다.

### 시간표
    월~금요일에 1교시 ~10교시까지 어떠한 수업에 어떤 
    기자재 개수 몇 개 반출하는지 볼 수 있고 생성, 수정, 삭제
    기능이 있습니다.

### 대여
#### 관리자
    카테고리, 장비, 수업명, 대여 수 입력 받아 생성 할 수 있고 수정, 삭제 할 수 있습니다.
#### 일반사용자
    권한X

<br>

## 배운 점 & 아쉬운 점

<p align="justify">

</p>

<br>

## 라이센스

readme MIT &copy; 작성자 [NoHack](mailto:lbjp114@gmail.com)

readme template: <a href="https://github.com/n0hack/readme-template.git">바로가기</a>

<!-- Stack Icon Refernces -->

[html]: /images/stack/frontend/html.svg
[js]: /images/stack/frontend/javascript.svg
[jquery]: /images/stack/frontend/jquery.svg

[spring-boot]: /images/stack/backend/spring-boot.svg
[spring-data]: /images/stack/backend/spring-data.svg
[querydsl]: /images/stack/backend/querydsl.png
[mysql]: /images/stack/backend/mysql.png

[docker]: /images/stack/docker.png
[amazon-ec2]: /images/stack/amazon-ec2.png
[amazon-rds]: /images/stack/amazon-rds.png