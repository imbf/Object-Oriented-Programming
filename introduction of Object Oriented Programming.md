# 객체 지향 프로그래밍 입문

## Introduction

**캡슐화와 추상화에 대해서 중점적으로 설명하겠다.**

### 선수 지식

- private, public
- 클래스, 추상 클래스, 인터페이스
- 추상 메서드
- 상속(extends, implements)
- 오버라이딩(재정의)

### 비용

버젼이 올라갈 수록 코드 한줄당 작성 비용이 늘어난다.

### 주요 원인

- 코드 분석 시간 증가
- 코드 변경 시간 증가

### 소프트웨어 가치 : 변화

**소프트웨어를 유지보수 한다는 것은 이전처럼 동작하는게 아니라, 변화하는 세계에서 여전히 유용해야 한다.**

### 비용과 변화

- 낮은 비용으로 변화할 수 있어야 함
- 이를 위한 방법
   - 패러다임
      - 객체 지향, 함수형, 리액티브, ...
   - 코드, 설계, 아키텍처
      - DRY, TDD, SOLID, DDD, ...
      - 클린 아키텍처, MSA, ...
   - 업무 프로세스/문화
      - 애자일, DevOps, ...

### 객체 지향과 비용

**캡슐화 + 다형성(추상화)**

---

## Object(객체)

### 절차 지향

**데이터를 여러 프로시저가 공유하는 방식**

 **시간이 흘러갈 수록 데이터를 공유하는 방식은 구조가 복잡해지고 유지보수를 어렵게 한다.**

### 절차 지향과 비용

데이터를 여러 프로시저가 사용하는 절차 지향적인 언어는 데이터를 자꾸 복사하게 되고 코드를 자꾸 복사하게 되는 비효율적인 현상이 발생한다.

### 객체 지향 (Object-Oriented)

**객체 지향은 데이터와 프로시저를 객체라는 단위로 묶는다.**

특정 객체가 가지고 있는 데이터를 그 객체의 프로시저만 접근할 수 있도록 한다.

다른 객체에서는 그 객체의 데이터(private 필드)에 바로 접근할 수 없다.

**객체는 프로시저를 이용해서 외부에 데이터를 제공한다. (프로시저를 호출하는 방식)**

### 객체란

-  **객체의 핵심 -> 기능 제공**
   - 객체는 제공하는 기능으로 정의
      - 내부적으로 가진 필드(데이터)로 정의하지 않음
- 예 : 회원 객체
   - 암호 변경하기 기능
   - 차단 여부 확인 하기 기능
- 예 : 소리 제어기
   - 소리 크기 증가하기 기능
   - 소리 크기 감소하기 기능

### 기능 명세

- **메서드(오퍼레이션)를 이용해서 기능 명세**

   - 이름, 파라미터, 결과로 구성

   ```java
   public class VolumeController{
     public void increase(int inc){
       ...
     }
     public void decrease(int dec){
       ...
     }
     public int volume(){
       ...
     }
   }
   
   public Member{
     public void changePassword(String curPw, String newPw){
       ...
     }
   }
   ```

### 객체와 객체

- 객체와 객체는 기능을 사용해서 연결

   - 기능 사용 = 메서드 호출

   ```java
   VolumnController volCont = new VolumnController();
   
   volCont.increase(4);
   
   volCont.decrease(3);
   
   int currentVol = volCont.volume();
   ```

### 용어: 메세지

- **<u>객체와 객체 상호 작용</u> : 메시지를 주고 받는다고 표현**
   - 메서드를 호출하는 메시지, 리턴하는 메시지, 익셉션 메시지

### 객체 (객체는 기능으로 정의한다 !!!! 꼭 기억하자)

```java
// 이 클래스에서 생성된 인스턴스는 객체가 아니라 데이터이다.
// 이 클래스에서 기능이 붙은 인스턴스를 객체라고 할 수 있다.
public class Member{
  private String name;
  private String id;
  
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public void setId(String id){
    this.id = id;
  }
  public String getId(){
    return id;
  }
}
```

---

## 캡슐화(Encapsulation)

- **데이터 + 관련 기능 묶기**
- **객체가 기능을 어떻게 구현했는지 외부에 감추는 것**
   - 구현에 사용된 데이터의 상세 내용을 외부에 감춤
- **정보 은닉(Information Hiding) 의미 포함**
- **외부에 영향 없이 객체 내부 구현 변경 가능**

### 캡슐화하지 않으면

요구사항의 변화가 데이터 구조/사용에 변화를 발생시킴 -> 데이터를 사용하는 코드의 수정 발생

### 캡슐화하면

- **기능을 제공하고 구현 상세를 감춤**

   ```
   
   ```

   























































