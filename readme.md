<h3>개발 요구사항</h3>
<ol>
    <li>spring framework 3.2.4</li>
    <li>JDK 17</li>
    <li>java 커맨드를 통해서 서버가 실행이 되어야함</li>
    <li>서비스로직 테스트코드 작성 필수</li>
</ol>
<h3>요구사항</h3>
    <ul>
        <li>
            권한 있는 접속자는 대여장비(CRUD), 시간표(CRUD),렌탈(CRUD) 기능이 구현이 되어야함
        </li>
        <li>
            수업시간 언제~까지 어떤장비를 대여했는지 알수있어야함
        </li>
        <li>
            대여한 후 장비들이 몇개 남아있는지 볼 수 있어야함
        </li>
        <li>
            시간 단위로 빌려간 사람 누구이고 어떤장비 몇시까지 사용할지에 대해서 구현(고민중)
        </li>
    </ul>
<details>
    <summary style="font-size: large">자세한 구현 사항</summary>
    <ul>
        <li>
            <s>장비 등록</s>
        <ul>
            <li>
                기능: 장비 등록
            </li>
            <li>
                요청: 장비이름, 갯수
            </li>
        </ul>
        </li>
    </ul>
    <ul>
        <li>
            <s>장비 수정</s>
            <ul>
                <li>
                    기능: 장비 갯수 만큼 뺄수있고 더할수있음
                </li>
                <li>
                    요청: 장비이름, (빼기 or 더하기)
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            <s>장비 삭제</s>
            <ul>
                <li>
                    기능: 장비 삭제
                </li>
                <li>
                    요청: 장비아이디(DB PK)
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            <s>수업 등록</s>
            <ul>
                <li>
                    기능: 수업명, 시작 ~ 까지 등록함
                </li>
                <li>
                    요청: 수업이름, 교시 선택(1~12교시) true 또는 false
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            <s>수업 수정</s>
            <ul>
                <li>
                    기능: 수업 이름 수정 및 교시 수정
                </li>
                <li>
                    요청: 기존 수업명, 수정할 이름, 수정할 교시
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            수업 삭제
            <ul>
                <li>
                    기능: 수업 삭제
                </li>
                <li>
                    요청: 수업아이디(DB PK)
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            렌탈 등록
            <ul>
                <li>
                    기능: 렌탈 등록
                </li>
                <li>
                    요청: 수업명, 렌탈할 제품, 수량
                </li>
            </ul>
        </li>
    </ul>
    <ul>
        <li>
            렌탈 삭제
            <ul>
                <li>
                    기능: 렌탈 삭제
                </li>
                <li>
                    요청: 렌탈아이디(DB PK)
                </li>
            </ul>
        </li>
    </ul>
</details>